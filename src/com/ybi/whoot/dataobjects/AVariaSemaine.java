package com.ybi.whoot.dataobjects;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ybi.whoot.singleton.ASingletonCanvas;
import com.ybi.whoot.utils.Utils;

public class AVariaSemaine implements AVaria
{

	public final static int NORMAL = 0;
	public final static int MINUSCULE = 1;
	public final static int MAJUSCULE = 2;
	public final static int CHAOTIQUE = 3;
	public final static int HAXORLEET = 4;
	public final static int TRIPLET = 5;
	public final static int TRIPLETMAJ = 6;
	public final static int SANS = 7;

	private int type = NORMAL;

	public AVariaSemaine(int t)
	{
		this.type = t;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getString(String s)
	{
		switch (type)
		{
		case SANS:
			return "";
		case NORMAL:
			return s;
		case MINUSCULE:
			return Utils.getMinuscle(s);
		case MAJUSCULE:
			return Utils.getMajuscule(s);
		case CHAOTIQUE:
			return Utils.getChaotique(s);
		case HAXORLEET:
			return Utils.getHaxor(s);
		case TRIPLET:
			return s.substring(0, 3);
		case TRIPLETMAJ:
			return s.substring(0, 3).toUpperCase();
		default:
			return s;
		}
	}

	public int getItemPosition()
	{
		return type;
	}

	public void setItemPosition(int item)
	{
		setType(item);

	}

	public String getLibelle()
	{
		return (String) ASingletonCanvas.getInstance().getVaria_semaine(getItemPosition());
	}

	@Override
	public CharSequence[] getChoices()
	{
		return ASingletonCanvas.getInstance().getVaria_semaine();
	}

	@Override
	public String getCustom()
	{
		return null;
	}

	@Override
	public void setCustom(String value)
	{

	}

	@Override
	public void save(String s, Editor editor)
	{
		editor.putInt(s + "getType", getType());
		// editor.putString(s+"getCustom", getCustom());

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		type = localsettings.getInt(s + "getType", getType());
	}

}
