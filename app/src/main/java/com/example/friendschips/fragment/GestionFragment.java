package com.example.friendschips.fragment;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.friendschips.classe.Connexion;
import com.example.friendschips.classe.Evenement;
import com.example.friendschips.classe.ItemEventAdapter;
import com.example.friendschips.classe.ItemEvent;
import com.example.friendschips.friendschips.R;
import com.example.friendschips.menu.FoodActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by MaximeDrx on 06/10/2016.
 */
public class GestionFragment extends Fragment{


    private RelativeLayout rlGestion;

    public List<Evenement> getItems() {
        return items;
    }

    public void setItems(List<Evenement> items) {
        this.items = items;
    }

    private List<Evenement> items ;

    private Connexion con;
    private Evenement event;
    private  ItemEventAdapter adapter;



    public void setColorBackScroll(String colorBackScroll) {
        this.colorBackScroll = colorBackScroll;
    }

    public String getColorBackScroll() {
        return colorBackScroll;
    }

    private String colorBackScroll ;
    private  ListView listItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View viewGestion =  inflater.inflate(R.layout.fragment_gestion, container, false);

        con = new Connexion();
        event = new Evenement();

        event.setCon(con.getCon());


        rlGestion = (RelativeLayout)viewGestion.findViewById(R.id.rlGestion);

            rlGestion.setBackgroundColor(Color.parseColor(colorBackScroll));

        listItem = (ListView)viewGestion.findViewById(R.id.listItemEvent);

        //items = InitListEvent(viewGestion);
        items = event.ListeEvenement();

         adapter  = new ItemEventAdapter(viewGestion.getContext(), items);
         listItem.setAdapter(adapter);


        return viewGestion;

    }


   public void InitListEvent(View view){

       adapter  = new ItemEventAdapter(view.getContext(), event.ListeEvenement());
       listItem.setAdapter(adapter);


    }


}
