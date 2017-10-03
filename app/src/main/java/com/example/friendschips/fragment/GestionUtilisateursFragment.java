package com.example.friendschips.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.ItemEventAdapter;
import com.example.friendschips.classe.ItemFriend;
import com.example.friendschips.classe.ItemFriendAdapter;
import com.example.friendschips.classe.ItemUtilisateurAdapter;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.friendschips.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.friendschips.friendschips.R.id.view;


/**
 * Created by MaximeDrx on 06/10/2016.
 */
public class GestionUtilisateursFragment extends Fragment{

    private RelativeLayout rlGestion;

    public List<Utilisateur> getItems() {
        return items;
    }

    public void setItems(List<Utilisateur> items) {
        this.items = items;
    }

    private List<Utilisateur> items ;
    private  ListView listItem;
    private Connexion con;
    private Utilisateur user;
    private  ItemUtilisateurAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGestion =  inflater.inflate(R.layout.fragment_gestionutilisateurs, container, false);

        con = new Connexion();
        user = new Utilisateur();

        user.setCon(con.getCon());


         listItem = (ListView)viewGestion.findViewById(R.id.listeItemUtilisateur);

        //items = genererItems(viewGestion);
        items = user.ListeUtilisateur();

         adapter  = new ItemUtilisateurAdapter(viewGestion.getContext(), items);
        listItem.setAdapter(adapter);




        return viewGestion;



    }


    public void InitListEvent(View view){

        adapter  = new ItemUtilisateurAdapter(view.getContext(), user.ListeUtilisateur());
        listItem.setAdapter(adapter);


    }

}
