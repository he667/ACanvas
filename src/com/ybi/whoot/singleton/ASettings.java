package com.ybi.whoot.singleton;

import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ybi.whoot.dataobjects.AEditable;

public class ASettings<E> extends HashMap<String, AEditable>
{
	protected final static String PREFS_NAME = "com.ybi.whoot";

	private static final long serialVersionUID = 8017095247901300931L;

	public void save(Editor editor)
	{
		Iterator<String> it = this.keySet().iterator();
		while (it.hasNext())
		{
			String s = (String) it.next();
			AEditable e = this.get(s);
			e.save(s,editor);
		}
	}

	public void load(Context context)
	{
		SharedPreferences localsettings = context.getSharedPreferences(PREFS_NAME, 0);

		Iterator<String> it = this.keySet().iterator();
		while (it.hasNext())
		{
			String s = (String) it.next();
			AEditable e = this.get(s);
			e.load(s,localsettings);
		}
	}

}
