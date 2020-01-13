package com.example.android.bakingapp.StepDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.food.Food;
import com.example.android.bakingapp.food.Steps;

public class PortraitStepDetail extends AppCompatActivity
{

    private Steps step;
    private TextView mTextView;
    private int position;
    private Food food;
    private Button bNext,bPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait_step_detail);

        bNext = findViewById(R.id.next);
        bPrevious = findViewById(R.id.previous);
        mTextView = findViewById(R.id.recipestepinstruction);


        if(savedInstanceState!=null)
        {
           step= (Steps) savedInstanceState.getSerializable("step");
           food= (Food) savedInstanceState.getSerializable("food");
           position= savedInstanceState.getInt("position");

            setTitle(step.getShortDescription());
            mTextView.setText(step.getShortDescription());

            prepareActivity();
        }
        else
        {

            if (getIntent().hasExtra("step"))
            {

                step = (Steps) getIntent().getSerializableExtra("step");
                setTitle(step.getShortDescription());

                mTextView.setText(step.getShortDescription());
                position = getIntent().getIntExtra("position", 0);
                food = (Food) getIntent().getSerializableExtra("food");

                if (!step.getVideoURL().equals(""))
                {
                    initiateFragment(step);
                }
                prepareActivity();

            }

        }






    }

    private void prepareActivity()
    {
        if (position == 0)
            bPrevious.setEnabled(false);
        if (position == food.getSteps().length - 1)
            bNext.setEnabled(false);

        bPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PortraitStepDetail.this, PortraitStepDetail.class);
                intent.putExtra("position", (position - 1));
                intent.putExtra("food", food);
                Steps[] steps = food.getSteps();
                Steps step = steps[position - 1];
                intent.putExtra("step", step);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PortraitStepDetail.this, PortraitStepDetail.class);
                intent.putExtra("position", (position + 1));
                intent.putExtra("food", food);
                Steps[] steps = food.getSteps();
                Steps step = steps[position + 1];
                intent.putExtra("step", step);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("step",step);
        outState.putSerializable("food",food);
        outState.putInt("position",position);
    }

    private void initiateFragment(Steps step)
    {

        RecipestepFragment fragment = new RecipestepFragment(step);
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.videofragment,fragment);
        fragmentTransaction.commit();

    }
}
