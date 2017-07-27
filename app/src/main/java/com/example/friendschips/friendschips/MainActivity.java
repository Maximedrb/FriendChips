package com.example.friendschips.friendschips;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Utilisateur;

import com.example.friendschips.menu.Menu_App;

import static android.os.StrictMode.setThreadPolicy;



public class MainActivity extends AppCompatActivity {

    private EditText edtNom;
    private EditText edtPass;

    private String userName,pass,db,ip;

    private Utilisateur user = new Utilisateur();
    private Connexion con;
    private ShareButton shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNom = (EditText)findViewById(R.id.etPseudo);
        edtPass = (EditText)findViewById(R.id.etPass);

        // #If Debug
        {  edtNom.setText("Maximedrx");
            edtPass.setText("a");
        }
        // #Else
        {

        }
        // #EndIf




        con = new Connexion();
        //Declaration des variables de connexion
        Button btInscription = (Button) findViewById(R.id.btInscritption);


        final Button connexion = (Button)findViewById(R.id.btConnexion);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // Connect to database
                    if (con == null) {
                        Toast.makeText(MainActivity.this, "Vérifiez votre connexion!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Change below query according to your own database.

                        String query = "select * from Utilisateur  where pseudo = '" + edtNom.getText().toString() + "' and mtp = '" + edtPass.getText().toString() + "' ; ";
                        Statement stmt = con.getCon().createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        if (rs.next()) {

                            user = new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));

                            query = "select * from Connexion  where idUtilisateur = " + user.getIdUtilisateur() + "; ";

                            Toast.makeText(MainActivity.this, "Verification de l'utilisateur", Toast.LENGTH_SHORT).show();
                            rs = stmt.executeQuery(query);

                            if(!rs.next())
                            {

                                try{

                                    if(con.ConnexionApp(user.getIdUtilisateur()))
                                    {
                                        Toast.makeText(MainActivity.this, "Connexion avec succès", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(MainActivity.this, Menu_App.class);
                                        i.putExtra("userConnecter",user);

                                        startActivity(i);
                                        finish();
                                    }else{
                                        Toast.makeText(MainActivity.this, "Il y a un problème \nlors de la connexion", Toast.LENGTH_SHORT).show();
                                    }


                                }
                                catch (Exception e)
                                {
                                    Log.e("error here 3 : ", e.getMessage());
                                }

                            }else{
                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case DialogInterface.BUTTON_POSITIVE:

                                               if( con.Deconnexion(user.getIdUtilisateur()))
                                               {
                                                   con = new Connexion();

                                                   if(con.ConnexionApp(user.getIdUtilisateur()))
                                                   {
                                                       Toast.makeText(MainActivity.this, "Connexion avec succès", Toast.LENGTH_SHORT).show();


                                                       Intent i = new Intent(MainActivity.this, Menu_App.class);
                                                       i.putExtra("userConnecter",user);

                                                       startActivity(i);
                                                       finish();
                                                       break;
                                                   }else{

                                                       Toast.makeText(MainActivity.this, "Il y a un problème \nlors de la connexion", Toast.LENGTH_SHORT).show();
                                                       Toast.makeText(MainActivity.this, "Il y a un problème \nlors de la connexion", Toast.LENGTH_SHORT).show();
                                                   }
                                               }else{
                                                   Toast.makeText(MainActivity.this, "Nous rencontrons un problème pour\nfermer votre ancienne session.", Toast.LENGTH_SHORT).show();
                                               }

                                            case DialogInterface.BUTTON_NEGATIVE:

                                                break;
                                        }
                                    }
                                };

                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setMessage("Vous êtes déjà connectez,\nVoulez-vous fermer votre ancienne session?").setPositiveButton("Oui", dialogClickListener)
                                        .setNegativeButton("Non", dialogClickListener).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Le pseudo de l'utilisateur ou le mot de passe sont incorrect", Toast.LENGTH_SHORT).show();

                        }
                    }
                } catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

        });

        btInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Inscription.class);
                startActivity(i);
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                    + "databaseName=" + database + ";user=" + user
                    + ";password=" + password + ";";
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



