package com.hiba.projet1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LargeurFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LargeurFragment extends DialogFragment {

    SeekBar Sb;
    TextView Titre;
    int ValeurSb;


    public LargeurFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LargeurFragment newInstance() {
        LargeurFragment fragment = new LargeurFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        ViewGroup rootView = (ViewGroup ) inflater.inflate(R.layout.largeurtrait, null);

        Sb = rootView.findViewById(R.id.seekBar);
        Titre = rootView.findViewById(R.id.textLT);

        Ecouteur SeekB = new Ecouteur();
        Sb.setOnSeekBarChangeListener(SeekB);


        builder.setView(rootView)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                       MainActivity ma =  (MainActivity)getActivity();
                       ma.LT = ValeurSb;


                    }
                })
                .setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss(); // fermer la boîte de dialogue
                    }
                });

        return builder.create();
    }
    private class Ecouteur implements SeekBar.OnSeekBarChangeListener{


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ValeurSb = progress;
            Titre.setText("Largeur du trait : " + progress);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

}