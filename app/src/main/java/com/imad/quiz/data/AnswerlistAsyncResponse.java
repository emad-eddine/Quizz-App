package com.imad.quiz.data;

import com.imad.quiz.model.Question;

import java.util.ArrayList;

public interface AnswerlistAsyncResponse {

    void processFinish(ArrayList<Question> questionArrayList);
}
