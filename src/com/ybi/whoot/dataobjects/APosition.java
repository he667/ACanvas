package com.ybi.whoot.dataobjects;

import com.ybi.whoot.singleton.ASingletonCanvas;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class APosition implements AEditable
{
	public static final int POSITION_TYPE_RELATIF = 0;
	public static final int POSITION_TYPE_ABSOLU = 1;
	
	public static final int POSITION_ALIGNEMENT_GAUCHE = 0;
	public static final int POSITION_ALIGNEMENT_CENTRE = 1;
	public static final int POSITION_ALIGNEMENT_DROITE = 2;
	
	public static final int LINE_ONE =0 ;
	public static final int LINE_TWO =0 ;
	public static final int LINE_THRE =0 ;
	
	private int posx;
	private int posy;
	private int type;
	private int alignement;
	private int line;

	public APosition(int rline, int rx, int ry)
	{
		this.type = POSITION_TYPE_RELATIF ;
		this.line = rline;
		this.alignement = POSITION_ALIGNEMENT_CENTRE;
		this.posx = rx;
		this.posy = ry;
	}
	

	public void setPosx(int psx)
	{
		if (psx < 0) 
			psx =0;
		// TODO Recuperation de la valeur max de l'ecran
		else if (psx > 480)
			psx=480;
		this.posx = psx;
	}


	public void setPosy(int psy)
	{
		if (psy < 0) 
			psy =0;
		// TODO Recuperation de la valeur max de l'ecran
		else if (psy > 100)
			psy=100;
		
		this.posy = psy;
	}


	public int getPosx()
	{
		return posx;
	}


	public int getPosy()
	{
		return posy;
	}


	public void setPosition(int rx, int ry)
	{
		this.posx = rx;
		this.posy = ry;
	}

	@Override
	public void save(String s, Editor editor)
	{

		editor.putInt(s + "posx", this.posx);
		editor.putInt(s + "posy", this.posy);

		editor.putInt(s + "alignement", this.alignement);
		editor.putInt(s + "type", this.type);
		editor.putInt(s + "line", this.line);

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		posx = localsettings.getInt(s + "posx", posx);
		posy = localsettings.getInt(s + "posy", posy);

		alignement = localsettings.getInt(s + "alignement", alignement);
		type = localsettings.getInt(s + "type", type);
		line = localsettings.getInt(s + "line", line);
	}


	public int getType()
	{
		return type;
	}


	public void setType(int type)
	{
		this.type = type;
	}


	public int getLine()
	{
		return line;
	}


	public void setLine(int line)
	{
		this.line = line;
	}


	public int getAlignement()
	{
		return alignement;
	}


	public void setAlignement(int alignement)
	{
		this.alignement = alignement;
	}

	
	public CharSequence[] getPositionChoices()
	{
		return ASingletonCanvas.getInstance().getPosition_texte();
	}
	public CharSequence[] getLineChoices()
	{
		return ASingletonCanvas.getInstance().getLine_texte();
	}
	public CharSequence[] getAlignementChoices()
	{
		return ASingletonCanvas.getInstance().getAlignement_texte();
	}
}
