package com.example.android.bakingapp.food;


import java.io.Serializable;

public class Food  implements Serializable {
    private String id;
    private String name;
    private Ingredients[] ingredients;
    private Steps[] steps;
    private String Servings;
    private String image;

    public Food(String id, String name, Ingredients[] ingredients, Steps[] steps, String servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        Servings = servings;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Ingredients[] getIngredients() {
        return ingredients;
    }

    public Steps[] getSteps() {
        return steps;
    }

    public String getServings() {
        return Servings;
    }

    public String getImage() {
        return image;
    }


}
