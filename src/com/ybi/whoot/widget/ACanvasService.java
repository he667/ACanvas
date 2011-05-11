package com.ybi.whoot.widget;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ACanvasService extends Service
{
	static final String LOGGING_TAG = "YBI";
	static int[] appWidgetIds;
	private static ACanvasBroadcastReceiver acb = null;
	
	
	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
		this.setForeground(true);
		Log.v(LOGGING_TAG, "DemoService.onStart()");
		if (acb ==null)
		{
			acb = new ACanvasBroadcastReceiver();
		}
		else
		{
			try
			{
				unregisterReceiver(acb);
			} catch (Exception e)
			{
				Log.w("YBI", "failed to unregister previous receiver... Maybe this is the first time or am did not destroy it completely...");
			}
		}
		IntentFilter inf = new IntentFilter();
		inf.addAction(Intent.ACTION_TIME_TICK);
		inf.addAction(Intent.ACTION_SCREEN_OFF);
		inf.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(acb, inf);
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		this.setForeground(true);
		Log.v(LOGGING_TAG, "DemoService.onStart()");
		if (acb ==null)
		{
			acb = new ACanvasBroadcastReceiver();
		}
		else
		{
			try
			{
				unregisterReceiver(acb);
			} catch (Exception e)
			{
				Log.w("YBI", "failed to unregister previous receiver... Maybe this is the first time or am did not destroy it completely...");
			}
		}
		IntentFilter inf = new IntentFilter();
		inf.addAction(Intent.ACTION_TIME_TICK);
		inf.addAction(Intent.ACTION_SCREEN_OFF);
		inf.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(acb, inf);
	}

	public static void setWidgetIds(int[] tAppWidgetIds)
	{
		appWidgetIds = tAppWidgetIds;

	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if (acb!=null)
		{
		unregisterReceiver(acb);
		}
		Log.v(LOGGING_TAG, "DemoService.onDestroy()");
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
