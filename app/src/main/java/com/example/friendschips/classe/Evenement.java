package com.example.friendschips.classe;

import android.graphics.Color;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mderoubaix on 18/07/2017.
 */
public class Evenement {


    private int itemAvatar;
    private int idEvenement;
    private int idUtilisateurEvnt;
    private String nom;
    private String dateDebut;
    private String dateFin;
    private String message;

    private Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdUtilisateurEvnt() {
        return idUtilisateurEvnt;
    }

    public void setIdUtilisateurEvnt(int idUtilisateurEvnt) {
        this.idUtilisateurEvnt = idUtilisateurEvnt;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getItemAvatar() {
        return itemAvatar;
    }

    public void setItemAvatar(int itemAvatar) {
        this.itemAvatar = itemAvatar;
    }


    public Evenement(){}

    public Evenement(int _idEvenement,int _idUtilisateurEvnt, String _nom, String _dateDebut, String _dateFin, String _message)
    {
        this.idEvenement = _idEvenement;
        this.idUtilisateurEvnt = _idUtilisateurEvnt;
        this.nom = _nom;
        this.dateDebut = _dateDebut;
        this.dateFin = _dateFin;
        this.message = _message;
    }

    public Evenement( int _idUtilisateurEvnt,String _nom, String _dateDebut, String _dateFin, String _message)
    {
        this.idUtilisateurEvnt = _idUtilisateurEvnt;
        this.nom = _nom;
        this.dateDebut = _dateDebut;
        this.dateFin = _dateFin;
        this.message = _message;
    }


    public Evenement( int _itemAvatar, String _nom, String _itemPeriode)
    {
        this.itemAvatar = _itemAvatar;
        this.nom = _nom;
        this.dateDebut = _itemPeriode;

    }

    public boolean AjouterEvenement(String _typeEvnt, Evenement _evnt)
    {
        boolean okInsert = false;

        try{

            String query = "insert into Evenement(idUtilisateurEvnt,nomEvnt, dateDebut, dateFin,messageEvnt) values ("+_evnt.getIdUtilisateurEvnt()+",'"
                    + _evnt.getNom() + "','" + _evnt.getDateDebut() + "','" + _evnt.getDateFin() + "','" + _evnt.getMessage() + "');\n";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

            query = "Select idEvenement from Evenement where nomEvnt ='"+_evnt.getNom()+"';";

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next())
            {
                _evnt.setIdEvenement(rs.getInt(1));
            }

            if(_typeEvnt.equals("Food"))
            {
                query = "Insert into FoodEvenement values("+_evnt.getIdEvenement()+","+_evnt.getIdUtilisateurEvnt()+");";

            }else  if(_typeEvnt.equals("Birth"))
            {
                query = "Insert into BirthdayEvenement values("+_evnt.getIdEvenement()+","+_evnt.getIdUtilisateurEvnt()+");";

            }else{
                query = "Insert into DrinkEvenement values("+_evnt.getIdEvenement()+","+_evnt.getIdUtilisateurEvnt()+");";
            }

            stmt = con.createStatement();

            stmt.executeUpdate(query);

            con.close();

            return okInsert = true;


        }catch (SQLException se)
        {
            okInsert = false;
            Log.e("error here 1 : ", "######################################" + se.getMessage());
        }

        return okInsert;
    }

    public List<Evenement> ListeEvenement()
    {
        List<Evenement> evnts = new ArrayList<>();

        try {

            String query = "Select nomEvnt, dateDebut, dateFin from Evenement ;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                evnts.add(new Evenement(Color.parseColor("#FFF54C73"),rs.getString(1), rs.getString(2)+"_"+rs.getString(3)));
            }

            con.close();


        }catch (SQLException se)
        {

            Log.e("error here 1 : ", "######################################" + se.getMessage());
        }
        return evnts ;
    }
}
