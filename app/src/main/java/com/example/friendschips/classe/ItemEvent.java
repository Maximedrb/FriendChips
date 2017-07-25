package com.example.friendschips.classe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MaximeDrx on 15/06/2017.
 */
public class ItemEvent  {

    private int itemAvatar;
    private String itemNom;
    private String itemPeriode;

    public ItemEvent(int _itemAvatar, String _itemNom, String _itemPeriode) {
        this.itemAvatar = _itemAvatar;
        this.itemNom = _itemNom;
        this.itemPeriode = _itemPeriode;
    }

    public void setItemAvater(int itemAvater) {
        this.itemAvatar = itemAvater;
    }

    public void setItemNom(String itemNom) {
        this.itemNom = itemNom;
    }

    public void setItemPeriode(String itemPeriode) {
        this.itemPeriode = itemPeriode;
    }

    public int getItemAvatar() {
        return itemAvatar;
    }

    public String getItemNom() {
        return itemNom;
    }

    public String getItemPeriode() {
        return itemPeriode;
    }



}
