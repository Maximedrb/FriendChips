package com.example.friendschips.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.friendschips.friendschips.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class AddFriendFragment extends Fragment
{
  private List<String> listeElmts = new ArrayList();
  private EditText edtDateDebut,edtHeureDebut;
  private EditText edtDateFin,edtHeureFin;
  private int mYear, mMonth, mDay, mHour, mMinute;
  public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {


    final View view = layoutInflater.inflate(R.layout.fragmentfood_add, viewGroup, false);


    final CheckBox cbCourseAFaire = (CheckBox)view.findViewById(R.id.cbCourseAFaire);



    cbCourseAFaire.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

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


               /* npCmpt.setMaxValue(100);
                npCmpt.setMinValue(0);
                npCmpt.setWrapSelectorWheel(false);*/

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


      return view;
    }


  }