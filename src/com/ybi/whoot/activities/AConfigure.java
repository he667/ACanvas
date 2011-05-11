package com.ybi.whoot.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

import com.ybi.whoot.dialogs.AAnglePicker;
import com.ybi.whoot.dialogs.AAnglePickerCallBack;
import com.ybi.whoot.R;
import com.ybi.whoot.singleton.ASingletonCanvas;
import com.ybi.whoot.widget.ACanvas;

public class AConfigure extends Activity implements OnClickListener,
		AAnglePickerCallBack
{
	private static final int PICK_IMAGE = 100;

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	public static final String TEST_INTENT = "MyTestIntent";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("CheckStartActivity", "OnCreate");
		setContentView(R.layout.config_dashboard);
	}

	/**
	 * Appelée lorsque l’ activité termine son cycle visible. Sauvez les
	 * données importantes.
	 */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * Appelée après onCreate. Les données sont rechargées et l’interface
	 * utilisateur. est restaurée dans le bon état.
	 */
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onClick(View v)
	{

	}

	public void onCouleurs(View v)
	{
		Intent couleurIntent = new Intent(this, ACouleurActivity.class);
		this.startActivity(couleurIntent);
	}

	public void onTypographie(View v)
	{
		Intent couleurIntent = new Intent(this, ATypographieActivity.class);
		this.startActivity(couleurIntent);

	}

	public void onTaille(View v)
	{
		Intent couleurIntent = new Intent(this, ATailleActivity.class);
		this.startActivity(couleurIntent);

	}

	public void onDecalages(View v)
	{
		Intent couleurIntent = new Intent(this, ADecalagesActivity.class);
		this.startActivity(couleurIntent);

	}

	public void onVariations(View v)
	{
		Intent couleurIntent = new Intent(this, AVariationsActivity.class);
		this.startActivity(couleurIntent);

	}

	public void on3D(View v)
	{
		AAnglePicker acp = new AAnglePicker(this, ASingletonCanvas.getInstance().getAngle());
		acp.register(this);
		acp.show();

	}

	public void onFont(View v)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.choisir_font);
		builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getFont().getChoices(), ASingletonCanvas.getInstance().getFont().getItemPosition(), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				ASingletonCanvas.getInstance().getFont().setItem(item);
				dialog.dismiss();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void onReinitialiser(View v)
	{
		// reinitialisation
		ASingletonCanvas.getInstance().initSettings();
		ASingletonCanvas.getInstance().setContext(this);
		ASingletonCanvas.getInstance().saveSettings();
		//ASingletonCanvas.getInstance().setContext(null);

		int mAppWidgetId;
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null)
		{
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

			Log.i("YBI", "bouton ok pressed, try to pass back a value to the widget");
			Intent resultValue = new Intent();
			resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
			ACanvas.updateAppWidget(this, appWidgetManager, mAppWidgetId, null);

			setResult(RESULT_OK, resultValue);

		}
		finish();

	}

	@Override
	public void angleSelected(int rotx, int roty)
	{

		try
		{
			ASingletonCanvas.getInstance().getAngle().setRotx(rotx);
			ASingletonCanvas.getInstance().getAngle().setRoty(roty);
		} catch (Exception e)
		{
			Log.e("YBI", "Something gone wrong when angle did get selected", e);

		}

	}

	public void onBackground(View v)
	{
		Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		photoPickerIntent.setType("image/*");

		this.startActivityForResult(photoPickerIntent, PICK_IMAGE);

	}

	public void onPosition(View v)
	{
		Intent positionIntent = new Intent(this, APositionActivity.class);
		this.startActivity(positionIntent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode)
		{
		case PICK_IMAGE: // our request code
			if (resultCode == Activity.RESULT_OK)
			{
				Uri imageUri = data.getData(); // get the image URI
				ASingletonCanvas.getInstance().getImage().setImage(imageUri);
				Log.d("YBI", "An Image has been selected " + imageUri.toString());
				
			}
		}

	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		// sauvegarde
		ASingletonCanvas.getInstance().setContext(this);
		ASingletonCanvas.getInstance().saveSettings();
		//ASingletonCanvas.getInstance().setContext(null);

		int mAppWidgetId;
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null)
		{
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

			Log.i("YBI", "bouton ok pressed, try to pass back a value to the widget");
			Intent resultValue = new Intent();
			resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
			// SharedPreferences.Editor prefs =
			// this.getSharedPreferences("com.ybi.cfg", 0).edit();
			// prefs.putInt("HeuresColor", heuresColor);
			// prefs.putInt("SepColor", sepColor);
			// prefs.putInt("MinutesColor", minutesColor);
			// prefs.commit();

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
			ACanvas.updateAppWidget(this, appWidgetManager, mAppWidgetId, null);

			setResult(RESULT_OK, resultValue);

		}

		finish();
	}
}
