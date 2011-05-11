package com.ybi.whoot.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ybi.whoot.R;
import com.ybi.whoot.dataobjects.AAngle;
import com.ybi.whoot.singleton.ASingletonCanvas;

public class AAnglePicker extends Dialog implements OnClickListener
{

	private AAnglePickerView aprotx;
	private AAnglePickerView aproty;
	private TextView tvx;
	private TextView tvy;

	private int rotx;
	private int roty;

	private AAnglePickerCallBack aacb;

	public AAnglePicker(Context context, AAngle angle)
	{
		super(context);
		this.rotx = angle.getRotx();
		this.roty = angle.getRoty();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("CheckStartActivity", "OnCreate");
		setTitle("Choississez vos angles");
		Resources res = getContext().getResources();
		
		LinearLayout ll = new LinearLayout(this.getContext());
		ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER_HORIZONTAL);

		LinearLayout rrll = new LinearLayout(this.getContext());
		rrll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		rrll.setOrientation(LinearLayout.VERTICAL);
		rrll.setGravity(Gravity.CENTER_HORIZONTAL);

		tvx = new TextView(this.getContext());
		tvx.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tvx.setGravity(Gravity.CENTER_HORIZONTAL);
		tvx.setId(R.id.APTextView01);
		tvx.setText(res.getString(R.string.rotx) + " " + ASingletonCanvas.getInstance().getAngle().getRotx() + "°");

		tvy = new TextView(this.getContext());
		tvy.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tvy.setGravity(Gravity.CENTER_HORIZONTAL);
		tvy.setId(R.id.APTextView02);
		tvy.setText(res.getString(R.string.roty) + " " + ASingletonCanvas.getInstance().getAngle().getRoty() + "°");

		rrll.addView(tvx);
		rrll.addView(tvy);
		ll.addView(rrll);

		LinearLayout rll = new LinearLayout(this.getContext());
		rll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		rll.setOrientation(LinearLayout.HORIZONTAL);
		rll.setGravity(Gravity.CENTER_HORIZONTAL);

		aprotx = new AAnglePickerView(this.getContext(), rotx, tvx);
		rll.addView(aprotx);
		ll.addView(rll);

		LinearLayout vll = new LinearLayout(this.getContext());
		vll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		vll.setOrientation(LinearLayout.HORIZONTAL);
		vll.setGravity(Gravity.CENTER_HORIZONTAL);

		aproty = new AAnglePickerView(this.getContext(), roty, tvy);
		vll.addView(aproty);
		ll.addView(vll);

		LinearLayout sll = new LinearLayout(this.getContext());
		sll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
		sll.setOrientation(LinearLayout.HORIZONTAL);
		sll.setGravity(Gravity.CENTER_HORIZONTAL);

		Button btok = new Button(this.getContext());
		btok.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btok.setGravity(Gravity.CENTER_HORIZONTAL);
		btok.setText("Valider");
		btok.setId(R.id.APButtonOk);
		btok.setOnClickListener(this);
		sll.addView(btok);

		Button btcancel = new Button(this.getContext());
		btcancel.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btcancel.setGravity(Gravity.CENTER_HORIZONTAL);
		btcancel.setText("Annuler");
		btcancel.setId(R.id.APButtonCancel);
		btcancel.setOnClickListener(this);
		sll.addView(btcancel);

		ll.addView(sll);

		setContentView(ll);
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.APButtonOk)
		{
			this.rotx = (int)(aprotx.getPositionValue()/2.0f-60.0f);
			this.roty = (int)(aproty.getPositionValue()/2.0f-60.0f);
			aacb.angleSelected(this.rotx,this.roty);
			dismiss();
		}
		if (v.getId() == R.id.APButtonCancel)
		{
			dismiss();
		}
	}

	
	public void register(AAnglePickerCallBack apcb)
	{
		this.aacb = apcb;
	}

}
