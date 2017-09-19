package com.example.friendschips.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Evenement;
import com.example.friendschips.classe.ItemEvent;
import com.example.friendschips.classe.ItemEventAdapter;
import com.example.friendschips.classe.ItemFriend;
import com.example.friendschips.classe.ItemFriendAdapter;
import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.friendschips.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MaximeDrx on 06/10/2016.
 */
public class GestionFriendFragment extends Fragment{

    private RelativeLayout rlGestion;



    private List<ItemFriend> items ;

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
        View viewGestion =  inflater.inflate(R.layout.fragment_gestionfriends, container, false);



        ListView listItem = (ListView)viewGestion.findViewById(R.id.listItemFriend);

        List<ItemFriend> items = genererItems();
        items = genererItems();

        ItemFriendAdapter adapter  = new ItemFriendAdapter(viewGestion.getContext(),true, items);
        listItem.setAdapter(adapter);




        return viewGestion;



    }

    private List<ItemFriend> genererItems(){
        List<ItemFriend> items = new ArrayList<ItemFriend>();
        items.add(new ItemFriend(Color.BLACK, "Florent",false));
        items.add(new ItemFriend(Color.BLUE, "Kevin",false));
        items.add(new ItemFriend(Color.GREEN, "Logan",false));
        items.add(new ItemFriend(Color.RED, "Mathieu",false));
        items.add(new ItemFriend(Color.GRAY, "Willy",false));
        return items;
    }

}
