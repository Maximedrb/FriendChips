package com.example.friendschips.classe;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaximeDrx on 12/10/2016.
 */
public class Utilisateur  implements Parcelable{

    private String nom;
    private String prenom;
    private String pseudo;
    private String mail;
    private String mtp;
    private String dateNaissance;


    private String photoProfil;



    private boolean itemInvite;
    private Boolean actif ;
    private Connection con;



    private int idUtilisateur;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }






    public Utilisateur(){}

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMtp() {
        return mtp;
    }

    public void setMtp(String mtp) {
        this.mtp = mtp;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
    public boolean isItemInvite() {
        return itemInvite;
    }

    public void setItemInvite(boolean itemInvite) {
        this.itemInvite = itemInvite;
    }




    public void setPhotoProfil(String avatar) {
        this.photoProfil = photoProfil;
    }

  /*  public Utilisateur(String _pseudo, int _itemAvatar)
    {
        this.pseudo = _pseudo;

    }*/

    public Utilisateur(int _idUtilisateur,String _nom,String _prenom, String _pseudo,String _mtp,String _photoProfil ,String _mail, String _date)
    {
        super();
        this.idUtilisateur = _idUtilisateur;
        this.nom = _nom;
        this.prenom = _prenom;
        this.pseudo = _pseudo;
        this.photoProfil = _photoProfil;
        this.mail = _mail;
        this.mtp = _mtp;
        this.dateNaissance = _date;
    }

    public Utilisateur(String _nom,String _prenom, String _pseudo, String _mtp,String _photoProfil,String _mail,String _date)
    {
        super();
        this.nom = _nom;
        this.prenom = _prenom;
        this.pseudo = _pseudo;
        this.photoProfil = _photoProfil;
        this.mail = _mail;
        this.mtp = _mtp;
        this.dateNaissance = _date;
    }

    public Utilisateur(int _idUtilisateur, String _nom,String _prenom, String _pseudo,String _photoProfil)
    {
        super();
        this.idUtilisateur = _idUtilisateur;
        this.nom = _nom;
        this.prenom = _prenom;
        this.pseudo = _pseudo;
        this.photoProfil = _photoProfil;

    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(idUtilisateur);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(pseudo);
        dest.writeString(mail);
        dest.writeString(mtp);
        dest.writeString(photoProfil);
        dest.writeString(dateNaissance);
    }
    public static final Parcelable.Creator<Utilisateur> CREATOR = new Parcelable.Creator<Utilisateur>()
    {
        @Override
        public Utilisateur createFromParcel(Parcel source)
        {
            return new Utilisateur(source);
        }

        @Override
        public Utilisateur[] newArray(int size)
        {
            return new Utilisateur[size];
        }
    };

    public Utilisateur(Parcel in) {
        this.idUtilisateur = in.readInt();
        this.nom = in.readString();
        this.prenom = in.readString();
        this.pseudo = in.readString();
        this.mail = in.readString();
        this.mtp = in.readString();
        this.photoProfil = in.readString();
        this.dateNaissance = in.readString();
    }

    public Utilisateur GetUtilisateur(String _pseudo)
    {
        Utilisateur user = new Utilisateur();
        try {
            String query = "select * from Utilisateur  where pseudo = '" + _pseudo + "' ; ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next())
            {
              user = new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            }
            con.close();
        }catch (SQLException se)
    {
        Log.e("error here 1 : ", "######################################" + se.getMessage());
    }
    catch (Exception e)
    {
        Log.e("error here 3 : ", e.getMessage());
    }
        return user;
    }

    public boolean AjouterUtilisateur(Utilisateur _utilisateur)
    {
        boolean okInsert = false;
        try{

            String query = "insert into Utilisateur(nom,prenom,pseudo,mtp,photoProfil,mail,dateNaissance,actif) values ('"
                    + _utilisateur.getNom() + "','" + _utilisateur.getPrenom() + "','" + _utilisateur.getPseudo() + "','" + _utilisateur.getMtp() + "','"
                    +_utilisateur.getPhotoProfil()+"','"+ _utilisateur.getMail()+ "','" + _utilisateur.getDateNaissance() + "',1);";
            Statement stmt = con.createStatement();
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

    public List<Utilisateur> ListeUtilisateur(String _idUtilisateur)
    {
        List<Utilisateur> users = new ArrayList<>();

        try {

            String query = "Select idUtilisateur, nom, prenom, pseudo,photoProfil from Utilisateur where idUtilisateur !="+_idUtilisateur+" ;";



            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                users.add(new Utilisateur(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }



        }catch (SQLException se)
        {

            Log.e("error here 1 : ******* ", "######################################" + se.getMessage());
        }
        return users ;
    }

    public List<Utilisateur> ListeAmis(String _idUtilisateur)
    {
        List<Utilisateur> users = new ArrayList<>();

        try {

            String query = " Select u.idUtilisateur, nom, prenom, pseudo,photoProfil from Utilisateur u inner join EtreAmis e on u.idUtilisateur = e.idAmi where e.idUtilisateur = "+_idUtilisateur+";";



            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                users.add(new Utilisateur(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }



        }catch (SQLException se)
        {

            Log.e("error here 1 : ******* ", "######################################" + se.getMessage());
        }
        return users ;
    }

}
