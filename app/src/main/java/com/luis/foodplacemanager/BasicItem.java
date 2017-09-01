package com.luis.foodplacemanager;

/**
 * Created by Luis on 17.08.2017.
 */

public class BasicItem {
    private int _id;
    private String _itemname;
    private String _itemdescription;
    public BasicItem(){

    }
    public BasicItem(int id, String itemname, String itemdescription){
        this._id =id;
        this._itemname = itemname;
        this._itemdescription = itemdescription;
    }
    public BasicItem(String itemname, String itemdescription){
        this._itemname = itemname;
        this._itemdescription = itemdescription;
    }
    public int getId(){
        return this._id;
    }
    public void setID(int id){
        this._id=id;
    }
    public String getItemName(){
        return this._itemname;
    }
    public void setItemName(String itemname){
        this._itemname = itemname;
    }
    public String getItemDescription(){
        return this._itemdescription;
    }
    public void set_itemdescription(String s){this._itemdescription=s;}
}
