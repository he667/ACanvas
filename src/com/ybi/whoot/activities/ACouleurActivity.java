package com.ybi.whoot.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ybi.whoot.dialogs.AColorPicker;
import com.ybi.whoot.dialogs.AColorPickerCallBack;
import com.ybi.whoot.singleton.ASingletonCanvas;
import com.ybi.whoot.utils.Utils;
import com.ybi.whoot.R;

public class ACouleurActivity extends Activity implements OnClickListener,
		AColorPickerCallBack
{

	// heure
	private Button btBCOH;
	private Button btBCOM;
	private Button btBCOO;
	private Button btBCOS;
	private Button btBCOF;

	// date
	private Button btBCOSE;
	private Button btBCOMS;
	private Button btBCOJO;

	private Button btBVALIDER;

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		btBCOH.setOnClickListener(null);
		btBCOM.setOnClickListener(null);
		btBCOO.setOnClickListener(null);
		btBCOS.setOnClickListener(null);
		btBCOF.setOnClickListener(null);

		btBVALIDER.setOnClickListener(null);

		btBCOH = null;
		btBCOM = null;
		btBCOO = null;
		btBCOS = null;
		btBCOF = null;

		btBCOSE = null;
		btBCOMS = null;
		btBCOJO = null;

		btBVALIDER = null;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.d("CheckStartActivity", "OnCreate");
		setContentView(R.layout.config_couleur);

		btBCOH = (Button) this.findViewById(R.id.BCOH);
		btBCOM = (Button) this.findViewById(R.id.BCOM);
		btBCOO = (Button) this.findViewById(R.id.BCOO);
		btBCOS = (Button) this.findViewById(R.id.BCOS);
		btBCOF = (Button) this.findViewById(R.id.BCOF);
		btBCOH.setOnClickListener(this);
		btBCOM.setOnClickListener(this);
		btBCOO.setOnClickListener(this);
		btBCOS.setOnClickListener(this);
		btBCOF.setOnClickListener(this);

		btBCOSE = (Button) this.findViewById(R.id.BCOSE);
		btBCOMS = (Button) this.findViewById(R.id.BCOMS);
		btBCOJO = (Button) this.findViewById(R.id.BCOJO);
		btBCOSE.setOnClickListener(this);
		btBCOMS.setOnClickListener(this);
		btBCOJO.setOnClickListener(this);

		btBVALIDER = (Button) this.findViewById(R.id.BVALIDER);
		btBVALIDER.setOnClickListener(this);

		btBCOH.setText(Utils.texteAndColor(btBCOH.getText(), ASingletonCanvas.getInstance().getColor("heuresColor").getColor()));
		btBCOM.setText(Utils.texteAndColor(btBCOM.getText(), ASingletonCanvas.getInstance().getColor("minutesColor").getColor()));
		btBCOO.setText(Utils.texteAndColor(btBCOO.getText(), ASingletonCanvas.getInstance().getColor("shadowColor").getColor()));
		btBCOS.setText(Utils.texteAndColor(btBCOS.getText(), ASingletonCanvas.getInstance().getColor("sepColor").getColor()));
		btBCOF.setText(Utils.texteAndColor(btBCOF.getText(), ASingletonCanvas.getInstance().getColor("backgroundColor").getColor()));
		btBCOSE.setText(Utils.texteAndColor(btBCOSE.getText(), ASingletonCanvas.getInstance().getColor("semaineColor").getColor()));
		btBCOMS.setText(Utils.texteAndColor(btBCOMS.getText(), ASingletonCanvas.getInstance().getColor("moisColor").getColor()));
		btBCOJO.setText(Utils.texteAndColor(btBCOJO.getText(), ASingletonCanvas.getInstance().getColor("jourColor").getColor()));

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

		if (v.getId() == R.id.BCOH)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("heuresColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOS)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("sepColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOM)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("minutesColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOMS)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("moisColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOSE)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("semaineColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOJO)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("jourColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOO)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("shadowColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BCOF)
		{
			AColorPicker acp = new AColorPicker(this, ASingletonCanvas.getInstance().getColor("backgroundColor"));
			acp.register(this);
			acp.show();
		} else if (v.getId() == R.id.BVALIDER)
		{
			finish();
		}

	}

	@Override
	public void colorSelected()
	{
		btBCOH.setText(Utils.texteAndColor(btBCOH.getText(), ASingletonCanvas.getInstance().getColor("heuresColor").getColor()));
		btBCOS.setText(Utils.texteAndColor(btBCOS.getText(), ASingletonCanvas.getInstance().getColor("sepColor").getColor()));
		btBCOM.setText(Utils.texteAndColor(btBCOM.getText(), ASingletonCanvas.getInstance().getColor("minutesColor").getColor()));
		btBCOMS.setText(Utils.texteAndColor(btBCOMS.getText(), ASingletonCanvas.getInstance().getColor("moisColor").getColor()));
		btBCOSE.setText(Utils.texteAndColor(btBCOSE.getText(), ASingletonCanvas.getInstance().getColor("semaineColor").getColor()));
		btBCOJO.setText(Utils.texteAndColor(btBCOJO.getText(), ASingletonCanvas.getInstance().getColor("jourColor").getColor()));
		btBCOO.setText(Utils.texteAndColor(btBCOO.getText(), ASingletonCanvas.getInstance().getColor("shadowColor").getColor()));
		btBCOF.setText(Utils.texteAndColor(btBCOF.getText(), ASingletonCanvas.getInstance().getColor("backgroundColor").getColor()));
	}
}
