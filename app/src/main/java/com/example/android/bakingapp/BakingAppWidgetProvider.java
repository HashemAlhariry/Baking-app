package com.example.android.bakingapp;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.bakingapp.Network.NetworkUtilites;
import com.example.android.bakingapp.RecipeIngredients.RecipeIngredients;
import com.example.android.bakingapp.food.Food;
import com.example.android.bakingapp.food.Ingredients;
import com.example.android.bakingapp.food.Steps;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;


public class BakingAppWidgetProvider extends AppWidgetProvider
{
    private static String dataRetrieved;
    private static Food[] foodNames;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        networkUtilites();


        Intent intentB = new Intent(context, RecipeIngredients.class);
        intentB.putExtra("ingredients",foodNames[1].getIngredients());
        PendingIntent pendingIntentB = PendingIntent.getActivity(context, 0, intentB, 0);

        Intent intentN = new Intent(context, RecipeIngredients.class);
        intentN.putExtra("ingredients",foodNames[0].getIngredients());
        PendingIntent pendingIntentN = PendingIntent.getActivity(context, 0, intentN, 0);




        Intent intentY = new Intent(context, RecipeIngredients.class);
        intentY.putExtra("ingredients",foodNames[2].getIngredients());
        PendingIntent pendingIntentY = PendingIntent.getActivity(context, 0, intentY, 0);



        Intent intentC = new Intent(context, RecipeIngredients.class);
        intentC.putExtra("ingredients",foodNames[3].getIngredients());
        PendingIntent pendingIntentC = PendingIntent.getActivity(context, 0, intentC, 0);




        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
        views.setOnClickPendingIntent(R.id.brownies, pendingIntentB);
        views.setOnClickPendingIntent(R.id.nutella, pendingIntentN);

        views.setOnClickPendingIntent(R.id.yellowcake, pendingIntentY);
        views.setOnClickPendingIntent(R.id.cheesecake, pendingIntentC);

        appWidgetManager.updateAppWidget(appWidgetId, views);



    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            networkUtilites();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        networkUtilites();

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public static void networkUtilites()
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

    public static void getFoodArray()
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
                    Ingredients[]ob= new Ingredients[ingredients.length()];
                    for(int j=0;j<ingredients.length();j++)
                    {
                        JSONObject ingredientsObject = ingredients.getJSONObject(j);
                        String quantity =ingredientsObject.getString("quantity");
                        String measure= ingredientsObject.getString("measure");
                        String ingredient= ingredientsObject.getString("ingredient");
                        ob[j]=new Ingredients(quantity,measure,ingredient);
                    }


                    JSONArray steps=parentObject.getJSONArray("steps");
                    Steps[] obj = new Steps[steps.length()];
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

