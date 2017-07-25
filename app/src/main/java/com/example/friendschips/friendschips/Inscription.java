package com.example.friendschips.friendschips;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
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

    private Connexion con = new Connexion();
    private String userName,pass,db,ip;
    private Utilisateur user = new Utilisateur();

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


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inscription.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

       /* ip = "192.168.0.101:1433";
        db = "FriendChips";
        userName = "FCAdmin";
        pass = "Fr1endCh1ps";
        con = connectionclass(userName, pass, db, ip);*/

        Button btInsciption = (Button)findViewById(R.id.btAddInscription);

        btInsciption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (con.getCon() == null) {
                        Toast.makeText(Inscription.this, "Vérifier votre connexion internet!", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println(etConfMtp.getText().toString() + "###########" + etMtp.getText().toString());

                        if (etConfMtp.getText().toString().equals(etMtp.getText().toString())) {

                            user.setCon(con.getCon());
                            user = new Utilisateur(etNom.getText().toString(), etPrenom.getText().toString(), etPseudo.getText().toString()
                                    , etMail.getText().toString(), etMtp.getText().toString(), etDate.getText().toString());

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



    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);
        Connection connection = null;
        String ConnURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://" + server + ";"
                    + "databaseName=" + database + ";user=" + user + ";password="
                    + password + ";";
            connection = DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", "######################################" + se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
