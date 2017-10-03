package com.example.friendschips.classe;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendschips.friendschips.R;
//import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.List;

/**
 * Created by MaximeDrx on 15/06/2017.
 */
public class ItemEventAdapter extends ArrayAdapter<Evenement> {


    //tweets est la liste des models à afficher
    public ItemEventAdapter(Context context, List<Evenement> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlistevent,parent, false);
        }

        ItemViewHolder viewHolder = (ItemViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ItemViewHolder();
            viewHolder.itemNom = (TextView) convertView.findViewById(R.id.itemNom);
            viewHolder.itemPeriode = (TextView) convertView.findViewById(R.id.itemPeriode);
            viewHolder.itemAvatar = (ImageView) convertView.findViewById(R.id.itemAvatarEvent);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Evenement item = getItem(position);

        //il ne reste plus qu'à remplir notre vue

        viewHolder.itemNom.setText(item.getNom());
        viewHolder.itemPeriode.setText(item.getDateDebut());
        viewHolder.itemAvatar.setImageDrawable(new ColorDrawable(item.getItemAvatar()));

        return convertView;
    }

    private class ItemViewHolder{
       // public int itemId;
        public TextView itemNom;
        public TextView itemPeriode;
        public ImageView itemAvatar;

    }

}
