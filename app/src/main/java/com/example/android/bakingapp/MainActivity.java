package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import com.example.android.bakingapp.Network.NetworkUtilites;
import com.example.android.bakingapp.food.Food;
import com.example.android.bakingapp.food.Ingredients;
import com.example.android.bakingapp.food.Steps;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{

    @BindView(R.id.recycler_view_listitem)  RecyclerView recyclerView;
    private String dataRetrieved;
    private Food[] foodNames;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes);
        networkUtilites();

        ButterKnife.bind(this);
        RecipesRecyclerViewAdapter adapter = new RecipesRecyclerViewAdapter(foodNames,this);
        recyclerView.setAdapter(adapter);
        if(getResources().getBoolean(R.bool.isTablet))
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        }
        else
        {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }





    }

    public void networkUtilites()
    {

        NetworkUtilites ob = new NetworkUtilites();
        try
        {
        dataRetrieved= ob.execute().get();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        getFoodArray();
    }

    public void getFoodArray()
    {
        try {
            if (!dataRetrieved.equals(null)) {
                JSONArray ArrayObject = new JSONArray(dataRetrieved);
                foodNames =new Food[ArrayObject.length()];
                for (int i = 0; i < ArrayObject.length(); i++)
                {
                    JSONObject parentObject=ArrayObject.getJSONObject(i);
                    String id=parentObject.getString("id");
                    String name=parentObject.getString("name");


                    JSONArray ingredients = parentObject.getJSONArray("ingredients");
                    Ingredients []ob= new Ingredients[ingredients.length()];
                    for(int j=0;j<ingredients.length();j++)
                    {
                        JSONObject ingredientsObject = ingredients.getJSONObject(j);
                        String quantity =ingredientsObject.getString("quantity");
                        String measure= ingredientsObject.getString("measure");
                        String ingredient= ingredientsObject.getString("ingredient");
                        ob[j]=new Ingredients(quantity,measure,ingredient);
                    }


                    JSONArray steps=parentObject.getJSONArray("steps");
                    Steps [] obj = new Steps[steps.length()];
                    for(int j=0;j<steps.length();j++)
                    {
                        JSONObject stepsObject = steps.getJSONObject(j);
                        String idSteps =stepsObject.getString("id");
                        String shortDescription= stepsObject.getString("shortDescription");
                        String description= stepsObject.getString("description");
                        String videoURL=stepsObject.getString("videoURL");
                        String thumbnailURL=stepsObject.getString("thumbnailURL");
                        obj[j]=new Steps(idSteps,shortDescription,description,videoURL,thumbnailURL);

                    }


                    String servings=parentObject.getString("servings");
                    String image=parentObject.getString("image");
                    foodNames[i] = new Food(id,name,ob,obj,servings,image);

                }

            }
        } catch (JSONException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

}