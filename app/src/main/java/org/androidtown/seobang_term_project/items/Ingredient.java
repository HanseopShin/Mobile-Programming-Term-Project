package org.androidtown.seobang_term_project.items;

/**
 * Developed by hayeon0824 on 2018-05-19.
 * Copyright (c) 2018 hayeon0824 rights reserved.
 */

/**
 * @When:
 * This data model class is for covering the items
 *
 * @Functions & Technique:
 * It has methods which will be utilized from {@link org.androidtown.seobang_term_project.recycler.viewholders.IngredientViewHolder} for checking states
 * This class is good for software architecture
 */

public class Ingredient {

    private String ingredientType;
    private int image;
    private boolean isListItem = true;
    private boolean isResultItem = true;
    private boolean isClicked = false;

    public Ingredient(String ingredientType, int image) {
        this.ingredientType = ingredientType;
        this.image = image;
    }


    /*********************
     * getters & setters *
     ********************/

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean getIsListItem() {
        return isListItem;
    }

    public void setListItem(boolean listItem) {
        this.isListItem = listItem;
    }

    public boolean getIsResult() {
        return isResultItem;
    }
}
