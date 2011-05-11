package com.ybi.whoot.dialogs;

import com.ybi.whoot.R;

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

public class AColorPickerView extends View implements
		android.view.View.OnTouchListener
{

	public static final int COLOR_RED = 0;
	public static final int COLOR_GREEN = 1;
	public static final int COLOR_BLUE = 2;
	public static final int ALPHA = 3;

	private AColorPickerRender render;

	private int type;

	private int positionValue = 130;

	public AColorPickerView(Context context, int type, int startValue, AColorPickerRender render)
	{
		super(context);
		this.setOnTouchListener(this);
		this.type = type;
		this.positionValue = startValue;
		this.render = render;
		if (type == COLOR_RED)
			this.render.setRed(startValue);
		else if (type == COLOR_GREEN)
			this.render.setGreen(startValue);
		else if (type == COLOR_BLUE)
			this.render.setBlue(startValue);
		else if (type == ALPHA)
			this.render.setAlpha(startValue);
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

		if (type == COLOR_RED)
			bg = BitmapFactory.decodeResource(getResources(), R.drawable.color_red);
		else if (type == COLOR_GREEN)
			bg = BitmapFactory.decodeResource(getResources(), R.drawable.color_green);
		else if (type == COLOR_BLUE)
			bg = BitmapFactory.decodeResource(getResources(), R.drawable.color_blue);
		else if (type == ALPHA)
			bg = BitmapFactory.decodeResource(getResources(), R.drawable.alpha);
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
		if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			float x = event.getX();
			positionValue = (int) x;
			if (positionValue < 0)
				positionValue = 0;
			if (positionValue > 255)
				positionValue = 255;

			if (type == COLOR_RED)
				this.render.setRed(positionValue);
			else if (type == COLOR_GREEN)
				this.render.setGreen(positionValue);
			else if (type == COLOR_BLUE)
				this.render.setBlue(positionValue);
			else if (type == ALPHA)
				this.render.setAlpha(positionValue);

			invalidate();
		}

		Log.d("YBI", "TOUCH PositonValue=" + positionValue);
		return true;
	}

}
