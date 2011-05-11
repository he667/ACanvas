package com.ybi.whoot.activities;

import com.ybi.whoot.singleton.ASingletonCanvas;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ybi.whoot.utils.Utils;
import com.ybi.whoot.R;

public class ATailleActivity extends Activity implements OnClickListener
{
	// heure
	private Button btBTAH;
	private Button btBTAM;
	private Button btBTAS;

	// ombre
	private Button btBTAO;

	// interligne
	private Button btBTAI;

	// date
	private Button btBTASE;
	private Button btBTAMS;
	private Button btBTAJO;

	private Button btBVALIDER;

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		btBTAH.setOnClickListener(null);
		btBTAM.setOnClickListener(null);
		btBTAO.setOnClickListener(null);
		btBTAS.setOnClickListener(null);
		btBTAI.setOnClickListener(null);

		btBTASE.setOnClickListener(null);
		btBTAMS.setOnClickListener(null);
		btBTAJO.setOnClickListener(null);

		btBVALIDER.setOnClickListener(null);

		btBTAH = null;
		btBTAM = null;
		btBTAO = null;
		btBTAS = null;
		btBTAI = null;
		btBTASE = null;
		btBTAMS = null;
		btBTAJO = null;

		btBVALIDER = null;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("CheckStartActivity", "OnCreate");
		setContentView(R.layout.config_taille);
		btBTAH = (Button) this.findViewById(R.id.BTAH);
		btBTAM = (Button) this.findViewById(R.id.BTAM);
		btBTAO = (Button) this.findViewById(R.id.BTAO);
		btBTAS = (Button) this.findViewById(R.id.BTAS);
		btBTAI = (Button) this.findViewById(R.id.BTAI);
		btBTAH.setOnClickListener(this);
		btBTAM.setOnClickListener(this);
		btBTAO.setOnClickListener(this);
		btBTAS.setOnClickListener(this);
		btBTAI.setOnClickListener(this);

		btBTASE = (Button) this.findViewById(R.id.BTASE);
		btBTAMS = (Button) this.findViewById(R.id.BTAMS);
		btBTAJO = (Button) this.findViewById(R.id.BTAJO);
		btBTASE.setOnClickListener(this);
		btBTAMS.setOnClickListener(this);
		btBTAJO.setOnClickListener(this);

		btBVALIDER = (Button) this.findViewById(R.id.BVALIDER);
		btBVALIDER.setOnClickListener(this);

		btBTAH.setText(Utils.texteAndValue(btBTAH.getText(), ASingletonCanvas.getInstance().getTaille("heuresTaille").getLibelle()));
		btBTAM.setText(Utils.texteAndValue(btBTAM.getText(), ASingletonCanvas.getInstance().getTaille("minutesTaille").getLibelle()));
		btBTAO.setText(Utils.texteAndValue(btBTAO.getText(), ASingletonCanvas.getInstance().getTaille("shadowTaille").getLibelle()));
		btBTAS.setText(Utils.texteAndValue(btBTAS.getText(), ASingletonCanvas.getInstance().getTaille("sepTaille").getLibelle()));
		btBTAI.setText(Utils.texteAndValue(btBTAI.getText(), ASingletonCanvas.getInstance().getTaille("interligneTaille").getLibelle()));
		btBTASE.setText(Utils.texteAndValue(btBTASE.getText(), ASingletonCanvas.getInstance().getTaille("semaineTaille").getLibelle()));
		btBTAMS.setText(Utils.texteAndValue(btBTAMS.getText(), ASingletonCanvas.getInstance().getTaille("moisTaille").getLibelle()));
		btBTAJO.setText(Utils.texteAndValue(btBTAJO.getText(), ASingletonCanvas.getInstance().getTaille("jourTaille").getLibelle()));

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
		if (v.getId() == R.id.BTAH)
		{

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_taille);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("heuresTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("heuresTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("heuresTaille").setItemTexte(item);
					dialog.dismiss();
					btBTAH.setText(Utils.texteAndValue(btBTAH.getText(), ASingletonCanvas.getInstance().getTaille("heuresTaille").getLibelle()));

				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTAS)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_taille);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("sepTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("sepTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("sepTaille").setItemTexte(item);
					dialog.dismiss();
					btBTAS.setText(Utils.texteAndValue(btBTAS.getText(), ASingletonCanvas.getInstance().getTaille("sepTaille").getLibelle()));

				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTAM)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_taille);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("minutesTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("minutesTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("minutesTaille").setItemTexte(item);
					dialog.dismiss();
					btBTAM.setText(Utils.texteAndValue(btBTAM.getText(), ASingletonCanvas.getInstance().getTaille("minutesTaille").getLibelle()));

				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTAO)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choississez la taille de l'ombre");
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("shadowTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("shadowTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("shadowTaille").setItemShadow(item);
					dialog.dismiss();
					btBTAO.setText(Utils.texteAndValue(btBTAO.getText(), ASingletonCanvas.getInstance().getTaille("shadowTaille").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTAI)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choississez l'espacement entre les lignes");
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("interligneTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("interligneTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("interligneTaille").setItemTexte(item);
					dialog.dismiss();
					btBTAI.setText(Utils.texteAndValue(btBTAI.getText(), ASingletonCanvas.getInstance().getTaille("interligneTaille").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTAMS)
		{

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_taille);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("moisTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("moisTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("moisTaille").setItemTexte(item);
					dialog.dismiss();
					btBTAMS.setText(Utils.texteAndValue(btBTAMS.getText(), ASingletonCanvas.getInstance().getTaille("moisTaille").getLibelle()));

				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTASE)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_taille);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("semaineTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("semaineTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("semaineTaille").setItemTexte(item);
					dialog.dismiss();
					btBTASE.setText(Utils.texteAndValue(btBTASE.getText(), ASingletonCanvas.getInstance().getTaille("semaineTaille").getLibelle()));

				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTAJO)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_taille);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTaille("jourTaille").getChoices(), ASingletonCanvas.getInstance().getTaille("jourTaille").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTaille("jourTaille").setItemTexte(item);
					dialog.dismiss();
					btBTAJO.setText(Utils.texteAndValue(btBTAJO.getText(), ASingletonCanvas.getInstance().getTaille("jourTaille").getLibelle()));

				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		} else if (v.getId() == R.id.BVALIDER)
		{

			finish();
		}

	}

}
