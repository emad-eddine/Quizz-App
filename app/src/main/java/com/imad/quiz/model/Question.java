package com.imad.quiz.model;

public class Question {

    private String question;
    private Boolean isAnswerTrue;

    public Question()
    {

    }

    public Question(String question, Boolean isAnswerTrue) {
        this.question = question;
        this.isAnswerTrue = isAnswerTrue;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAnswerTrue() {
        return isAnswerTrue;
    }

    public void setAnswerTrue(Boolean answerTrue) {
        isAnswerTrue = answerTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", isAnswerTrue=" + isAnswerTrue +
                '}';
    }
}
