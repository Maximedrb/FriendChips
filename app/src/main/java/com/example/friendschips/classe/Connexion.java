package com.example.friendschips.classe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.friendschips.friendschips.Inscription;
import com.example.friendschips.friendschips.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import static android.os.StrictMode.setThreadPolicy;

/**
 * Created by mderoubaix on 13/07/2017.
 */
public class Connexion {

    public Connection getCon() {
        return con;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    public String getDb() {
        return db;
    }

    public String getIp() {
        return ip;
    }

    public void setCon(Connection con) {
        this.con = con;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private Connection con;
    private String userName,pass,db,ip;

    public Connexion()
    { /* ip = "87.98.169.81:1433";
        db = "FriendsChips";
        userName = "Maxime";
        pass = "Azerty123";*/
        ip = "88.190.158.218:1433";
        db = "FriendChips";
        userName = "FCAdmin";
        pass = "Fr1endCh1ps";
        con = connectionclass(userName, pass, db, ip);
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

    public boolean ConnexionApp(int _idUtilisateur)
    {
        boolean okDec = false;
        try
        {
            Calendar c = Calendar.getInstance();
            String dateNow = c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR);
            String heureNow = String.valueOf(c.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(c.get(Calendar.MINUTE));

            String query = "insert into Connexion(dateConnexion,heureConnexion,idUtilisateur)values('"+dateNow+"','"+ heureNow +"',"+_idUtilisateur+");";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();

            okDec = true;

        }
        catch (SQLException se)
        {
            Log.e("error here 1 :", "######################################" + se.getMessage());
            okDec = false;
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
            okDec = false;
        }

        return okDec;
    }

  public boolean Deconnexion(int _idUtilisateur)
  {
      boolean okDec = false;
      try
      {
      String   query = "Delete from Connexion where idUtilisateur = "+_idUtilisateur+";";
      Statement stmt = con.createStatement();

      stmt.executeUpdate(query);

        con.close();

       okDec = true;

      }
      catch (SQLException se)
      {
          Log.e("error here 1 : ", "######################################" + se.getMessage());
      }
      catch (Exception e)
      {
          Log.e("error here 3 : ", e.getMessage());
      }

      return okDec;
  }
}

