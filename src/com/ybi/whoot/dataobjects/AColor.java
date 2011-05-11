package com.ybi.whoot.dataobjects;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;

public class AColor implements AEditable
{
	private int red;
	private int green;
	private int blue;
	private int alpha;

	public AColor(int alpha, int red, int green, int blue)
	{
		this.alpha = alpha;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public AColor(int color)
	{
		this.green = Color.green(color);
		this.alpha = Color.alpha(color);
		this.red = Color.red(color);
		this.blue = Color.blue(color);
	}

	public int getColor()
	{
		return Color.argb(alpha, red, green, blue);
	}

	public void setColor(int alpha, int red, int green, int blue)
	{
		this.alpha = alpha;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public void save(String s,Editor editor)
	{
		editor.putInt(s, this.getColor());
		
	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		int color = localsettings.getInt(s, getColor());
		this.green = Color.green(color);
		this.alpha = Color.alpha(color);
		this.red = Color.red(color);
		this.blue = Color.blue(color);
	}
}
