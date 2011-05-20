package com.ybi.whoot.widget;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RemoteViews;

import com.ybi.whoot.R;
import com.ybi.whoot.activities.AConfigure;
import com.ybi.whoot.dataobjects.AFont;
import com.ybi.whoot.dataobjects.APosition;
import com.ybi.whoot.dataobjects.AVariaTexte;
import com.ybi.whoot.singleton.ASingletonCanvas;

public class ACanvas extends AppWidgetProvider
{

	public static final String URI_SCHEME = "ybi_widget";
	
	private static boolean isVisible = true;
	private static int batteryLevel = 0;

	
	public static void setBatteryLevel (int bl) 
	{
		batteryLevel = bl;
	}
	public static void setVisible (boolean vis)
	{
		isVisible = vis;
	}
	
	@Override
	public void onEnabled(Context context)
	{
		// This is only called once, regardless of the number of widgets of this
		// type
		Log.i("YBI", "onEnabled()");
		// recupere le widget id
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName thisWidget = new ComponentName(context, ACanvas.class);
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		// donne les ids au service qui les donnera au broadcast
		ACanvasService.setWidgetIds(appWidgetIds);
		Intent svc = new Intent(context, ACanvasService.class);
		context.startService(svc);
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.i("YBI", "ONREEIVE");

		try
		{

			ASingletonCanvas.getInstance().setContext(context);
			ASingletonCanvas.getInstance().initTranslations();
			ASingletonCanvas.getInstance().initSettings();
			ASingletonCanvas.getInstance().loadSettings();

			final String action = intent.getAction();
			if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action))
			{
				final int appWidgetId = intent.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
				if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID)
				{
					this.onDeleted(context, new int[]
					{ appWidgetId });
				}
			} else if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
			{
				if (!URI_SCHEME.equals(intent.getScheme()))
				{
					Log.i("YBI", "LAUNCHING SERVICE");

					final int[] appWidgetIds = intent.getExtras().getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);

					// donne les ids au service qui les donnera au broadcast
					ACanvasService.setWidgetIds(appWidgetIds);
					Intent svc = new Intent(context, ACanvasService.class);
					context.startService(svc);

				}
			}
		} catch (Exception e)
		{
			Log.e("YBI", "Probleme dans l'initialisation", e);
		}
		super.onReceive(context, intent);

	}



	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{

		try
		{
			ASingletonCanvas.getInstance().setContext(context);
			ASingletonCanvas.getInstance().initTranslations();
			ASingletonCanvas.getInstance().loadSettings();
//			ASingletonCanvas.getInstance().setContext(null);

			Log.i("YBI", "ON UPDATE!!!!");
			for (int appWidgetId : appWidgetIds)
			{
				Bitmap bitmap = ADrawWidget.drawWidget();

				RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
				views.setImageViewBitmap(R.id.ImageView01, bitmap);

				Intent intent = new Intent(context, AConfigure.class);
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				intent.setData(Uri.withAppendedPath(Uri.parse(URI_SCHEME
						+ "://widget/id/"), String.valueOf(appWidgetId)));
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);// PendingIntent.FLAG_UPDATE_CURRENT);

				views.setOnClickPendingIntent(R.id.ImageView01, pendingIntent);

				appWidgetManager.updateAppWidget(appWidgetId, views);
				// bitmap.recycle();

			}
		} catch (Exception e)
		{
			Log.e("YBI", "Probleme dans la mise a jour", e);
		}

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		Log.d("YBI", "onDelete()");
		Intent svc = new Intent(context, ACanvasService.class);
		context.stopService(svc);
		super.onDeleted(context, appWidgetIds);
	}

	
	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String titlePrefix)
	{
		if (isVisible)
			{
			Log.d("YBI", "updateAppWidget appWidgetId=" + appWidgetId
					+ " titlePrefix=" + titlePrefix);
	
			// petite verification
			ASingletonCanvas.getInstance().setContext(context);
			ASingletonCanvas.getInstance().initTranslations();
			ASingletonCanvas.getInstance().loadSettings();
			
			Bitmap bitmap = ADrawWidget.drawWidget();
	
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			views.setImageViewBitmap(R.id.ImageView01, bitmap);
			
			Intent intent = new Intent(context, AConfigure.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			intent.setData(Uri.withAppendedPath(Uri.parse(URI_SCHEME
					+ "://widget/id/"), String.valueOf(appWidgetId)));
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);// PendingIntent.FLAG_UPDATE_CURRENT);
	
			views.setOnClickPendingIntent(R.id.ImageView01, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		else
		{
			Log.d("YBI", "WIDGET IS SLEEPING NOTHING TO BE DONE" );
		}
		// bitmap.recycle();
	}
	
	
}
