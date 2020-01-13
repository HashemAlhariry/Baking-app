package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.android.bakingapp.RecipeStepList.RecipeStepsListActivity;
import com.example.android.bakingapp.food.Food;



public class RecipesRecyclerViewAdapter extends RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder> {


    private Food[]food;
    private Context mContext;




    public RecipesRecyclerViewAdapter(Food[] food, Context mContext)
    {
        this.food = food;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_recyclerview_item, parent, false);
        RecipesRecyclerViewAdapter.ViewHolder holder = new RecipesRecyclerViewAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.food.setText(food[position].getName());

        holder.food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipesSteps = new Intent(mContext, RecipeStepsListActivity.class);

                Food object=food[position];
                recipesSteps.putExtra("foodObject",  object);
                mContext.startActivity(recipesSteps);
            }
        });


    }

    @Override
    public int getItemCount() {
        return food.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.foodtextview) TextView food;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }


    }
}
