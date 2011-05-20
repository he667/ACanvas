package com.ybi.whoot.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ybi.whoot.R;
import com.ybi.whoot.singleton.ASingletonCanvas;
import com.ybi.whoot.widget.ACanvas;
import com.ybi.whoot.widget.ADrawWidget;

public class APositionActivity extends Activity implements OnTouchListener
{

	private final static String[] elementsKey =
	{ "heuresPos", "sepPos", "minutesPos", "jourPos", "semainePos", "moisPos" };

	private ImageView renderingPreview;
	private String[] elementsListe;
	private String[] positionnementListe;
	private String[] alignementListe;
	private String[] lineListe;
	private int currentElement;

	private TextView currentElementLabel;
	private TextView currentElementX;
	private TextView currentElementY;
	private TextView currentPositonnement;
	private TextView currentAlignement;
	private TextView currentLine;

	private ImageButton up;
	private ImageButton down;
	private ImageButton left;
	private ImageButton right;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.config_position);

		// recuperation des composants pour interaction
		renderingPreview = (ImageView) this.findViewById(R.id.RenderingPreview);
		currentElementLabel = (TextView) this.findViewById(R.id.TextViewPositionElement);
		currentElementX = (TextView) this.findViewById(R.id.TextViewPositionX);
		currentElementY = (TextView) this.findViewById(R.id.TextViewPositionY);
		currentPositonnement = (TextView) this.findViewById(R.id.TextViewPositionnement);
		currentAlignement = (TextView) this.findViewById(R.id.TextViewAlignement);
		currentLine = (TextView) this.findViewById(R.id.TextViewLine);

		up = (ImageButton) this.findViewById(R.id.PositionButtonUp);
		down = (ImageButton) this.findViewById(R.id.PositionButtonDown);
		left = (ImageButton) this.findViewById(R.id.PositionButtonLeft);
		right = (ImageButton) this.findViewById(R.id.PositionButtonRight);

		// colle des listners aux boutons
		up.setOnTouchListener(this);
		down.setOnTouchListener(this);
		left.setOnTouchListener(this);
		right.setOnTouchListener(this);

		// affichage initial de la zone de previsualisation
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
		Resources res = getResources();
		elementsListe = res.getStringArray(R.array.element_liste);
		positionnementListe = res.getStringArray(R.array.position_liste);
		alignementListe = res.getStringArray(R.array.alignement_liste);
		lineListe = res.getStringArray(R.array.line_liste);

	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		Log.d("YBI", "onBackPressed");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d("YBI", "onDestroy");
	}

	@Override
	public void finish()
	{
		super.finish();
		Log.d("YBI", "onFinish");
	}

	public void onUp(View v)
	{
		Log.d("YBI", "UP Movement");
		ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setPosy(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPosy() - 1);
		updateLabels();
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
	}

	public void onDown(View v)
	{
		Log.d("YBI", "Down Movement");
		ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setPosy(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPosy() + 1);
		updateLabels();
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
	}

	public void onLeft(View v)
	{
		Log.d("YBI", "Left Movement");
		ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setPosx(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPosx() - 1);
		updateLabels();
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
	}

	public void onRight(View v)
	{
		Log.d("YBI", "Right Movement");
		ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setPosx(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPosx() + 1);
		updateLabels();
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
	}

	public void onPrevious(View v)
	{
		if (currentElement > 0)
			currentElement--;
		updateLabels();
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
	}

	public void onNext(View v)
	{
		if (currentElement < (elementsListe.length - 1))
			currentElement++;
		updateLabels();
		renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
	}

	public void onPositionnementPosition(View v)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.choisir_ligne);
		builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPositionChoices(), ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getType(), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				// si on change de positionnement alors je reinitialise les valeurs
				if (ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getType() != item)
				{
					ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setType(item);
					ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setPosx(0);
					ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setPosy(0);
				}
				dialog.dismiss();
				renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
				updateLabels();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void onPositionnementAlignement(View v)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.choisir_alignement);
		builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getAlignementChoices(), ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getAlignement(), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setAlignement(item);
				dialog.dismiss();
				renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
				updateLabels();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void onPositionnementLine(View v)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.choisir_ligne);
		builder.setSingleChoiceItems(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getLineChoices(), ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getLine(), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).setLine(item);
				dialog.dismiss();
				renderingPreview.setImageBitmap(ADrawWidget.drawWidget());
				updateLabels();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	private void updateLabels()
	{
		currentElementLabel.setText(elementsListe[currentElement]);
		currentElementX.setText(Integer.toString(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPosx()));
		currentElementY.setText(Integer.toString(ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getPosy()));
		currentPositonnement.setText(positionnementListe[ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getType()]);
		currentAlignement.setText(alignementListe[ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getAlignement()]);
		currentLine.setText(lineListe[ASingletonCanvas.getInstance().getPosition(elementsKey[currentElement]).getLine()]);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		if (v.getId() == R.id.PositionButtonUp)
		{
			onUp(v);
		} else if (v.getId() == R.id.PositionButtonDown)
		{
			onDown(v);
		} else if (v.getId() == R.id.PositionButtonLeft)
		{
			onLeft(v);
		} else if (v.getId() == R.id.PositionButtonRight)
		{
			onRight(v);
		}
		return false;
	}

}
