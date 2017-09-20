package com.example.friendschips.classe;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.friendschips.friendschips.R;

import java.util.List;

/**
 * Created by MaximeDrx on 15/06/2017.
 */
public class ItemUtilisateurAdapter extends ArrayAdapter<Utilisateur> {



    //tweets est la liste des models à afficher
    public ItemUtilisateurAdapter(Context context, List<Utilisateur> items) {
        super(context, 0, items);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlisteutilisateur,parent, false);
        }

        ItemViewHolder viewHolder = (ItemViewHolder) convertView.getTag();
        if(viewHolder == null){

            viewHolder = new ItemViewHolder();
            viewHolder.itemPseudo = (TextView) convertView.findViewById(R.id.itemPseudoUtilisateur);
            viewHolder.itemAvatar = (ImageView) convertView.findViewById(R.id.itemAvatarUtilisateur);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Utilisateur item = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.itemPseudo.setText(item.getPseudo());
        viewHolder.itemAvatar.setImageDrawable(new ColorDrawable(item.getItemAvatar()));

        return convertView;
    }

    private class ItemViewHolder{
        public TextView itemPseudo;
        public ImageView itemAvatar;

    }
}
