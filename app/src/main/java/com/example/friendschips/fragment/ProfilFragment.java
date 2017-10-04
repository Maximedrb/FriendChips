package com.example.friendschips.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.friendschips.R;


/**
 * Created by MaximeDrx on 06/10/2016.
 */
public class ProfilFragment extends Fragment{

    private Utilisateur user = new Utilisateur();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGestion =  inflater.inflate(R.layout.fragment_profil, container, false);

        final ImageView ivProfilPhoto = (ImageView)viewGestion.findViewById(R.id.rivPhotoProfil);

        Intent i = getActivity().getIntent();
        user = i.getExtras().getParcelable("userConnecter");

        if(user.getPhotoProfil()!= null)
        {
            ivProfilPhoto.setImageBitmap(StringToBitmap(user.getPhotoProfil()));
        }

        return viewGestion;

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
