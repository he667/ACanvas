package com.ybi.whoot.dataobjects;

import com.ybi.whoot.singleton.ASingletonCanvas;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ATaille implements AEditable
{

	public final static float ENORME = 75.0f;
	public final static float TRESGRAND = 55.0f;
	public final static float GRAND = 45.0f;
	public final static float MOYEN = 40.0f;
	public final static float PETIT = 30.0f;
	public final static float TRESPETIT = 20.0f;

	public final static float SERREE = 1.0f;
	public final static float PROCHE = 2.0f;
	public final static float DIFFUSE = 3.0f;

	private float taille = MOYEN;

	public ATaille(float f)
	{
		this.taille = f;
	}

	public float getTaille()
	{
		return taille;
	}

	public void setTaille(float taille)
	{
		this.taille = taille;
	}

	public int getItemPosition()
	{
		if (taille == TRESPETIT)
			return 0;
		else if (taille == PETIT)
			return 1;
		else if (taille == MOYEN)
			return 2;
		else if (taille == GRAND)
			return 3;
		else if (taille == TRESGRAND)
			return 4;
		else if (taille == ENORME)
			return 5;
		else if (taille == SERREE) // TODO
			return 0;
		else if (taille == PROCHE) // TODO
			return 1;
		else if (taille == DIFFUSE) // TODO
			return 2;
		else
			return 1;

	}

	public void setItemTexte(int item)
	{
		if (item == 0)
		{
			setTaille(ATaille.TRESPETIT);
		} else if (item == 1)
		{
			setTaille(ATaille.PETIT);
		} else if (item == 2)
		{
			setTaille(ATaille.MOYEN);
		} else if (item == 3)
		{
			setTaille(ATaille.GRAND);
		} else if (item == 4)
		{
			setTaille(ATaille.TRESGRAND);
		} else if (item == 5)
		{
			setTaille(ATaille.ENORME);
		}

	}

	public void setItemShadow(int item)
	{
		if (item == 0)
		{
			setTaille(ATaille.SERREE);
		} else if (item == 1)
		{
			setTaille(ATaille.PROCHE);
		} else if (item == 2)
		{
			setTaille(ATaille.DIFFUSE);
		}

	}

	public String getLibelle()
	{
		if (taille > 3.0f)
			return (String) ASingletonCanvas.getInstance().getTaille_texte(getItemPosition());
		else
			return (String) ASingletonCanvas.getInstance().getTaille_shadow(getItemPosition());
	}

	public CharSequence[] getChoices()
	{
		if (taille > 3.0f)
			return ASingletonCanvas.getInstance().getTaille_texte();
		else
			return ASingletonCanvas.getInstance().getTaille_shadow();
	}

	@Override
	public void save(String s, Editor editor)
	{
		editor.putFloat(s, getTaille());

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		taille = localsettings.getFloat(s, getTaille());
	}
}
