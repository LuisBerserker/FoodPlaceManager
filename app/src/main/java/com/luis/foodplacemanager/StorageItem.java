package com.luis.foodplacemanager;

import java.util.Date;

/**
 * Created by Luis on 01.09.2017.
 */

public class StorageItem {
    public int ItemID;
    public Date expirationDate;
    public boolean expires;
    public StorageItem(int ItemID, Date expirationDate, boolean expires){
        this.ItemID=ItemID;
        this.expirationDate=expirationDate;
        this.expires=expires;
    }
    public StorageItem(int ItemID, Date expirationDate, int expires){
        this.ItemID=ItemID;
        this.expirationDate=expirationDate;
        if (expires==0){
            this.expires=true;
        }
        else {
            this.expires=false;
        }
    }
    public int getItemID(){
        return ItemID;
    }
    public Date getExpirationDate(){
        return expirationDate;
    }
    public boolean getExpires(){
        return expires;
    }
}
