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
			ACanvas.setVisible (false);
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
		{
			ACanvas.setVisible (true);
			tick(context);
		}
		else if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
		{
			int rawlevel = intent.getIntExtra("level", -1);
            int scale = intent.getIntExtra("scale", -1);
            int level = -1;
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }
            ACanvas.setBatteryLevel (level);
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