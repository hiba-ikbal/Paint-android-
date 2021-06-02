package com.hiba.projet1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

public class Dessin{

    //drawing and canvas paint
    public Paint DrawPaint;
    public Dessin(){
        DrawPaint = new Paint();
    }
    //canvas
    public void  DrawCanvas(Canvas c){

    }
public void setCouleur(int couleur )
    {
        DrawPaint.setColor(couleur);
    }





}




