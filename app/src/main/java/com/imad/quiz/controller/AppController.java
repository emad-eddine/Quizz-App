package com.imad.quiz.controller;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    private static AppController instance;
    private RequestQueue requestQueue;



    public static synchronized AppController getInstance()
    {
        //if(instance == null)
       // {
         //   instance = new Singlton(context);
        //}
        return instance;
    }

    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue =  requestQueue =  Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }



}
