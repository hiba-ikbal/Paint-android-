package com.hiba.projet1;

import android.graphics.Canvas;

public class Rectangle extends Dessin{
    float xg,yhg,xd,ybd;
    public Rectangle(){
        super();
    }

    @Override
    public void DrawCanvas(Canvas c) {
        super.DrawCanvas(c);
        c.drawRect(xg,yhg,xd,ybd,DrawPaint);


    }

}
