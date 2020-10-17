package com.example.myapplication.ui.menu;

class CosmicBody{
    private String name;
    private int categoryID;

    public CosmicBody(String name, int categoryID) {
        this.name = name;
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "ComicBody{" +
                "name='" + name + '\'' +
                ", categoryID=" + categoryID +
                '}';
    }
}