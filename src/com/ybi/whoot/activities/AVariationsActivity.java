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
import android.widget.EditText;

import com.ybi.whoot.dataobjects.AVariaSeparateur;
import com.ybi.whoot.utils.Utils;
import com.ybi.whoot.R;

public class AVariationsActivity extends Activity implements OnClickListener
{
	// heure
	private Button btBVAH;
	private Button btBVAM;
	private Button btBVAS;

	// date
	private Button btBVASE;
	private Button btBVAMS;
	private Button btBVAJO;

	private Button btBVALIDER;

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		btBVAH.setOnClickListener(null);
		btBVAM.setOnClickListener(null);
		btBVAS.setOnClickListener(null);

		btBVASE.setOnClickListener(null);
		btBVAMS.setOnClickListener(null);
		btBVAJO.setOnClickListener(null);

		btBVALIDER.setOnClickListener(null);

		btBVAH = null;
		btBVAM = null;
		btBVAS = null;

		btBVASE = null;
		btBVAMS = null;
		btBVAJO = null;

		btBVALIDER = null;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("CheckStartActivity", "OnCreate");
		setContentView(R.layout.config_variations);

		btBVAH = (Button) this.findViewById(R.id.BVAH);
		btBVAM = (Button) this.findViewById(R.id.BVAM);
		btBVAS = (Button) this.findViewById(R.id.BVAS);
		btBVAH.setOnClickListener(this);
		btBVAM.setOnClickListener(this);
		btBVAS.setOnClickListener(this);

		btBVASE = (Button) this.findViewById(R.id.BVASE);
		btBVAMS = (Button) this.findViewById(R.id.BVAMS);
		btBVAJO = (Button) this.findViewById(R.id.BVAJO);

		btBVASE.setOnClickListener(this);
		btBVAMS.setOnClickListener(this);
		btBVAJO.setOnClickListener(this);

		btBVALIDER = (Button) this.findViewById(R.id.BVALIDER);
		btBVALIDER.setOnClickListener(this);

		btBVAH.setText(Utils.texteAndValue(btBVAH.getText(), ASingletonCanvas.getInstance().getType("heuresType").getLibelle()));
		btBVAM.setText(Utils.texteAndValue(btBVAM.getText(), ASingletonCanvas.getInstance().getType("minutesType").getLibelle()));
		btBVAS.setText(Utils.texteAndValue(btBVAS.getText(), ASingletonCanvas.getInstance().getType("sepType").getLibelle()));
		btBVASE.setText(Utils.texteAndValue(btBVASE.getText(), ASingletonCanvas.getInstance().getType("semaineType").getLibelle()));
		btBVAMS.setText(Utils.texteAndValue(btBVAMS.getText(), ASingletonCanvas.getInstance().getType("moisType").getLibelle()));
		btBVAJO.setText(Utils.texteAndValue(btBVAJO.getText(), ASingletonCanvas.getInstance().getType("jourType").getLibelle()));

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
		if (v.getId() == R.id.BVAH)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_varia);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getType("heuresType").getChoices(), ASingletonCanvas.getInstance().getType("heuresType").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getType("heuresType").setItemPosition(item);
					dialog.dismiss();
					btBVAH.setText(Utils.texteAndValue(btBVAH.getText(), ASingletonCanvas.getInstance().getType("heuresType").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		} else if (v.getId() == R.id.BVAM)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_varia);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getType("minutesType").getChoices(), ASingletonCanvas.getInstance().getType("minutesType").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getType("minutesType").setItemPosition(item);
					dialog.dismiss();
					btBVAM.setText(Utils.texteAndValue(btBVAM.getText(), ASingletonCanvas.getInstance().getType("minutesType").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BVAS)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_varia);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getType("sepType").getChoices(), ASingletonCanvas.getInstance().getType("sepType").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getType("sepType").setItemPosition(item);
					// cas standard
					if (ASingletonCanvas.getInstance().getType("sepType").getType() == AVariaSeparateur.CUSTOM)
					{
						dialog.dismiss();
						// dialog pour la customatisation du texte
						AlertDialog.Builder tbuilder = new AlertDialog.Builder(AVariationsActivity.this);
						tbuilder.setTitle(R.string.personnalise);  
						tbuilder.setMessage(R.string.choisir_custom);  
						   
						 // Set an EditText view to get user input   
						 final EditText input = new EditText(AVariationsActivity.this);  
						 tbuilder.setView(input);  
						   
						 tbuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {  
						 public void onClick(DialogInterface dialog, int whichButton) {  
						   String value = input.getText().toString();  
						   
						   ASingletonCanvas.getInstance().getType("sepType").setCustom(value);  
						   btBVAS.setText(Utils.texteAndValue(btBVAS.getText(), ASingletonCanvas.getInstance().getType("sepType").getLibelle()));
						   }  
						 });  
						   
						 tbuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
						   public void onClick(DialogInterface dialog, int whichButton) {  
						     // Canceled.  
						   }  
						 });  
						   
						 tbuilder.show(); 
						
					} else
					{
						dialog.dismiss();
						btBVAS.setText(Utils.texteAndValue(btBVAS.getText(), ASingletonCanvas.getInstance().getType("sepType").getLibelle()));
					}
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BVAMS)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_varia);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getType("moisType").getChoices(), ASingletonCanvas.getInstance().getType("moisType").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getType("moisType").setItemPosition(item);
					dialog.dismiss();
					btBVAMS.setText(Utils.texteAndValue(btBVAMS.getText(), ASingletonCanvas.getInstance().getType("moisType").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BVASE)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_varia);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getType("semaineType").getChoices(), ASingletonCanvas.getInstance().getType("semaineType").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getType("semaineType").setItemPosition(item);
					dialog.dismiss();
					btBVASE.setText(Utils.texteAndValue(btBVASE.getText(), ASingletonCanvas.getInstance().getType("semaineType").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BVAJO)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_varia);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getType("jourType").getChoices(), ASingletonCanvas.getInstance().getType("jourType").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getType("jourType").setItemPosition(item);
					dialog.dismiss();
					btBVAJO.setText(Utils.texteAndValue(btBVAJO.getText(), ASingletonCanvas.getInstance().getType("jourType").getLibelle()));
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
