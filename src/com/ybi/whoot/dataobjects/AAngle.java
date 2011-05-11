package com.ybi.whoot.dataobjects;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AAngle implements AEditable
{
	private int rotx;
	private int roty;

	public AAngle(int rx, int ry)
	{
		this.rotx = rx;
		this.roty = ry;
	}

	public int getRotx()
	{
		return rotx;
	}

	public void setRotx(int rotx)
	{
		this.rotx = rotx;
	}

	public int getRoty()
	{
		return roty;
	}

	public void setRoty(int roty)
	{
		this.roty = roty;
	}

	public void setAngle(int rx, int ry)
	{
		this.rotx = rx;
		this.roty = ry;
	}

	@Override
	public void save(String s, Editor editor)
	{
		editor.putInt(s + "rotx", this.rotx);
		editor.putInt(s + "roty", this.roty);

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		rotx = localsettings.getInt(s + "rotx", rotx);
		roty = localsettings.getInt(s + "roty", roty);
	}
}
