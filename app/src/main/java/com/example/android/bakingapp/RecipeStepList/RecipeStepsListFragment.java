package com.example.android.bakingapp.RecipeStepList;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.food.Food;

public class RecipeStepsListFragment extends Fragment
{

    private Food food;
    private StepsRecyclerViewAdapter stepsRecyclerViewAdapter;
    private boolean check;
    private TextView textView;

    public RecipeStepsListFragment() {
    }



    public RecipeStepsListFragment(Food food , Boolean check, TextView textView) {
        this.food = food;
        this.check=check;
        this.textView=textView;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("food",food);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        if(savedInstanceState!=null)
        {
            food= (Food) savedInstanceState.getSerializable("food");
        }
       View rootView= inflater.inflate(R.layout.recipes,container,false);
       RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view_listitem);
       stepsRecyclerViewAdapter = new StepsRecyclerViewAdapter(getContext(),food,check,textView);
       recyclerView.setAdapter(stepsRecyclerViewAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


       return rootView;
    }





}
