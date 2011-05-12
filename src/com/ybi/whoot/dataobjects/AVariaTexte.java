package com.ybi.whoot.dataobjects;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ybi.whoot.singleton.ASingletonCanvas;
import com.ybi.whoot.utils.Utils;

public class AVariaTexte implements AVaria
{
	public final static int CHIFFRE = 0;
	public final static int NORMAL = 1;
	public final static int MINUSCULE = 2;
	public final static int MAJUSCULE = 3;
	public final static int CHAOTIQUE = 4;
	public final static int HAXORLEET = 5;
	public final static int SANS = 6;
	public final static int DOUBLECHIFFRE = 7;

	private int type = NORMAL;

	public AVariaTexte(int t)
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
		case CHIFFRE:
			return s;
		case DOUBLECHIFFRE:
			return Integer.parseInt(s)<10?'0'+s:s;
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
		case SANS:
			return "";
		default:
			return s;
		}
	}

	public int getItem()
	{
		return type;
	}

	public void setItemPosition(int item)
	{
		setType(item);
	}

	public String getLibelle()
	{
		return (String) ASingletonCanvas.getInstance().getVaria_texte(getItemPosition());
	}

	@Override
	public CharSequence[] getChoices()
	{
		return ASingletonCanvas.getInstance().getVaria_texte();
	}

	@Override
	public int getItemPosition()
	{
		return type;
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
		// editor.putString(s + "getCustom", getCustom());

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		type = localsettings.getInt(s + "getType", getType());
		// custom = localsettings.getString(s + "getCustom", getCustom());
	}

}
