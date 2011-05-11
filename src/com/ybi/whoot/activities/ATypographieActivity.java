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

public class ATypographieActivity extends Activity implements OnClickListener
{

	private Button btBTYH;
	private Button btBTYM;
	private Button btBTYS;

	private Button btBTYSE;
	private Button btBTYMS;
	private Button btBTYJO;

	private Button btBVALIDER;

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		btBTYH.setOnClickListener(null);
		btBTYM.setOnClickListener(null);
		btBTYS.setOnClickListener(null);
		btBTYSE.setOnClickListener(null);
		btBTYMS.setOnClickListener(null);
		btBTYJO.setOnClickListener(null);
		btBTYH = null;
		btBTYM = null;
		btBTYS = null;
		btBTYSE = null;
		btBTYMS = null;
		btBTYJO = null;

		btBVALIDER = null;

	}

	public static final String TEST_INTENT = "MyTestIntent";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("CheckStartActivity", "OnCreate");
		setContentView(R.layout.config_typographie);

		btBTYH = (Button) this.findViewById(R.id.BTYH);
		btBTYM = (Button) this.findViewById(R.id.BTYM);
		btBTYS = (Button) this.findViewById(R.id.BTYS);
		btBTYH.setOnClickListener(this);
		btBTYM.setOnClickListener(this);
		btBTYS.setOnClickListener(this);

		btBTYSE = (Button) this.findViewById(R.id.BTYSE);
		btBTYMS = (Button) this.findViewById(R.id.BTYMS);
		btBTYJO = (Button) this.findViewById(R.id.BTYJO);
		btBTYSE.setOnClickListener(this);
		btBTYMS.setOnClickListener(this);
		btBTYJO.setOnClickListener(this);

		btBVALIDER = (Button) this.findViewById(R.id.BVALIDER);
		btBVALIDER.setOnClickListener(this);

		btBTYH.setText(Utils.texteAndValue(btBTYH.getText(), ASingletonCanvas.getInstance().getTypo("heuresTypo").getLibelle()));
		btBTYM.setText(Utils.texteAndValue(btBTYM.getText(), ASingletonCanvas.getInstance().getTypo("minutesTypo").getLibelle()));
		btBTYS.setText(Utils.texteAndValue(btBTYS.getText(), ASingletonCanvas.getInstance().getTypo("sepTypo").getLibelle()));
		btBTYSE.setText(Utils.texteAndValue(btBTYSE.getText(), ASingletonCanvas.getInstance().getTypo("semaineTypo").getLibelle()));
		btBTYMS.setText(Utils.texteAndValue(btBTYMS.getText(), ASingletonCanvas.getInstance().getTypo("moisTypo").getLibelle()));
		btBTYJO.setText(Utils.texteAndValue(btBTYJO.getText(), ASingletonCanvas.getInstance().getTypo("jourTypo").getLibelle()));

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
		if (v.getId() == R.id.BTYH)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_typo);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTypo("heuresTypo").getChoices(), ASingletonCanvas.getInstance().getTypo("heuresTypo").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTypo("heuresTypo").setItem(item);
					dialog.dismiss();
					btBTYH.setText(Utils.texteAndValue(btBTYH.getText(), ASingletonCanvas.getInstance().getTypo("heuresTypo").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTYS)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_typo);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTypo("sepTypo").getChoices(), ASingletonCanvas.getInstance().getTypo("sepTypo").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTypo("sepTypo").setItem(item);
					dialog.dismiss();
					btBTYS.setText(Utils.texteAndValue(btBTYS.getText(), ASingletonCanvas.getInstance().getTypo("sepTypo").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTYM)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_typo);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTypo("minutesTypo").getChoices(), ASingletonCanvas.getInstance().getTypo("minutesTypo").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTypo("minutesTypo").setItem(item);
					dialog.dismiss();
					btBTYM.setText(Utils.texteAndValue(btBTYM.getText(), ASingletonCanvas.getInstance().getTypo("minutesTypo").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTYMS)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_typo);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTypo("moisTypo").getChoices(), ASingletonCanvas.getInstance().getTypo("moisTypo").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTypo("moisTypo").setItem(item);
					dialog.dismiss();
					btBTYMS.setText(Utils.texteAndValue(btBTYMS.getText(), ASingletonCanvas.getInstance().getTypo("moisTypo").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTYSE)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_typo);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTypo("semaineTypo").getChoices(), ASingletonCanvas.getInstance().getTypo("semaineTypo").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTypo("semaineTypo").setItem(item);
					dialog.dismiss();
					btBTYSE.setText(Utils.texteAndValue(btBTYSE.getText(), ASingletonCanvas.getInstance().getTypo("semaineTypo").getLibelle()));
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		} else if (v.getId() == R.id.BTYJO)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.choisir_typo);
			builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getTypo("jourTypo").getChoices(), ASingletonCanvas.getInstance().getTypo("jourTypo").getItemPosition(), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					ASingletonCanvas.getInstance().getTypo("jourTypo").setItem(item);
					dialog.dismiss();
					btBTYJO.setText(Utils.texteAndValue(btBTYJO.getText(), ASingletonCanvas.getInstance().getTypo("jourTypo").getLibelle()));
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
