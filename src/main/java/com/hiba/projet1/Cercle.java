package com.hiba.projet1;

import android.graphics.Canvas;

public class Cercle extends Dessin{
    float x,y,r;
    public Cercle(){
        super();
    }

    @Override
    public void DrawCanvas(Canvas c) {
        super.DrawCanvas(c);
        c.drawCircle(x,y,r,DrawPaint);


    }
    public void CalculRadus(float x2 , float y2){
        r = (float)Math.sqrt(((x2-x) *(x2-x))  + ((y2-y)*(y2-y)));
    }

}