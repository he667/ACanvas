package com.ybi.whoot.dataobjects;

public interface AVaria extends AEditable
{
	/**
	 * Recupere la chaine de valeur associee a la variation
	 * 
	 * @param s
	 * @return
	 */
	public String getString(String s);

	/**
	 * Recupere les choix possibles de valeurs que l'on peut attribuer a une
	 * variation
	 * 
	 * @return
	 */
	public CharSequence[] getChoices();

	/**
	 * Recupere la position dans la liste des choix possibles
	 * 
	 * @return
	 */
	public int getItemPosition();

	/**
	 * Selectionne la position dans la liste des choix possibles
	 * 
	 * @return
	 */
	public void setItemPosition(int position);

	/**
	 * Selectionne une valeur pour une variation
	 * 
	 * @param item
	 */
	public void setType(int item);

	/**
	 * Recupere la valeur associee a une variation dans la liste des choix
	 * 
	 * @return
	 */
	public String getLibelle();

	/**
	 * Recupere le type associe a une variation (ie un type de variation)
	 * 
	 * @return
	 */
	public int getType();

	/**
	 * Cas particulier des configurations custom
	 * 
	 * @return
	 */
	public String getCustom();

	/**
	 * Cas particuler des configurations custom
	 * @param value
	 */
	public void setCustom(String value);

}
