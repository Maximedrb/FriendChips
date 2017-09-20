package com.example.friendschips.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.ItemFriend;
import com.example.friendschips.classe.ItemFriendAdapter;
import com.example.friendschips.classe.ItemUtilisateurAdapter;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.friendschips.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MaximeDrx on 06/10/2016.
 */
public class GestionUtilisateursFragment extends Fragment{

    private RelativeLayout rlGestion;
    private List<Utilisateur> items ;

    private Connexion con;
    private Utilisateur user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGestion =  inflater.inflate(R.layout.fragment_gestionutilisateurs, container, false);



        ListView listItem = (ListView)viewGestion.findViewById(R.id.listeItemUtilisateur);

        List<Utilisateur> items = genererItems();
        items = genererItems();

        ItemUtilisateurAdapter adapter  = new ItemUtilisateurAdapter(viewGestion.getContext(), items);
        listItem.setAdapter(adapter);




        return viewGestion;



    }

    private List<Utilisateur> genererItems(){
        List<Utilisateur> items = new ArrayList<Utilisateur>();
        items.add(new Utilisateur("Maxime",Color.BLACK));
        items.add(new Utilisateur("Kevin",Color.BLUE));
        items.add(new Utilisateur("Logan",Color.GREEN));

        return items;
    }

}
