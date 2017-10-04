package com.example.friendschips.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Evenement;
import com.example.friendschips.classe.ItemFriend;
import com.example.friendschips.classe.ItemFriendAdapter;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.friendschips.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MaximeDrx on 21/03/2017.
 */
public class AddDrinksFragment extends Fragment {

    private List<String> listeElmts = new ArrayList();
    private EditText edtDateDebut,edtHeureDebut;
    private EditText edtDateFin,edtHeureFin;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Connexion con = new Connexion();
    private Evenement event = new Evenement();

    private Utilisateur user = new Utilisateur();

    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {


        final View view = layoutInflater.inflate(R.layout.fragmentdrinks_add, viewGroup, false);
        final Button btAddDrink = (Button)view.findViewById(R.id.btAddDrink);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        final EditText etNomEvt = (EditText)view.findViewById(R.id.etNomEvtD);
        final EditText etMessageEvt = (EditText)view.findViewById(R.id.etMessageEvtD);

        Intent i = getActivity().getIntent();
        user = i.getExtras().getParcelable("userConnecter");
        user.setCon(con.getCon());
        event.setCon(con.getCon());

        edtDateDebut = (EditText)view.findViewById(R.id.edtDateDebutD);
        edtHeureDebut = (EditText)view.findViewById(R.id.edtHeureDebutD);
        edtDateFin = (EditText)view.findViewById(R.id.edtDateFinD);
        edtHeureFin = (EditText)view.findViewById(R.id.edtHeureFinD);

        edtDateDebut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Get Current Date
                if (hasFocus) {
                    // Get Current Date

                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    edtDateDebut.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });

        edtHeureDebut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Get Current Date
                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    edtHeureDebut.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, true);

                    timePickerDialog.show();
                }
            }
        });

        edtDateFin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Get Current Date
                if (hasFocus) {
                    // Get Current Date

                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    edtDateFin.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });

        edtHeureFin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                // Get Current Date
                if(hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    edtHeureFin.setText(hourOfDay + ":" + minute);
                                }

                            }, mHour, mMinute, true);

                    timePickerDialog.show();
                }
            }
        });

        btAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View viewPopInviteFriend = getActivity().getLayoutInflater().inflate(R.layout.popup_invitefriends, null);

                Evenement newEvnt = new Evenement(user.getIdUtilisateur(),etNomEvt.getText().toString(),edtDateDebut.getText().toString()+"_"+edtHeureDebut.getText().toString(),
                        edtDateFin.getText().toString()+"_"+ edtHeureDebut.getText().toString(),etMessageEvt.getText().toString());

                event.AjouterEvenement("Drink",newEvnt,Color.parseColor("#FF366AC4"));

                ListView listItem = (ListView) viewPopInviteFriend.findViewById(R.id.listInviteFriend);
                List<Utilisateur> items = user.ListeAmis(String.valueOf(user.getIdUtilisateur()));

               ItemFriendAdapter adapter = new ItemFriendAdapter(viewPopInviteFriend.getContext(), true, items);
                listItem.setAdapter(adapter);


                alertDialog.setView(viewPopInviteFriend).setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }
                }).setNegativeButton(R.string.Annuler, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                alertDialog.create().show();

            }
        });

        return view;
    }

    private List<ItemFriend> genererItems(){
        List<ItemFriend> items = new ArrayList<ItemFriend>();
        items.add(new ItemFriend(Color.BLACK, "Florent",false));
        items.add(new ItemFriend(Color.BLUE, "Kevin",false));
        items.add(new ItemFriend(Color.GREEN, "Logan",false));
        items.add(new ItemFriend(Color.RED, "Mathieu",false));
        items.add(new ItemFriend(Color.GRAY, "Willy", false));
        items.add(new ItemFriend(Color.BLUE, "Kevin", false));
        items.add(new ItemFriend(Color.GREEN, "Logan", false));
        items.add(new ItemFriend(Color.RED, "Mathieu", false));
        items.add(new ItemFriend(Color.GRAY, "Willy", false));
        return items;
    }
}
