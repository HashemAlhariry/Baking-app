package com.example.android.bakingapp.RecipeIngredients;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.food.Ingredients;


public class RecipeIngredientsRecyclerView extends RecyclerView.Adapter<RecipeIngredientsRecyclerView.ViewHolder> {

    private Ingredients[]ingredients;

    public RecipeIngredientsRecyclerView(Ingredients[] ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipeingredients, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.quantity.setText(ingredients[position].getQuantity());
        holder.measure.setText(ingredients[position].getMeasure());
        holder.ingredient.setText(ingredients[position].getIngredients());
    }

    @Override
    public int getItemCount() {
        return  ingredients.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quantity) TextView quantity;
        @BindView(R.id.measure) TextView measure;
        @BindView(R.id.ingredient) TextView ingredient;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }
  }
