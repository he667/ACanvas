package com.ybi.whoot.dataobjects;

import java.util.HashMap;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ybi.whoot.singleton.ASingletonCanvas;
import com.ybi.whoot.utils.Utils;

public class AVariaSeparateur implements AVaria
{

	private final static HashMap<String, String> hmLeet = new HashMap<String, String>()
	{
		private static final long serialVersionUID = -4774084030754406983L;

		{
			put("e", "3");
			put("l", "1");
			put("s", "$");
			put("t", "7");
			put("z", "2");
			put("c", "<");
			put("a", "4");
			put("i", "|");
			put("o", "0");
			put("b", "8");
		}
	};

	public final static int SANS = 0;
	public final static int TRAIT = 1;
	public final static int DEUXPOINTS = 2;
	public final static int SLASH = 3;
	public final static int NORMAL = 4;
	public final static int MINUSCULE = 5;
	public final static int MAJUSCULE = 6;
	public final static int CHAOTIQUE = 7;
	public final static int HAXORLEET = 8;
	public final static int CUSTOM = 9;

	public final static int TEXTE = 0;
	public final static int SEPARATEUR = 1;
	public final static int SEMAINE = 2;
	public final static int MOIS = 3;

	public String getCustom()
	{
		return custom;
	}

	public void setCustom(String custom)
	{
		this.custom = custom;
	}

	private int type = NORMAL;
	private String custom = "////";

	public AVariaSeparateur(int t)
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
			return new String("");
		case TRAIT:
			return new String("|");
		case DEUXPOINTS:
			return new String(":");
		case SLASH:
			return new String("/");
		case NORMAL:
			return s;
		case MINUSCULE:
			return Utils.getMinuscle(s);
		case MAJUSCULE:
			return Utils.getMajuscule(s);
		case CHAOTIQUE:
			return Utils.getChaotique(s);
		case HAXORLEET:
			return getHaxor(s);
		case CUSTOM:
			return custom;
		default:
			return s;
		}
	}

	public static String getHaxor(String s)
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++)
		{
			sb.append(hmLeet.get(Character.toString(s.toLowerCase().charAt(i))) != null ? hmLeet.get(Character.toString(s.toLowerCase().charAt(i)))
					: Character.toString(s.toLowerCase().charAt(i)));
		}
		return sb.toString();

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
		return (String) ASingletonCanvas.getInstance().getVaria_separateur(getItemPosition());
	}

	@Override
	public CharSequence[] getChoices()
	{
		return ASingletonCanvas.getInstance().getVaria_separateur();
	}

	@Override
	public void save(String s, Editor editor)
	{
		editor.putInt(s + "getType", getType());
		editor.putString(s + "getCustom", getCustom());

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		type = localsettings.getInt(s + "getType", getType());
		custom = localsettings.getString(s + "getCustom", getCustom());

	}

}
