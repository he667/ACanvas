package com.ybi.whoot.dataobjects;

import com.ybi.whoot.singleton.ASingletonCanvas;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ADecalage  implements AEditable
{

	protected static final int HAUT = -5;
	protected static final int CENTRE = 0;
	protected static final int BAS = 5;

	private int decalage = CENTRE;
	private int deplacement = CENTRE;

	public int getDecalage()
	{
		return decalage;
	}

	public int getDeplacement()
	{
		return deplacement;
	}

	public void setDeplacement(int deplacement)
	{
		this.deplacement = deplacement;
	}

	public void setDecalage(int decalage)
	{
		this.decalage = decalage;
	}

	public int getItemPositionTexte()
	{
		if (decalage == HAUT)
			return 0;
		else if (decalage == CENTRE)
			return 1;
		else if (decalage == BAS)
			return 2;
		else
			return 1;

	}

	public void setItemTexte(int item)
	{
		if (item == 0)
		{
			setDecalage(ADecalage.HAUT);
		} else if (item == 1)
		{
			setDecalage(ADecalage.CENTRE);
		} else if (item == 2)
		{
			setDecalage(ADecalage.BAS);
		}
	}

	public int getItemPositionShadow()
	{
		if ((decalage == 0) && (deplacement == 0))
			return 0;
		else if ((decalage == -1) && (deplacement == 1))
			return 1;
		else if ((decalage == 0) && (deplacement == 1))
			return 2;
		else if ((decalage == 1) && (deplacement == 1))
			return 3;
		else
			return 1;

	}

	public void setItemShadow(int item)
	{

		if (item == 0)
		{
			this.setDecalage(0);
			this.setDeplacement(0);

		} else if (item == 1)
		{
			this.setDecalage(-1);
			this.setDeplacement(1);

		} else if (item == 2)
		{
			this.setDecalage(0);
			this.setDeplacement(1);

		} else if (item == 3)
		{
			this.setDecalage(1);
			this.setDeplacement(1);

		}

	}

	public String getLibelleTexte()
	{
		return (String) ASingletonCanvas.getInstance().getDec_texte(getItemPositionTexte());
	}

	public String getLibelleShadow()
	{
		return (String) ASingletonCanvas.getInstance().getDec_shadow(getItemPositionShadow());
	}

	public CharSequence[] getChoices()
	{
		return ASingletonCanvas.getInstance().getDec_texte();

	}
	public CharSequence[] getChoicesShadow()
	{
		return ASingletonCanvas.getInstance().getDec_shadow();

	}

	@Override
	public void save(String s, Editor editor)
	{
		editor.putInt(s+"getDecalage", getDecalage());
		editor.putInt(s+"getDeplacement", getDeplacement());
	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		decalage = localsettings.getInt(s+"getDecalage", decalage);
		deplacement = localsettings.getInt(s+"getDeplacement", deplacement);
	}
}
