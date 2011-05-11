package com.ybi.whoot.dataobjects;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;

public class AImage implements AEditable
{

	
	private Uri image ;

	public AImage()
	{}
	
	public AImage(Uri i)
	{
		this.image = i;
	}

	public Uri getImage()
	{
		return image;
	}

	public void setImage(Uri f)
	{
		this.image= f;
	}

	@Override
	public void save(String s, Editor editor)
	{
		if (getImage() != null)
		editor.putString(s, getImage().toString());

	}

	@Override
	public void load(String s, SharedPreferences localsettings)
	{
		// si image est null deux possibilites
		// - soit image a ete gc et dans ce cas, il faut loader la valeur
		// - soit l'utilisateur n'a jamais choisi d'image et dans ce cas on fait rien
		if (getImage() == null)
		{
			String imageUrl = localsettings.getString(s, null);
			if (imageUrl !=null)
				image = Uri.parse(imageUrl);
		}
		else
		{
			String imageUrl = localsettings.getString(s, getImage().toString());
			image = Uri.parse(imageUrl);
		}
	}
}
