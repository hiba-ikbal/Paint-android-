package com.hiba.projet1;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.graphics.ColorSpace;

public class Effacer extends  Dessin {
    public Path PathEfface;
    public Effacer(){
        super();
        PathEfface = new Path();
    }

    @Override
    public void DrawCanvas(Canvas c) {
        super.DrawCanvas(c);
        c.drawPath(PathEfface,DrawPaint);
    }

}