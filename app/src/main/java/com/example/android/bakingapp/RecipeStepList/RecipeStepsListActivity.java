package com.example.android.bakingapp.RecipeStepList;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.food.Food;



public class RecipeStepsListActivity extends AppCompatActivity
{

    private Food food;
    private boolean check;
    @BindView(R.id.textviewtablet) TextView mTextViewTable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_step);



        check=getResources().getBoolean(R.bool.isTablet);
        if(check)
            ButterKnife.bind(this);

        if(savedInstanceState ==null)
        {
            food = (Food) getIntent().getSerializableExtra("foodObject");
            setTitle(food.getName());
            initiateFragment();




        }

    }


    private void initiateFragment()
    {

        RecipeStepsListFragment fragment = new RecipeStepsListFragment(food,check,mTextViewTable);
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.placeholder,fragment).commit();

    }

}
