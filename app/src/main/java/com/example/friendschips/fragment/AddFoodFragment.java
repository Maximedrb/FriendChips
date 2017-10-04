package com.example.friendschips.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Evenement;
import com.example.friendschips.classe.ItemEvent;
import com.example.friendschips.classe.ItemEventAdapter;
import com.example.friendschips.classe.ItemFriend;
import com.example.friendschips.classe.ItemFriendAdapter;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.friendschips.MainActivity;
import com.example.friendschips.friendschips.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class AddFoodFragment extends Fragment
{
  private List<String> listeElmts = new ArrayList();
  private EditText edtDateDebut,edtHeureDebut;
  private EditText edtDateFin,edtHeureFin;
  private int mYear, mMonth, mDay, mHour, mMinute;
  private int yearsSt,monthsSt,daysSt,hoursSt,minsSt,secondsSt;
  private int yearsEnd,monthsEnd,daysEnd,hoursEnd,minsEnd,secondsEnd;

  private Connexion con = new Connexion();
  private Evenement event = new Evenement();

  private Utilisateur user = new Utilisateur();

  public GestionFragment getGestion() {
    return gestion;
  }

  public void setGestion(GestionFragment gestion) {
    this.gestion = gestion;
  }

  private GestionFragment gestion  = new GestionFragment();

  public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {


    final View view = layoutInflater.inflate(R.layout.fragmentfood_add, viewGroup, false);

     Intent i = getActivity().getIntent();
    user = i.getExtras().getParcelable("userConnecter");

    final CheckBox cbCourseAFaire = (CheckBox)view.findViewById(R.id.cbCourseAFaire);
    final Button btAddFood = (Button)view.findViewById(R.id.btAddFood);
    final EditText etNomEvtFood = (EditText)view.findViewById(R.id.etNomEvtFood);
    final EditText etMessageEvt = (EditText)view.findViewById(R.id.etMessageEvtFood);
    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

      user.setCon(con.getCon());
      event.setCon(con.getCon());

    cbCourseAFaire.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {



        final View viewPop = getActivity().getLayoutInflater().inflate(R.layout.popup_listecourse, null);

        if (cbCourseAFaire.isChecked()) {

          Button btAjoutElmt = (Button) viewPop.findViewById(R.id.btAddElmt);

          final EditText etNomElmt = (EditText)viewPop.findViewById(R.id.etNomElmt);
          final LinearLayout listCourse = (LinearLayout)viewPop.findViewById(R.id.listeElmt);

          btAjoutElmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(etNomElmt.getText()!= null)
              {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewPop.getContext());
                View viewCmpt = getActivity().getLayoutInflater().inflate(R.layout.compteur, null);

                final EditText npCmpt = (EditText)viewCmpt.findViewById(R.id.tbNbElmt);


                builder.setView(viewCmpt).setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener(){

                  @Override
                  public void onClick(DialogInterface dialog, int id) {
                    if(npCmpt.getText().toString() != "0")
                    {
                      LayoutParams lparams = new LayoutParams(
                              LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                      TextView tvElmt = new TextView(viewPop.getContext());
                      tvElmt.setLayoutParams(lparams);
                      tvElmt.setText(npCmpt.getText().toString() +" - "+etNomElmt.getText());
                      listCourse.addView(tvElmt);

                      listeElmts.add(npCmpt.getText().toString() +" - "+etNomElmt.getText());

                      etNomElmt.setText(null);
                    }
                  }
                }).setNegativeButton(R.string.Annuler,new DialogInterface.OnClickListener(){
                  @Override
                  public void onClick(DialogInterface dialog, int id) {

                  }
                });

                builder.create();
                builder.show();
              }

            }
          });


        }
        alertDialog.setView(viewPop).setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener(){
          @Override
          public void onClick(DialogInterface dialog, int id) {

            if(listeElmts.size() != 0) {
              LinearLayout listElmts = (LinearLayout) view.findViewById(R.id.listCourse);
              for (String elmts : listeElmts) {

                LayoutParams lparams = new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                TextView tvElmt = new TextView(viewPop.getContext());
                tvElmt.setLayoutParams(lparams);
                tvElmt.layout(50, 20, 0, 0);
                tvElmt.setTypeface(tvElmt.getTypeface(), Typeface.BOLD);
                tvElmt.setGravity(Gravity.LEFT);
                tvElmt.setText("     " + elmts.split("-")[0] + "                                     " + elmts.split("-")[1]);

                listElmts.addView(tvElmt);

              }
            }


          }
        }).setNegativeButton(R.string.Annuler,new DialogInterface.OnClickListener(){
          @Override
          public void onClick(DialogInterface dialog, int id) {
              cbCourseAFaire.setChecked(false);
          }
        });

        alertDialog.create().show();

      }
    });

    edtDateDebut = (EditText)view.findViewById(R.id.edtDateDebutF);
    edtHeureDebut = (EditText)view.findViewById(R.id.edtHeureDebutF);
    edtDateFin = (EditText)view.findViewById(R.id.edtDateFinF);
    edtHeureFin = (EditText)view.findViewById(R.id.edtHeureFinF);

    edtDateDebut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        // Get Current Date
        if (hasFocus) {
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

    btAddFood.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {



        final View viewPopInviteFriend = getActivity().getLayoutInflater().inflate(R.layout.popup_invitefriends, null);



        ListView listItem = (ListView)viewPopInviteFriend.findViewById(R.id.listInviteFriend);
        List<Utilisateur> items = user.ListeAmis(String.valueOf(user.getIdUtilisateur()));

        ItemFriendAdapter adapter = new ItemFriendAdapter(viewPopInviteFriend.getContext(),true, items);
        listItem.setAdapter(adapter);


        alertDialog.setView(viewPopInviteFriend).setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener(){
          @Override
          public void onClick(DialogInterface dialog, int id) {
              Evenement newEvnt = new Evenement(user.getIdUtilisateur(),etNomEvtFood.getText().toString(),edtDateDebut.getText().toString()+"_"+edtHeureDebut.getText().toString(),
                      edtDateFin.getText().toString()+"_"+ edtHeureDebut.getText().toString(),etMessageEvt.getText().toString());

              event.AjouterEvenement("Food",newEvnt,Color.parseColor("#FFF54C73"));

          }
        }).setNegativeButton(R.string.Annuler, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {

          }
        });

        alertDialog.create().show();

      }});
      return view;
    }

  private List<ItemFriend> genererItems(){
    List<ItemFriend> items = new ArrayList<ItemFriend>();
    items.add(new ItemFriend(Color.BLACK, "Florent",false));
    items.add(new ItemFriend(Color.BLUE, "Kevin",false));
    items.add(new ItemFriend(Color.GREEN, "Logan",false));
    items.add(new ItemFriend(Color.RED, "Mathieu",false));
    items.add(new ItemFriend(Color.GRAY, "Willy",false));
    items.add(new ItemFriend(Color.BLUE, "Kevin",false));
    items.add(new ItemFriend(Color.GREEN, "Logan",false));
    items.add(new ItemFriend(Color.RED, "Mathieu",false));
    items.add(new ItemFriend(Color.GRAY, "Willy",false));
    return items;
  }



  }