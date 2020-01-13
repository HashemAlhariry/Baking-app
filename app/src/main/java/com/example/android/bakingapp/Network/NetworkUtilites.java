package com.example.android.bakingapp.Network;


import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class NetworkUtilites extends AsyncTask<Void,Void,String>
{


    private String foodURL="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private String sent;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(Void... strings)
    {

        try
        {
            URL url = new URL(foodURL);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            sent=streamtoString(in);
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return sent;
    }

    @Override
    protected void onPostExecute(String  s)
    {
        super.onPostExecute(String.valueOf(s));
    }

    public String streamtoString(InputStream inputStream)
    {
        BufferedReader bureader= new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String Text = "";
        try{
            while((line=bureader.readLine())!=null)
            {
                Text+=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Text;
    }




}
