package com.ybi.whoot.dialogs;

import com.ybi.whoot.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class AColorPickerRender extends View
{

	private int alpha;
	private int red;
	private int green;
	private int blue;

	public AColorPickerRender(Context context)
	{
		super(context);
	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		Paint paint = new Paint();
		Bitmap bg = null;
		bg = BitmapFactory.decodeResource(getResources(), R.drawable.rendu);
		canvas.drawBitmap(bg, 0, 0, paint);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.argb(alpha, red, green, blue));
		canvas.drawRoundRect(new RectF(50, 5, 210, 35), 3, 3, paint);
		paint.setColor(Color.BLACK);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(255, 50);
	}

	public int getAlpha()
	{
		return alpha;
	}

	public void setAlpha(int alpha)
	{
		this.alpha = alpha;
		invalidate();
	}

	public int getRed()
	{
		return red;
	}

	public void setRed(int red)
	{
		this.red = red;
		invalidate();
	}

	public int getGreen()
	{
		return green;
	}

	public void setGreen(int green)
	{
		this.green = green;
		invalidate();
	}

	public int getBlue()
	{
		return blue;
	}

	public void setBlue(int blue)
	{
		this.blue = blue;
		invalidate();
	}

}
