package com.example.week4withaddedsqliteopenhelper;

public class NameDataModel {
    private int id;
    private String nameText;

    public NameDataModel(String nameText) {
        this.nameText = nameText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }
}
