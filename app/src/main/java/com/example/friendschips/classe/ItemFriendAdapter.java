package com.example.friendschips.classe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
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
public class ItemFriendAdapter extends ArrayAdapter<Utilisateur> {

    private boolean invite;

    //tweets est la liste des models à afficher
    public ItemFriendAdapter(Context context,boolean _invite,List<Utilisateur> items) {
        super(context, 0, items);
        invite = _invite;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlistfriend,parent, false);
        }

        ItemViewHolder viewHolder = (ItemViewHolder) convertView.getTag();
        if(viewHolder == null){

            viewHolder = new ItemViewHolder();
            viewHolder.itemPseudo = (TextView) convertView.findViewById(R.id.itemPseudo);
            viewHolder.itemAvatar = (ImageView) convertView.findViewById(R.id.itemAvatarFriend);
            viewHolder.itemInvite = (CheckBox)convertView.findViewById(R.id.cbInvite);
            if(invite)
            {
                viewHolder.itemInvite.setVisibility(View.VISIBLE);
            }else{
                viewHolder.itemInvite.setVisibility(View.INVISIBLE);
            }
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Utilisateur item = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.itemPseudo.setText(item.getPseudo());
        viewHolder.itemAvatar.setImageBitmap(StringToBitmap(item.getPhotoProfil()));
        if(invite)
        {
            viewHolder.itemInvite.setChecked(item.isItemInvite());
        }
        return convertView;
    }

    private class ItemViewHolder{
        public TextView itemPseudo;
        public ImageView itemAvatar;
        public CheckBox itemInvite;
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (NullPointerException e) {
            e.getMessage();
            return null;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
