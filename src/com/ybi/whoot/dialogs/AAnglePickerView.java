package com.ybi.whoot.dialogs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ybi.whoot.R;
import com.ybi.whoot.utils.Utils;

public class AAnglePickerView extends View implements
		android.view.View.OnTouchListener
{

	private TextView render;

	private int positionValue = 0;

	public AAnglePickerView(Context context, int startValue, TextView render)
	{
		super(context);
		this.setOnTouchListener(this);
		this.positionValue = (int)(startValue*2.0f+120.0f);
		this.render = render;
	}

	public int getPositionValue()
	{
		return positionValue;
	}

	public void setPositionValue(int positionValue)
	{
		this.positionValue = positionValue;
	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		Paint paint = new Paint();
		Bitmap bg = null;

		bg = BitmapFactory.decodeResource(getResources(), R.drawable.angle);
		canvas.drawBitmap(bg, 0, 0, paint);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		canvas.drawCircle(positionValue, 20, 10, paint);
		paint.setColor(Color.BLACK);
		canvas.drawCircle(positionValue, 20, 8, paint);
		Log.d("YBI", "DRAW PositonValue=" + positionValue);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(255, 50);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{

		try
		{
			if (event.getAction() == MotionEvent.ACTION_MOVE)
			{
				float x = event.getX();
				positionValue = (int) x;
				if (positionValue < 0)
					positionValue = 0;
				if (positionValue > 255)
					positionValue = 255;

				this.render.setText(Utils.texteAndValue(this.render.getText(), ((int)(positionValue / 2.0f - 60.0f)) + "°"));

				invalidate();
			}

			Log.d("YBI", "TOUCH PositonValue=" + positionValue);
		} catch (Exception e)
		{
			Log.e("YBI", "dont know what happend but couldnt play with move",e);
		}
		return true;
	}

}