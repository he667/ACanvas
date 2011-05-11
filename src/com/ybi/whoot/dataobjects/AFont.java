package com.ybi.whoot.dataobjects;

import com.ybi.whoot.singleton.ASingletonCanvas;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AFont implements AEditable
{

	public final static int DEFAULT = 0;
	public final static int SERIF = 1;
	public final static int MONO = 2;
	public final static int SANSERIF = 3;
	
	private int font = DEFAULT;

	public AFont(int f)
	{
		this.font = f;
	}

	public int getFont()
	{
		return font;
	}

	public void setFont(int f)
	{
		this.font = f;
	}

	public int getItemPosition()
	{
		return font;
	}

	public void setItem(int item)
	{
		font = item;


	}

	public String getLibelle()
	{
			return (String) ASingletonCanvas.getInstance().getFont_texte(getItemPosition());
	}

	public CharSequence[] getChoices()
	{
			return ASingletonCanvas.getInstance().getFont_texte();
	}

	@Override
	public void save(String s, Editor editor)
	{
		editor.putInt(s, getFont());

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		font = localsettings.getInt(s, getFont());
	}
}
