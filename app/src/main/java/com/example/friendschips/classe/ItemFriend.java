package com.example.friendschips.classe;

/**
 * Created by MaximeDrx on 15/06/2017.
 */
public class ItemFriend {



    private int itemAvatar;
    private String itemPseudo;



    private boolean itemInvite;

    public ItemFriend(int _itemAvatar, String _itemPseudo, boolean _itemInvite) {
        this.itemAvatar = _itemAvatar;
        this.itemPseudo = _itemPseudo;
        this.itemInvite = _itemInvite;


    }

    public void setItemPseudo(String itemPseudo) {
        this.itemPseudo = itemPseudo;
    }

    public void setItemAvatar(int itemAvatar) {
        this.itemAvatar = itemAvatar;
    }

    public int getItemAvatar() {
        return itemAvatar;
    }

    public String getItemPseudo() {
        return itemPseudo;
    }

    public boolean isItemInvite() {
        return itemInvite;
    }

    public void setItemInvite(boolean itemInvite) {
        this.itemInvite = itemInvite;
    }
}
