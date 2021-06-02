package com.hiba.projet1;

import android.graphics.Canvas;
import android.graphics.Path;

public class Triangle extends Dessin {
    float xs,ys,xbg,ybg,xbd,ybd;
    Path PathTriangle;
    public Triangle(){ super();PathTriangle = new Path();}
    public void  DrawCanvas(Canvas c){
        super.DrawCanvas(c);
        PathTriangle.moveTo(xs,ys);
        PathTriangle.lineTo(xbg,ybg);
        PathTriangle.lineTo(xbd,ybd);
        PathTriangle.lineTo(xs,ys);
        PathTriangle.close();
        c.drawPath(PathTriangle,DrawPaint);

    }
}
