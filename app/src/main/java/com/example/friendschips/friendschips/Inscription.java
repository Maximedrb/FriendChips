package com.example.friendschips.friendschips;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.menu.Menu_App;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.os.StrictMode.setThreadPolicy;


/**
 * Created by MaximeDrx on 11/10/2016.
 */
public class Inscription extends AppCompatActivity {

    private Connexion con ;
    private Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText etNom = (EditText)findViewById(R.id.etNomInsc);
        final EditText etPrenom = (EditText)findViewById(R.id.etPrenomInsc);
        final EditText etPseudo = (EditText)findViewById(R.id.etPseudoInsc);
        final EditText etMtp = (EditText)findViewById(R.id.etPassInsc);
        final EditText etConfMtp = (EditText)findViewById(R.id.etConfPassInsc);
        final EditText etMail = (EditText)findViewById(R.id.etMailInsc);
        final EditText etDate = (EditText)findViewById(R.id.etNaissanceInsc);

        con = new Connexion();
        user = new Utilisateur();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inscription.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button btInsciption = (Button)findViewById(R.id.btAddInscription);

        btInsciption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (con.getCon() == null) {
                        Toast.makeText(Inscription.this, "Vérifier votre connexion internet!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (etConfMtp.getText().toString().equals(etMtp.getText().toString())) {


                            user = new Utilisateur(etNom.getText().toString(), etPrenom.getText().toString(), etPseudo.getText().toString()
                                    , etMail.getText().toString(), etMtp.getText().toString(), etDate.getText().toString());
                            user.setCon(con.getCon());

                            if (user.AjouterUtilisateur(user)) {
                                Toast.makeText(Inscription.this, "Inscription réussit!", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Inscription.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Inscription.this, "Problème lors de votre inscription", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Inscription.this, "Le mot de passe est incorrect", Toast.LENGTH_SHORT).show();
                        }


                    }

                }
                catch (Exception e)
                {
                    Log.e("error here 3 : ", e.getMessage());
                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Inscription.this, MainActivity.class);
        startActivity(i);
        finish();
    }



}
