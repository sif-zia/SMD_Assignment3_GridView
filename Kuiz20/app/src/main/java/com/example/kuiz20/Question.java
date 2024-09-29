package com.example.kuiz20;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    private final String statement;
    private final String[] options;
    private final String answer;

    public Question(String statement, String[] options, String answer) {
        this.statement = statement;
        this.options = options;
        this.answer = answer;
    }

    boolean checkAnswer(String givenAnswer) {
        return Objects.equals(givenAnswer, answer);
    }

    public String getStatement() {
        return statement;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }
}
