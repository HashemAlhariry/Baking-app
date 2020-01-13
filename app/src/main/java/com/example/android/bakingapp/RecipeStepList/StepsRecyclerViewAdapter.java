package com.example.android.bakingapp.RecipeStepList;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.StepDetails.PortraitStepDetail;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeIngredients.RecipeIngredients;
import com.example.android.bakingapp.StepDetails.RecipestepFragment;
import com.example.android.bakingapp.food.Food;
import com.example.android.bakingapp.food.Ingredients;
import com.example.android.bakingapp.food.Steps;


public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.ViewHolder>
{

    private Food food;
    private Context mContext;
    private Steps step;
    private boolean check;
    private TextView textview;
    public StepsRecyclerViewAdapter(Context context, Food food,boolean check,TextView textView)
    {
        this.food = food;
        this.mContext=context;
        this.check=check;
        this.textview=textView;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipestepitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {


        if(position==0)
        {
            holder.mTextView.setText("Recipe Ingredients");
            final Ingredients[] ingredients=food.getIngredients();
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, RecipeIngredients.class);
                    intent.putExtra("ingredients",ingredients);
                    mContext.startActivity(intent);
                }
            });

        }
        else
        {

            holder.mTextView.setText(getRecipeDescription(position-1,food));

            if(!check) {
                holder.mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Steps[] Step = food.getSteps();
                        step = Step[position - 1];

                        Intent intent = new Intent(mContext, PortraitStepDetail.class);
                        intent.putExtra("position", (position - 1));
                        intent.putExtra("food", food);
                        intent.putExtra("step", step);
                        mContext.startActivity(intent);


                    }
                });
            }
            else
            {
                holder.mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Steps[] Step = food.getSteps();
                        step = Step[position - 1];

                        if(!step.getVideoURL().equals(""))
                        {initiateFragment(step);}

                        textview.setText(step.getDescription());


                    }
                });
            }

        }




    }
    private void initiateFragment(Steps step)
    {

        RecipestepFragment fragment = new RecipestepFragment(step);
        FragmentManager fragmentManager= ((FragmentActivity)mContext).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.videoviewtablet,fragment);
        fragmentTransaction.commit();

    }

    private String getRecipeDescription(int position, Food ob)
    {

       Steps[] steps=ob.getSteps();

      return   steps[position].getShortDescription();
    }

    @Override
    public int getItemCount()
    {
        return food.getSteps().length +1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.text_list_item) TextView mTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }




}
