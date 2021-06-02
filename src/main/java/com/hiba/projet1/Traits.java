package com.hiba.projet1;

import android.graphics.Canvas;
import android.graphics.Path;

public class Traits extends  Dessin {
    public Path PathTraits;
    public Traits(){
        super();
        PathTraits = new Path();
    }

    @Override
    public void DrawCanvas(Canvas c) {
        super.DrawCanvas(c);
        c.drawPath(PathTraits,DrawPaint);
    }
}