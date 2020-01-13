package com.example.android.bakingapp.food;

import java.io.Serializable;

public class Ingredients  implements Serializable
{
   private String quantity;
   private String measure;
   private String ingredients;

    public Ingredients(String quantity, String measure, String ingredients) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredients = ingredients;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredients() {
        return ingredients;
    }


}
