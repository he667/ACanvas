package com.ybi.whoot.widget;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RemoteViews;

import com.ybi.whoot.R;
import com.ybi.whoot.activities.AConfigure;
import com.ybi.whoot.dataobjects.AFont;
import com.ybi.whoot.dataobjects.APosition;
import com.ybi.whoot.dataobjects.AVariaTexte;
import com.ybi.whoot.singleton.ASingletonCanvas;

public class ACanvas extends AppWidgetProvider
{

	public static final float MAX_WIDTH = 480.0f;
	public static final float MAX_HEIGHT= 100.0f;
	public static final String URI_SCHEME = "ybi_widget";
	
	public static boolean isVisible = true;

	@Override
	public void onEnabled(Context context)
	{
		// This is only called once, regardless of the number of widgets of this
		// type
		Log.i("YBI", "onEnabled()");
		// recupere le widget id
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName thisWidget = new ComponentName(context, ACanvas.class);
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		// donne les ids au service qui les donnera au broadcast
		ACanvasService.setWidgetIds(appWidgetIds);
		Intent svc = new Intent(context, ACanvasService.class);
		context.startService(svc);
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.i("YBI", "ONREEIVE");

		try
		{

			ASingletonCanvas.getInstance().setContext(context);
			ASingletonCanvas.getInstance().initTranslations();
			ASingletonCanvas.getInstance().initSettings();
			ASingletonCanvas.getInstance().loadSettings();

			final String action = intent.getAction();
			if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action))
			{
				final int appWidgetId = intent.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
				if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID)
				{
					this.onDeleted(context, new int[]
					{ appWidgetId });
				}
			} else if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
			{
				if (!URI_SCHEME.equals(intent.getScheme()))
				{
					Log.i("YBI", "LAUNCHING SERVICE");

					final int[] appWidgetIds = intent.getExtras().getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);

					// donne les ids au service qui les donnera au broadcast
					ACanvasService.setWidgetIds(appWidgetIds);
					Intent svc = new Intent(context, ACanvasService.class);
					context.startService(svc);

				}
			}
		} catch (Exception e)
		{
			Log.e("YBI", "Probleme dans l'initialisation", e);
		}
		super.onReceive(context, intent);

	}



	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{

		try
		{
			ASingletonCanvas.getInstance().setContext(context);
			ASingletonCanvas.getInstance().initTranslations();
			ASingletonCanvas.getInstance().loadSettings();
//			ASingletonCanvas.getInstance().setContext(null);

			Log.i("YBI", "ON UPDATE!!!!");
			for (int appWidgetId : appWidgetIds)
			{
				Bitmap bitmap = ACanvas.drawWidget();

				RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
				views.setImageViewBitmap(R.id.ImageView01, bitmap);

				Intent intent = new Intent(context, AConfigure.class);
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				intent.setData(Uri.withAppendedPath(Uri.parse(URI_SCHEME
						+ "://widget/id/"), String.valueOf(appWidgetId)));
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);// PendingIntent.FLAG_UPDATE_CURRENT);

				views.setOnClickPendingIntent(R.id.ImageView01, pendingIntent);

				appWidgetManager.updateAppWidget(appWidgetId, views);
				// bitmap.recycle();

			}
		} catch (Exception e)
		{
			Log.e("YBI", "Probleme dans la mise a jour", e);
		}

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		Log.d("YBI", "onDelete()");
		Intent svc = new Intent(context, ACanvasService.class);
		context.stopService(svc);
		super.onDeleted(context, appWidgetIds);
	}

	public static Bitmap drawWidget()
	{
		Log.d("YBI", "DRAWING WIDGET" );

		
		GregorianCalendar gc = new GregorianCalendar();

		String heure = null;
		String sep =null;
		String minute = null;

		int ijour = gc.get(GregorianCalendar.DATE) - 1;
		int isemaine = gc.get(GregorianCalendar.DAY_OF_WEEK) - 1;
		int imois = gc.get(GregorianCalendar.MONTH);

		int h = gc.get(GregorianCalendar.HOUR_OF_DAY);
		int m = gc.get(GregorianCalendar.MINUTE);
		if (h > 12)
			h += -12;

		if (ASingletonCanvas.getInstance().getType("heuresType").getType() == AVariaTexte.CHIFFRE)
			heure = Integer.toString(h);
		else
		if (ASingletonCanvas.getInstance().getType("heuresType").getType() == AVariaTexte.DOUBLECHIFFRE)
			heure = "0" + Integer.toString(h);
		else
			heure = ASingletonCanvas.getInstance().getHm(h);

		sep = new String();
		if ((h > 0) && (h < 12))
			sep = ASingletonCanvas.getInstance().getHeure();
		if ((h > 1) && (h < 12))
			sep = ASingletonCanvas.getInstance().getHeures();
		// if (h ==1)
		// heure += "##$$";

		if (ASingletonCanvas.getInstance().getType("minutesType").getType() == AVariaTexte.CHIFFRE)
		{
				minute = Integer.toString(m);
		} else
		if (ASingletonCanvas.getInstance().getType("minutesType").getType() == AVariaTexte.DOUBLECHIFFRE)
		{
			if (m < 10)
				minute = "0" + Integer.toString(m);
			else
				minute = Integer.toString(m);
		} else
		{
			if (m <= 10)
				minute = ASingletonCanvas.getInstance().getMn(m);
			else if (m < 20)
				minute = ASingletonCanvas.getInstance().getMd(m - 11);
			else
			{
				minute = ASingletonCanvas.getInstance().getMm((((int) (m / 10)) - 2));
				if ((m % 10) == 1)
					minute += ASingletonCanvas.getInstance().getEtUne();
				else
					minute += ASingletonCanvas.getInstance().getMn(m % 10);
			}
		}

		Paint p = new Paint();
		p.setDither(false);
		p.setFilterBitmap(false); 
		p.setStyle(Style.STROKE);
		p.setStrokeWidth(8);
		p.setColor(0xFFFF0000);
		
		switch (ASingletonCanvas.getInstance().getFont().getFont())
		{
		case AFont.DEFAULT:
			p.setTypeface(Typeface.DEFAULT);
			break;
		case AFont.MONO:
			p.setTypeface(Typeface.MONOSPACE);
			break;
		case AFont.SANSERIF:
			p.setTypeface(Typeface.SANS_SERIF);
			break;
		case AFont.SERIF:
			p.setTypeface(Typeface.SERIF);
			break;
		default:
			p.setTypeface(Typeface.DEFAULT);
			break;
		}

		Bitmap bitmap = Bitmap.createBitmap((int) MAX_WIDTH, (int) MAX_HEIGHT, Config.ARGB_8888);
		bitmap.setDensity(DisplayMetrics.DENSITY_HIGH);
		Canvas canvas = new Canvas(bitmap);
		
		p.setStyle(Paint.Style.FILL);
		// turn antialiasing on
		p.setAntiAlias(true);
		p.setColor(ASingletonCanvas.getInstance().getColor("backgroundColor").getColor());
		canvas.drawRect(new Rect(0, 0, (int)MAX_WIDTH, (int)MAX_HEIGHT), p);
		p.setColor(0xFF000000);
		
		// now is the good time to draw the image
		if ((ASingletonCanvas.getInstance().getImage() != null) && (ASingletonCanvas.getInstance().getImage().getImage() != null))
		{
			try
			{
				int backgroundPosX = 0;
				int backgroundPosY = 0;
				
				Log.d("YBI" ,"loading file" + ASingletonCanvas.getInstance().getImage().getImage().toString());
				Bitmap bgi = Media.getBitmap(ASingletonCanvas.getInstance().getContext().getContentResolver(), ASingletonCanvas.getInstance().getImage().getImage());
				
				if ((ASingletonCanvas.getInstance().getPosition("backgroundPos").getType() == APosition.POSITION_TYPE_RELATIF)
						&& (ASingletonCanvas.getInstance().getPosition("backgroundPos").getAlignement() == APosition.POSITION_ALIGNEMENT_GAUCHE))
				{
					backgroundPosX = (int) (ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosx());
					backgroundPosY = (int) (ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosy());
				}

				if ((ASingletonCanvas.getInstance().getPosition("backgroundPos").getType() == APosition.POSITION_TYPE_RELATIF)
						&& (ASingletonCanvas.getInstance().getPosition("backgroundPos").getAlignement() == APosition.POSITION_ALIGNEMENT_CENTRE))
				{
					backgroundPosX = (int) (ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosx()- (int) (bgi.getWidth() / 2.0f) + (int) (MAX_WIDTH / 2.0f));
					backgroundPosY = (int) (ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosy()- (int) (bgi.getHeight() / 2.0f) + (int) (MAX_HEIGHT / 2.0f));
				}

				if ((ASingletonCanvas.getInstance().getPosition("backgroundPos").getType() == APosition.POSITION_TYPE_RELATIF)
						&& (ASingletonCanvas.getInstance().getPosition("backgroundPos").getAlignement() == APosition.POSITION_ALIGNEMENT_DROITE))
				{
					backgroundPosX = (int) (ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosx() - bgi.getWidth() + MAX_WIDTH);
					backgroundPosY = (int) (ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosy() - bgi.getHeight() + MAX_HEIGHT);
				}

				if (ASingletonCanvas.getInstance().getPosition("backgroundPos").getType() == APosition.POSITION_TYPE_ABSOLU)
				{
						backgroundPosX = ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosx();
						backgroundPosY = ASingletonCanvas.getInstance().getPosition("backgroundPos").getPosy();
				}

				
				canvas.drawBitmap(bgi, backgroundPosX, backgroundPosY, null);
			} catch (FileNotFoundException e)
			{
				Log.e("YBI" ,"file not found exception when looking for background" ,e);
			} catch (IOException e)
			{
				Log.e("YBI" ,"io exception when looking for background" ,e);
			}
		}
		
		
		p.setShadowLayer(ASingletonCanvas.getInstance().getTaille("shadowTaille").getTaille(), ASingletonCanvas.getInstance().getDecalage("shadowDecalage").getDecalage(), ASingletonCanvas.getInstance().getDecalage("shadowDecalage").getDeplacement(), ASingletonCanvas.getInstance().getColor("shadowColor").getColor());

		float measureHeure = ACanvas.textMeasure("heures", heure, p);
		float measureSep = ACanvas.textMeasure("sep", sep, p);
		float measureMinute = ACanvas.textMeasure("minutes", minute, p);

		String semaine;
		String mois;
		String jour;

		semaine = ASingletonCanvas.getInstance().getSe(isemaine);
		mois = ASingletonCanvas.getInstance().getMs(imois);
		if (ASingletonCanvas.getInstance().getType("jourType").getType() == AVariaTexte.CHIFFRE)
		{
			jour = Integer.toString(ijour+1);
		} else
		if ((ASingletonCanvas.getInstance().getType("jourType").getType() == AVariaTexte.DOUBLECHIFFRE))
		{
			if (ijour+1<10)
				jour = "0" + Integer.toString(ijour+1);
			else
				jour = Integer.toString(ijour+1);
		} else
		{
			jour = ASingletonCanvas.getInstance().getJr(ijour);
		}

		float measureMois = ACanvas.textMeasure("mois", mois, p);
		float measureSemaine = ACanvas.textMeasure("semaine", semaine, p);
		float measureJour = ACanvas.textMeasure("jour", jour, p);
		
		float ht = ASingletonCanvas.getInstance().getTaille("heuresTaille").getTaille();
		float st = ASingletonCanvas.getInstance().getTaille("sepTaille").getTaille();
		float mt = ASingletonCanvas.getInstance().getTaille("minutesTaille").getTaille();

		if (measureHeure + measureSep + measureMinute > MAX_WIDTH)
		{
			Log.d("YBI", "Measrure =" + "H/"+measureHeure + "S/"+measureSep + "M/"+ measureMinute );
			Log.d("YBI", "Measrure Total =" + (measureHeure + measureSep + measureMinute));
			while (measureHeure + measureSep + measureMinute > MAX_WIDTH)
			{
				ht--;
				mt--;
				st--;

				p.setTextSize(ht);
				measureHeure = p.measureText(ASingletonCanvas.getInstance().getType("heuresType").getString(heure));

				p.setTextSize(st);
				measureSep = p.measureText(ASingletonCanvas.getInstance().getType("sepType").getString(sep));

				p.setTextSize(mt);
				measureMinute = p.measureText(ASingletonCanvas.getInstance().getType("minutesType").getString(minute));
				Log.d("YBI", "Resize Measrure =" + "H/"+measureHeure + "S/"+measureSep + "M/"+ measureMinute );
				Log.d("YBI", "Resize Measrure Total =" + (measureHeure + measureSep + measureMinute));

			}

		}

		float jot = ASingletonCanvas.getInstance().getTaille("jourTaille").getTaille();
		float mot = ASingletonCanvas.getInstance().getTaille("moisTaille").getTaille();
		float set = ASingletonCanvas.getInstance().getTaille("semaineTaille").getTaille();

		if (measureMois + measureSemaine + measureJour > MAX_WIDTH)
		{
			while (measureMois + measureSemaine + measureJour > MAX_WIDTH)
			{
				jot--;
				mot--;
				set--;

				p.setTextSize(mot);
				measureMois = p.measureText(ASingletonCanvas.getInstance().getType("moisType").getString(mois));

				p.setTextSize(set);
				measureSemaine = p.measureText(ASingletonCanvas.getInstance().getType("semaineType").getString(semaine));

				p.setTextSize(jot);
				measureJour = p.measureText(ASingletonCanvas.getInstance().getType("jourType").getString(jour));
			}

		}
		
		// dans le cas ou le positionnement est a absolu, on ne prend plus en compte la taille de l'element
		if (ASingletonCanvas.getInstance().getPosition("heuresPos").getType() == APosition.POSITION_TYPE_ABSOLU)
			measureHeure = 0.0f;
		if (ASingletonCanvas.getInstance().getPosition("sepPos").getType() == APosition.POSITION_TYPE_ABSOLU)
			measureSep= 0.0f;
		if (ASingletonCanvas.getInstance().getPosition("minutesPos").getType() == APosition.POSITION_TYPE_ABSOLU)
			measureMinute = 0.0f;
		if (ASingletonCanvas.getInstance().getPosition("jourPos").getType() == APosition.POSITION_TYPE_ABSOLU)
			measureJour = 0.0f;
		if (ASingletonCanvas.getInstance().getPosition("semainePos").getType() == APosition.POSITION_TYPE_ABSOLU)
			measureSemaine = 0.0f;
		if (ASingletonCanvas.getInstance().getPosition("moisPos").getType() == APosition.POSITION_TYPE_ABSOLU)
			measureMois = 0.0f;

		//dans le cas ou le positionnement est relatif, on ajoute la composante x comme un offset
		if (ASingletonCanvas.getInstance().getPosition("heuresPos").getType() == APosition.POSITION_TYPE_RELATIF)
			measureHeure += ASingletonCanvas.getInstance().getPosition("heuresPos").getPosx();
		if (ASingletonCanvas.getInstance().getPosition("sepPos").getType() == APosition.POSITION_TYPE_RELATIF)
			measureSep += ASingletonCanvas.getInstance().getPosition("sepPos").getPosx();;
		if (ASingletonCanvas.getInstance().getPosition("minutesPos").getType() == APosition.POSITION_TYPE_RELATIF)
			measureMinute += ASingletonCanvas.getInstance().getPosition("minutesPos").getPosx();;
		if (ASingletonCanvas.getInstance().getPosition("jourPos").getType() == APosition.POSITION_TYPE_RELATIF)
			measureJour += ASingletonCanvas.getInstance().getPosition("jourPos").getPosx();;
		if (ASingletonCanvas.getInstance().getPosition("semainePos").getType() == APosition.POSITION_TYPE_RELATIF)
			measureSemaine += ASingletonCanvas.getInstance().getPosition("semainePos").getPosx();;
		if (ASingletonCanvas.getInstance().getPosition("moisPos").getType() == APosition.POSITION_TYPE_RELATIF)
			measureMois += ASingletonCanvas.getInstance().getPosition("moisPos").getPosx();

		
		
			
		// cas centre
		int startPos = (int) ((MAX_WIDTH - (measureHeure + measureSep + measureMinute)) / 2.0);
		int startPosDate = (int) ((MAX_WIDTH - (measureSemaine + measureJour + measureMois)) / 2.0);

		int verticalPos = 55;
		if ((ASingletonCanvas.getInstance().getType("moisType").getString(mois).equals("")
				&& ASingletonCanvas.getInstance().getType("semaineType").getString(semaine).equals("") && ASingletonCanvas.getInstance().getType("jourType").getString(jour).equals("")))
			verticalPos = 75;

		
		// colonne de gauche
		if (ASingletonCanvas.getInstance().getPosition("heuresPos").getAlignement() == APosition.POSITION_ALIGNEMENT_GAUCHE)
		{
			float localStartPos = MAX_WIDTH - measureHeure;
			if (ASingletonCanvas.getInstance().getPosition("sepPos").getAlignement() == APosition.POSITION_ALIGNEMENT_GAUCHE)
				localStartPos -= measureSep;
			if (ASingletonCanvas.getInstance().getPosition("minutesPos").getAlignement() == APosition.POSITION_ALIGNEMENT_GAUCHE)
				localStartPos -= measureMinute;
			// HEURE TAILLE
			ACanvas.textPrepare("heures", ht, p);
			// HEURE DRAW
			ACanvas.drawElement("heures",canvas, heure, (int)localStartPos, 0.0f, verticalPos, p);

		}
		
		//if ((ASingletonCanvas.getInstance().getPosition("sepPos").getType() == APosition.POSITION_TYPE_RELATIF) &&
		//		(ASingletonCanvas.getInstance().getPosition("sepPos").getAlignement() == APosition.POSITION_ALIGNEMENT_GAUCHE))

		// HEURE TAILLE
		ACanvas.textPrepare("heures", ht, p);
		// HEURE DRAW
		ACanvas.drawElement("heures",canvas, heure, startPos,0, verticalPos, p);

		// SEP TAILLE
		ACanvas.textPrepare("sep", st, p);
		// SEP DRAW
		ACanvas.drawElement("sep",canvas, sep, startPos , measureHeure, verticalPos, p);
		
		// MINUTE TAILLE
		ACanvas.textPrepare("minutes", mt, p);
		// MINUTE DRAW
		ACanvas.drawElement("minutes",canvas, minute, startPos, measureHeure + measureSep, verticalPos, p);


		if (!((ASingletonCanvas.getInstance().getType("moisType").getString(mois).equals("")
				&& ASingletonCanvas.getInstance().getType("semaineType").getString(semaine).equals("") && ASingletonCanvas.getInstance().getType("jourType").getString(jour).equals(""))))
			verticalPos = (int) (verticalPos
					+ ASingletonCanvas.getInstance().getTaille("interligneTaille").getTaille() - 20.0d);

		// SEMAINE TAILLE
		ACanvas.textPrepare("semaine", set, p);
		// SEMAINE DRAW
		ACanvas.drawElement("semaine",canvas, semaine, startPosDate ,0, verticalPos + 25, p);
		
		
		// JOUR TAILLE
		ACanvas.textPrepare("jour", jot, p);
		// JOUR DRAW
		ACanvas.drawElement("jour",canvas, jour, startPosDate , measureSemaine , verticalPos + 25, p);
		
		
		// MOIS TAILLE
		ACanvas.textPrepare("mois", mot, p);
		// MOIS DRAW
		ACanvas.drawElement("mois",canvas, mois, startPosDate , measureSemaine + measureJour , verticalPos + 25, p);
		
		
		
		if ((ASingletonCanvas.getInstance().getAngle().getRotx() == 0) &&
		(ASingletonCanvas.getInstance().getAngle().getRoty()  == 0))
		{
			return bitmap;
		}
		else
		{
			Matrix matrix = new Matrix();
			Camera camera = new Camera();
	
			camera.save();
		    camera.rotateX(ASingletonCanvas.getInstance().getAngle().getRotx() );
		    camera.rotateY(ASingletonCanvas.getInstance().getAngle().getRoty() );
		    camera.translate(- MAX_WIDTH / 2.0f, 60.0f,0.0f);
		    camera.getMatrix(matrix);
		    camera.restore();
		    
		    //matrix.preTranslate(0.0f, 0.0f);
		    
		    Bitmap tbp = Bitmap.createBitmap(bitmap, 0, 0, 480, 100, matrix, true);
			return tbp;
		}
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String titlePrefix)
	{
		if (isVisible)
			{
			Log.d("YBI", "updateAppWidget appWidgetId=" + appWidgetId
					+ " titlePrefix=" + titlePrefix);
	
			// petite verification
			ASingletonCanvas.getInstance().setContext(context);
			ASingletonCanvas.getInstance().initTranslations();
			ASingletonCanvas.getInstance().loadSettings();
			
			Bitmap bitmap = ACanvas.drawWidget();
	
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			views.setImageViewBitmap(R.id.ImageView01, bitmap);
			
			Intent intent = new Intent(context, AConfigure.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			intent.setData(Uri.withAppendedPath(Uri.parse(URI_SCHEME
					+ "://widget/id/"), String.valueOf(appWidgetId)));
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);// PendingIntent.FLAG_UPDATE_CURRENT);
	
			views.setOnClickPendingIntent(R.id.ImageView01, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		else
		{
			Log.d("YBI", "WIDGET IS SLEEPING NOTHING TO BE DONE" );
		}
		// bitmap.recycle();
	}
	
	private static void textPrepare(String key, float value, Paint p)
	{
		p.setFakeBoldText(ASingletonCanvas.getInstance().getTypo(key + "Typo").isFakeBoldText());
		p.setTextSkewX(ASingletonCanvas.getInstance().getTypo( key + "Typo").getTextSkewX());
		p.setColor(ASingletonCanvas.getInstance().getColor(key + "Color").getColor());
		p.setTextSize(value);

	}
	
	private static float textMeasure(String key, String text, Paint p)
	{
		p.setFakeBoldText(ASingletonCanvas.getInstance().getTypo(key + "Typo").isFakeBoldText());
		p.setTextSkewX(ASingletonCanvas.getInstance().getTypo(key + "Typo").getTextSkewX());
		p.setTextSize(ASingletonCanvas.getInstance().getTaille(key + "Taille").getTaille());
		float measure = p.measureText(ASingletonCanvas.getInstance().getType(key + "Type").getString(text));
		return measure;

	}
	
	private static void drawElement(String key, Canvas canvas, String value, int startPosition,float offset, int verticalPosition, Paint p)
	{

		if ((ASingletonCanvas.getInstance().getPosition(key + "Pos").getType() == APosition.POSITION_TYPE_ABSOLU))
		{
			canvas.drawText(
					ASingletonCanvas.getInstance().getType(key + "Type").getString(value), 
					ASingletonCanvas.getInstance().getPosition(key + "Pos").getPosx(), 
					ASingletonCanvas.getInstance().getPosition(key + "Pos").getPosy(), 
					p);
		}
		else if ((ASingletonCanvas.getInstance().getPosition(key + "Pos").getType() == APosition.POSITION_TYPE_RELATIF))
		{
			// offset ici est celui du decalage cause par les autres elements, pas celui cause par le deplacement de l'element lui meme
			canvas.drawText(ASingletonCanvas.getInstance().getType(key + "Type").getString(value),startPosition+(int)offset+ASingletonCanvas.getInstance().getPosition(key + "Pos").getPosx(),verticalPosition+ASingletonCanvas.getInstance().getPosition(key + "Pos").getPosy(),p);
		}
		else
		{
			canvas.drawText(ASingletonCanvas.getInstance().getType(key + "Type").getString(value),startPosition+(int)offset,verticalPosition,p);
		}

	}
}
