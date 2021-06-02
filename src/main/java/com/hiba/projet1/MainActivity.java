package com.hiba.projet1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    LinearLayout Layout;

    //Utiliser find by id pour trouver les objets


    // Creer les objets de type DrawView

    //Le 1 er menu
    ImageView Crayon, Efface, Save, Undo, Redo, Circle, Rectangle, Triangle, Pipette, Remplir, LTraits;
    // Le 2 eme menu

    Button Orange, Rose, Mauve, Bleu, Jaune, Vert, Rouge, Noir;

    MotionEvent event;

    int CouleurSelectionne;
    String OutilSelectionne;
    public int LT;
    boolean PointExiste;


    Traits Ligne;
    Effacer effaceur;
    Rectangle rectangle;
    Cercle cercle;
    Triangle triangle;

    // vecteur pour ligne,rect,circle,triangle
    Vector<Traits> TableauTrait;
    Vector<Effacer> TableauEffaces;
    Vector<Rectangle> TableauRec;
    Vector<Cercle> TableauCercle;
    Vector<Triangle> TableauTriangle;

    Ecouteurclick ec;
    EcouteurTouch et;
    ConstraintLayout Tableau;
    Tableau_dessin TD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Layout = findViewById(R.id.parent);
        Crayon = (ImageView) findViewById(R.id.crayon);
        Efface = (ImageView) findViewById(R.id.efface);
        Save = (ImageView) findViewById(R.id.save);
        Undo = (ImageView) findViewById(R.id.undo);
        Redo = (ImageView) findViewById(R.id.redo);
        Remplir = (ImageView) findViewById(R.id.remplire);
        Pipette = (ImageView) findViewById(R.id.pipette);
        Circle = (ImageView) findViewById(R.id.cercle);
        Rectangle = (ImageView) findViewById(R.id.rectangle);
        Triangle = (ImageView) findViewById(R.id.triangle);
        LTraits = (ImageView) findViewById(R.id.LTrait);

        Tableau = (ConstraintLayout) findViewById(R.id.tableau);

        Rose = (Button) findViewById(R.id.rose);
        Mauve = (Button) findViewById(R.id.mauve);
        Jaune = (Button) findViewById(R.id.jaune);
        Vert = (Button) findViewById(R.id.vert);
        Noir = (Button) findViewById(R.id.noir);
        Bleu = (Button) findViewById(R.id.bleu);
        Orange = (Button) findViewById(R.id.orange);
        Rouge = (Button) findViewById(R.id.rouge);

        ec = new Ecouteurclick();
        et = new EcouteurTouch();

        TD = new Tableau_dessin(this);
        TD.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Tableau.addView(TD);
        Tableau.setBackgroundColor(Color.WHITE);
        Tableau.setOnTouchListener(et);

        Triangle.setOnClickListener(ec);
        Crayon.setOnClickListener(ec);
        Circle.setOnClickListener(ec);
        Rectangle.setOnClickListener(ec);
        Remplir.setOnClickListener(ec);
        Redo.setOnClickListener(ec);
        Undo.setOnClickListener(ec);
        Efface.setOnClickListener(ec);
        Save.setOnClickListener(ec);
        Pipette.setOnClickListener(ec);
        LTraits.setOnClickListener(ec);

        Noir.setOnClickListener(ec);
        Rouge.setOnClickListener(ec);
        Rose.setOnClickListener(ec);
        Orange.setOnClickListener(ec);
        Bleu.setOnClickListener(ec);
        Mauve.setOnClickListener(ec);
        Vert.setOnClickListener(ec);
        Jaune.setOnClickListener(ec);

        //Par defaut
        CouleurSelectionne = Color.BLACK;
        OutilSelectionne = "Crayon";
        TableauTrait = new Vector<Traits>();
        TableauEffaces = new Vector<Effacer>();
        TableauRec = new Vector<Rectangle>();
        TableauCercle = new Vector<Cercle>();
        TableauTriangle = new Vector<Triangle>();

        PointExiste = false;

    }

    //  sauvegarder la couleur dans une variable dans mainactivity
    public void setcolorsel(int colorsel) {
        CouleurSelectionne = colorsel;
    }

    public void SetOutilSel(String OutilSel) {
        OutilSelectionne = OutilSel;
    }

    public String getOutilSelectionne() {
        return OutilSelectionne;
    }

    public int getCouleurSelectionne() {
        return CouleurSelectionne;
    }

    private class Ecouteurclick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.noir:
                    setcolorsel(Color.BLACK);
                    break;
                case R.id.bleu:
                    setcolorsel(Color.BLUE);
                    break;
                case R.id.jaune:
                    setcolorsel(Color.YELLOW);
                    break;
                case R.id.mauve:
                    setcolorsel(Color.MAGENTA);
                    break;
                case R.id.rose:
                    setcolorsel(Color.rgb(255, 20, 140));
                    break;
                case R.id.rouge:
                    setcolorsel(Color.RED);
                    break;
                case R.id.orange:
                    setcolorsel(Color.rgb(255, 165, 0));
                    break;
                case R.id.vert:
                    setcolorsel(Color.GREEN);
                    break;


                case R.id.crayon:
                    SetOutilSel("Crayon");
                    break;
                case R.id.efface:
                    SetOutilSel("Efface");
                    break;
                case R.id.cercle:
                    SetOutilSel("Circle");
                    break;
                case R.id.pipette:
                    SetOutilSel("Pipette");
                    break;
                case R.id.rectangle:
                    SetOutilSel("Rectangle");
                    break;
                case R.id.redo:
                    SetOutilSel("Redo");
                    break;
                case R.id.remplire:
                    SetOutilSel("Remplir");
                    Tableau.setBackgroundColor(getCouleurSelectionne());
                    for (Effacer e : TableauEffaces) {
                        e.DrawPaint.setColor(getCouleurSelectionne());
                    }
                    TD.invalidate();
                    break;
                case R.id.save:
                    SetOutilSel("Save");
                    break;
                case R.id.triangle:
                    SetOutilSel("Triangle");
                    break;
                case R.id.undo:
                    SetOutilSel("Undo");
                    break;
                case R.id.LTrait:
                    SetOutilSel("Ltraits");
                    LargeurFragment lt = LargeurFragment.newInstance();
                    lt.show(getSupportFragmentManager(), "Fragment");
                    SetOutilSel("Crayon");
                    break;
            }

            //juste pour les tests et verifications
            System.out.println(CouleurSelectionne);
            System.out.println(OutilSelectionne);


        }
    }

    private class EcouteurTouch implements View.OnTouchListener {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float touchX = event.getX();
            float touchY = event.getY();
            Paint crayontemp = new Paint();

            crayontemp.setStrokeWidth(LT); // largeur du trait
            crayontemp.setStyle(Paint.Style.STROKE); // assure que le t rait sois plein


            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                switch (getOutilSelectionne()) {
                    case "Crayon":

                        Ligne = new Traits();
                        System.out.println("patate1");
                        crayontemp.setColor(getCouleurSelectionne());
                        Ligne.DrawPaint = crayontemp;
                        Ligne.PathTraits.moveTo(touchX, touchY);

                        break;

                    case "Efface":
                        effaceur = new Effacer();
                        System.out.println("patate2");
                        crayontemp.setColor(((ColorDrawable) Tableau.getBackground()).getColor());
                        effaceur.DrawPaint = crayontemp;
                        effaceur.PathEfface.moveTo(touchX, touchY);

                        break;

                    case "Rectangle":
                        rectangle = new Rectangle();
                        System.out.println("patate5");
                        crayontemp.setColor(getCouleurSelectionne());
                        rectangle.DrawPaint = crayontemp;
                        rectangle.xg = touchX;
                        rectangle.yhg = touchY;


                        break;

                    case "Circle":
                        cercle = new Cercle();
                        System.out.println("patate7");
                        crayontemp.setColor(getCouleurSelectionne());
                        cercle.DrawPaint = crayontemp;
                        cercle.x = touchX;
                        cercle.y = touchY;


                        break;

                    case "Pipette":
                        int color = TD.getBitmapImage().getPixel((int) touchX, (int) touchY);
                        setcolorsel(color);
                        SetOutilSel("Crayon");
                        break;

                    case "Triangle":
                        if (PointExiste == false) {
                            triangle = new Triangle();
                            crayontemp.setColor(getCouleurSelectionne());
                            triangle.DrawPaint = crayontemp;
                            triangle.xs = touchX;
                            triangle.ys = touchY;
                            PointExiste = true;
                        } else {
                            triangle.xbd = touchX;
                            triangle.ybd = touchY;

                            TableauTriangle.add(triangle);
                            TD.invalidate(); // force a appeler la function ondraw
                            PointExiste = false;
                        }

                        break;

                }


            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                switch (getOutilSelectionne()) {
                    case "Crayon":
                        if (Ligne != null) {
                            System.out.println("patate3");
                            Ligne.PathTraits.lineTo(touchX, touchY);
                            TableauTrait.add(Ligne);
                            TD.invalidate(); // force a appeler la function ondraw
                        }
                        break;

                    case "Efface":
                        if (effaceur != null) {
                            System.out.println("patate4");
                            effaceur.PathEfface.lineTo(touchX, touchY);
                            TableauEffaces.add(effaceur);
                            TD.invalidate(); // force a appeler la function ondraw
                        }
                        break;

                    case "Rectangle":
                        if (rectangle != null) {
                            System.out.println("patate6");
                            rectangle.xd = touchX;
                            rectangle.ybd = touchY;
                            TableauRec.add(rectangle);
                            TD.invalidate(); // force a appeler la function ondraw
                        }
                        break;

                    case "Circle":
                        if (cercle != null) {
                            System.out.println("patate8");
                            cercle.CalculRadus(touchX, touchY);
                            TableauCercle.add(cercle);
                            TD.invalidate(); // force a appeler la function ondraw
                        }
                        break;

                    case "Triangle":
                        if (triangle != null) {
                            triangle.xbg = touchX;
                            triangle.ybg = touchY;


                        }
                        break;

                }
            }


            return true;
        }

    }

    private class Tableau_dessin extends View {

        public Tableau_dessin(Context context) {
            super(context);
        }

        public Bitmap getBitmapImage() {

            this.buildDrawingCache();
            Bitmap bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();

            return bitmapImage;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (Traits t : TableauTrait) {
                t.DrawCanvas(canvas);
            }

            for (Rectangle R : TableauRec) {
                R.DrawCanvas(canvas);
            }

            for (Cercle C : TableauCercle) {
                C.DrawCanvas(canvas);
            }
            for (Triangle tr : TableauTriangle) {
                tr.DrawCanvas(canvas);
            }

            for (Effacer T : TableauEffaces) {
                T.DrawCanvas(canvas);
            }

        }
    }
}