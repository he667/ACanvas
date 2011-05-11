package com.ybi.whoot.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ACanvasBroadcastReceiver extends BroadcastReceiver
{


	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.d("YBI", "RECEIVED SOMETHING");
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)
				|| intent.getAction().equals(Intent.ACTION_TIME_CHANGED))
		{
			Log.d("YBI", "RECEIVED TIME TICK!!!!!!!!");
			tick(context);

		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
		{
			ACanvas.isVisible = false;
		}
		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
		{
			ACanvas.isVisible = true;
			tick(context);
		}

	}

	private void tick(Context context)
	{
		try
		{
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			ComponentName thisWidget = new ComponentName(context, ACanvas.class);
			int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

			if ((appWidgetIds != null) && (appWidgetIds.length > 0))
				for (int appWidgetId : appWidgetIds)
				{
					Log.i("YBI", "updating widget from time tick");
					// AppWidgetManager appWidgetManager =
					// AppWidgetManager.getInstance(context);
					ACanvas.updateAppWidget(context, appWidgetManager, appWidgetId, null);
				}
		} catch (Exception e)
		{
			Log.e("YBI", "Something wrong with the launch of the widget update from the broadcast", e);
		}
	}
};