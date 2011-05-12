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

public class ADecalagesActivity extends Activity implements OnClickListener
{

	// heure
	private Button btBDEH;
	private Button btBDEM;
	private Button btBDEO;
	private Button btBDES;

	// date
	private Button btBDESE;
	private Button btBDEMS;
	private Button btBDEJO;

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		btBDEH.setOnClickListener(null);
		btBDEM.setOnClickListener(null);
		btBDEO.setOnClickListener(null);
		btBDES.setOnClickListener(null);

		btBDESE.setOnClickListener(null);
		btBDEMS.setOnClickListener(null);
		btBDEJO.setOnClickListener(null);

		btBDEH = null;
		btBDEM = null;
		btBDEO = null;
		btBDES = null;

		btBDESE = null;
		btBDEMS = null;
		btBDEJO = null;

	}

	public static final String TEST_INTENT = "MyTestIntent";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("CheckStartActivity", "OnCreate");
		setContentView(R.layout.config_decalage);

		btBDEH = (Button) this.findViewById(R.id.BDEH);
		btBDEM = (Button) this.findViewById(R.id.BDEM);
		btBDEO = (Button) this.findViewById(R.id.BDEO);
		btBDES = (Button) this.findViewById(R.id.BDES);
		btBDEH.setOnClickListener(this);
		btBDEM.setOnClickListener(this);
		btBDEO.setOnClickListener(this);
		btBDES.setOnClickListener(this);

		btBDESE = (Button) this.findViewById(R.id.BDESE);
		btBDEMS = (Button) this.findViewById(R.id.BDEMS);
		btBDEJO = (Button) this.findViewById(R.id.BDEJO);

		btBDESE.setOnClickListener(this);
		btBDEMS.setOnClickListener(this);
		btBDEJO.setOnClickListener(this);

		btBDEH.setText(Utils.texteAndValue(btBDEH.getText(), ASingletonCanvas.getInstance().getDecalage("heuresDecalage").getLibelleTexte()));
		btBDEM.setText(Utils.texteAndValue(btBDEM.getText(), ASingletonCanvas.getInstance().getDecalage("minutesDecalage").getLibelleTexte()));
		btBDEO.setText(Utils.texteAndValue(btBDEO.getText(), ASingletonCanvas.getInstance().getDecalage("shadowDecalage").getLibelleShadow()));
		btBDES.setText(Utils.texteAndValue(btBDES.getText(), ASingletonCanvas.getInstance().getDecalage("sepDecalage").getLibelleTexte()));
		btBDESE.setText(Utils.texteAndValue(btBDESE.getText(), ASingletonCanvas.getInstance().getDecalage("semaineDecalage").getLibelleTexte()));
		btBDEMS.setText(Utils.texteAndValue(btBDEMS.getText(), ASingletonCanvas.getInstance().getDecalage("moisDecalage").getLibelleTexte()));
		btBDEJO.setText(Utils.texteAndValue(btBDEJO.getText(), ASingletonCanvas.getInstance().getDecalage("jourDecalage").getLibelleTexte()));

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
		if (v.getId() == R.id.BDEH)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_decalage);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("sepDecalage").getChoices(), ASingletonCanvas.getInstance().getDecalage("heuresDecalage").getItemPositionTexte(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("heuresDecalage").setItemTexte(item);
					dialog.dismiss();
					btBDEH.setText(Utils.texteAndValue(btBDEH.getText(), ASingletonCanvas.getInstance().getDecalage("heuresDecalage").getLibelleTexte()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		} else if (v.getId() == R.id.BDES)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_decalage);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("sepDecalage").getChoices(), ASingletonCanvas.getInstance().getDecalage("sepDecalage").getItemPositionTexte(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("sepDecalage").setItemTexte(item);
					dialog.dismiss();
					btBDES.setText(Utils.texteAndValue(btBDES.getText(), ASingletonCanvas.getInstance().getDecalage("sepDecalage").getLibelleTexte()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		} else if (v.getId() == R.id.BDEM)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_decalage);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("minutesDecalage").getChoices(), ASingletonCanvas.getInstance().getDecalage("minutesDecalage").getItemPositionTexte(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("minutesDecalage").setItemTexte(item);
					dialog.dismiss();
					btBDEM.setText(Utils.texteAndValue(btBDEM.getText(), ASingletonCanvas.getInstance().getDecalage("minutesDecalage").getLibelleTexte()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		} else if (v.getId() == R.id.BDEMS)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_decalage);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("moisDecalage").getChoices(), ASingletonCanvas.getInstance().getDecalage("moisDecalage").getItemPositionTexte(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("moisDecalage").setItemTexte(item);
					dialog.dismiss();
					btBDEMS.setText(Utils.texteAndValue(btBDEMS.getText(), ASingletonCanvas.getInstance().getDecalage("moisDecalage").getLibelleTexte()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BDESE)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_decalage);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("semaineDecalage").getChoices(), ASingletonCanvas.getInstance().getDecalage("semaineDecalage").getItemPositionTexte(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("semaineDecalage").setItemTexte(item);
					dialog.dismiss();
					btBDESE.setText(Utils.texteAndValue(btBDESE.getText(), ASingletonCanvas.getInstance().getDecalage("semaineDecalage").getLibelleTexte()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		} else if (v.getId() == R.id.BDEJO)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_decalage);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("jourDecalage").getChoices(), ASingletonCanvas.getInstance().getDecalage("jourDecalage").getItemPositionTexte(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("jourDecalage").setItemTexte(item);
					dialog.dismiss();
					btBDEJO.setText(Utils.texteAndValue(btBDEJO.getText(), ASingletonCanvas.getInstance().getDecalage("jourDecalage").getLibelleTexte()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BDEO)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_direction);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getDecalage("shadowDecalage").getChoicesShadow(), ASingletonCanvas.getInstance().getDecalage("shadowDecalage").getItemPositionShadow(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getDecalage("shadowDecalage").setItemShadow(item);
					dialog.dismiss();
					btBDEO.setText(Utils.texteAndValue(btBDEO.getText(), ASingletonCanvas.getInstance().getDecalage("shadowDecalage").getLibelleShadow()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} 
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
