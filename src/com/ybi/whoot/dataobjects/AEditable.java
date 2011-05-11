package com.ybi.whoot.dataobjects;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public interface AEditable
{
	public void save(String s, Editor editor);
	
	public void load(String s, SharedPreferences localsettings);
	
}
