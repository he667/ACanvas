package com.ybi.whoot.dataobjects;

import com.ybi.whoot.singleton.ASingletonCanvas;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ATypo implements AEditable
{
	public final static float ITALIQUE = -0.2f;
	public final static float NORMAL = 0.0f;

	private float textSkewX = 0.0f;
	private boolean fakeBoldText = true;

	public float getTextSkewX()
	{
		return textSkewX;
	}

	public void setTextSkewX(float textSkewX)
	{
		this.textSkewX = textSkewX;
	}

	public boolean isFakeBoldText()
	{
		return fakeBoldText;
	}

	public void setFakeBoldText(boolean fakeBoldText)
	{
		this.fakeBoldText = fakeBoldText;
	}

	public int getItemPosition()
	{
		if ((!fakeBoldText) && (textSkewX == NORMAL))
			return 0;
		else if ((fakeBoldText) && (textSkewX == NORMAL))
			return 1;
		else if ((!fakeBoldText) && (textSkewX == ITALIQUE))
			return 2;
		else if ((fakeBoldText) && (textSkewX == ITALIQUE))
			return 3;
		else
			return 0;

	}

	public void setItem(int item)
	{
		// normal
		if (item == 0)
		{
			setFakeBoldText(false);
			setTextSkewX(ATypo.NORMAL);
		} else
		// gras
		if (item == 1)
		{
			setTextSkewX(ATypo.NORMAL);
			setFakeBoldText(true);
		} else 
		// italique
		if (item == 2)
		{
			setFakeBoldText(false);
			setTextSkewX(ATypo.ITALIQUE);
		} else
		// grasitalique
		if (item == 3)
		{
			setFakeBoldText(true);
			setTextSkewX(ATypo.ITALIQUE);
		}

	}

	public String getLibelle()
	{
		return (String) ASingletonCanvas.getInstance().getTypo(getItemPosition());
	}

	public CharSequence[] getChoices()
	{
		return ASingletonCanvas.getInstance().getTypo();
	}


	@Override
	public void save(String s,Editor editor)
	{
		editor.putFloat(s+".getTextSkewX", getTextSkewX());
		editor.putBoolean(s+".isFakeBoldText", isFakeBoldText());
	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		textSkewX = localsettings.getFloat(s+".getTextSkewX", getTextSkewX());
		fakeBoldText = localsettings.getBoolean(s+".isFakeBoldText", isFakeBoldText());
	}
}
