package com.imad.quiz.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.imad.quiz.controller.AppController;
import com.imad.quiz.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    private ArrayList<Question> questionArrayList = new ArrayList<>();

    public List<Question> getQuestion(final AnswerlistAsyncResponse callBack)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray questionFromArray ;
                for(int i = 0 ; i<response.length();i++)
                {
                    try
                    {
                        Question question = new Question();
                        question.setQuestion(response.getJSONArray(i).getString(0));
                        question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));
                        //Log.d("question", "onResponse: " + response.getJSONArray(i).getString(0));
                        questionArrayList.add(question);



                    }

                     catch (JSONException e)
                     {
                        e.printStackTrace();
                     }
                }
                if(null != callBack)
                {
                    callBack.processFinish(questionArrayList);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return  questionArrayList;
    }


}
