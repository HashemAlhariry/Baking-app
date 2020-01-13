package com.example.android.bakingapp.RecipeIngredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.food.Ingredients;

public class RecipeIngredients extends AppCompatActivity {

    @BindView(R.id.recycler_view_ingredients)  RecyclerView recyclerView;
    private RecipeIngredientsRecyclerView recipeIngredientsRecyclerView;
    private Ingredients [] ingredients;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients);
        setTitle("RecipeIngredients");
        if(getIntent().hasExtra("ingredients"));
        {
            ingredients=(Ingredients[]) getIntent().getSerializableExtra("ingredients");

            recipeIngredientsRecyclerView  = new RecipeIngredientsRecyclerView(ingredients);
            ButterKnife.bind(this);
            recyclerView.setAdapter(recipeIngredientsRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));



        }
    }
}
