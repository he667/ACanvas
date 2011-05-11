package com.ybi.whoot.dialogs;

import com.ybi.whoot.R;
import com.ybi.whoot.dataobjects.AColor;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class AColorPicker extends Dialog implements OnClickListener
{

	private int colorRed;
	private int colorGreen;
	private int colorBlue;
	private int alpha;

	private AColorPickerRender acpr;
	private AColorPickerView acpvr;
	private AColorPickerView acpvv;
	private AColorPickerView acpvb;
	private AColorPickerView acpva;

	private AColor originalColor;

	private AColorPickerCallBack apcb;

	public AColorPicker(Context context, AColor aColor)
	{
		super(context);
		if (aColor != null)
		{
			originalColor = aColor;
			this.colorRed = Color.red(aColor.getColor());
			this.colorGreen = Color.green(aColor.getColor());
			this.colorBlue = Color.blue(aColor.getColor());
			this.alpha = Color.alpha(aColor.getColor());
		} else
		{
			this.colorRed = 255;
			this.colorGreen = 255;
			this.colorBlue = 255;
			this.alpha = 255;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("CheckStartActivity", "OnCreate");
		setTitle("Choississez votre Couleur");

		LinearLayout ll = new LinearLayout(this.getContext());
		ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER_HORIZONTAL);

		LinearLayout rrll = new LinearLayout(this.getContext());
		rrll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		rrll.setOrientation(LinearLayout.HORIZONTAL);
		rrll.setGravity(Gravity.CENTER_HORIZONTAL);

		acpr = new AColorPickerRender(this.getContext());
		rrll.addView(acpr);
		ll.addView(rrll);

		LinearLayout rll = new LinearLayout(this.getContext());
		rll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		rll.setOrientation(LinearLayout.HORIZONTAL);
		rll.setGravity(Gravity.CENTER_HORIZONTAL);

		acpvr = new AColorPickerView(this.getContext(), AColorPickerView.COLOR_RED, this.colorRed, acpr);
		rll.addView(acpvr);
		ll.addView(rll);

		LinearLayout vll = new LinearLayout(this.getContext());
		vll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		vll.setOrientation(LinearLayout.HORIZONTAL);
		vll.setGravity(Gravity.CENTER_HORIZONTAL);

		acpvv = new AColorPickerView(this.getContext(), AColorPickerView.COLOR_GREEN, this.colorGreen, acpr);
		vll.addView(acpvv);
		ll.addView(vll);

		LinearLayout bll = new LinearLayout(this.getContext());
		bll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		bll.setOrientation(LinearLayout.HORIZONTAL);
		bll.setGravity(Gravity.CENTER_HORIZONTAL);

		acpvb = new AColorPickerView(this.getContext(), AColorPickerView.COLOR_BLUE, this.colorBlue, acpr);
		bll.addView(acpvb);
		ll.addView(bll);

		LinearLayout all = new LinearLayout(this.getContext());
		all.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		all.setOrientation(LinearLayout.HORIZONTAL);
		all.setGravity(Gravity.CENTER_HORIZONTAL);

		acpva = new AColorPickerView(this.getContext(), AColorPickerView.ALPHA, this.alpha, acpr);
		all.addView(acpva);
		ll.addView(all);

		LinearLayout sll = new LinearLayout(this.getContext());
		sll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
		sll.setOrientation(LinearLayout.HORIZONTAL);
		sll.setGravity(Gravity.CENTER_HORIZONTAL);

		Button btok = new Button(this.getContext());
		btok.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btok.setGravity(Gravity.CENTER_HORIZONTAL);
		btok.setText("Valider");
		btok.setId(R.id.CPButtonOk);
		btok.setOnClickListener(this);
		sll.addView(btok);

		Button btcancel = new Button(this.getContext());
		btcancel.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btcancel.setGravity(Gravity.CENTER_HORIZONTAL);
		btcancel.setText("Annuler");
		btcancel.setId(R.id.CPButtonCancel);
		btcancel.setOnClickListener(this);
		sll.addView(btcancel);

		ll.addView(sll);

		// Button okButton = (Button) findViewById(R.id.CPButtonOk);
		// okButton.setOnClickListener(this);
		// Button cancelButton = (Button) findViewById(R.id.CPButtonCancel);
		// cancelButton.setOnClickListener(this);

		setContentView(ll);
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.CPButtonOk)
		{
			colorRed = acpvr.getPositionValue();
			colorGreen = acpvv.getPositionValue();
			colorBlue = acpvb.getPositionValue();
			alpha = acpva.getPositionValue();
			Log.d("YBI", "colorRed " + colorRed);
			Log.d("YBI", "colorGreen " + colorGreen);
			Log.d("YBI", "colorBlue " + colorBlue);
			Log.d("YBI", "alpha " + alpha);
			originalColor.setColor(alpha, colorRed, colorGreen, colorBlue);
			apcb.colorSelected();
			dismiss();
		}
		if (v.getId() == R.id.CPButtonCancel)
		{
			dismiss();
		}
	}

	public int getReturnColor()
	{
		Log.d("YBI", "retunr color "
				+ Color.argb(alpha, colorRed, colorGreen, colorBlue));
		return Color.argb(alpha, colorRed, colorGreen, colorBlue);
	}

	public void register(AColorPickerCallBack apcb)
	{
		this.apcb = apcb;
	}

}
