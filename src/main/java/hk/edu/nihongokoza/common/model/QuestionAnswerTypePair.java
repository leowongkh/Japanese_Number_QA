package hk.edu.nihongokoza.common.model;

import hk.edu.nihongokoza.constant.QuestionType;

import java.util.Objects;

public final class QuestionAnswerTypePair {
    private QuestionType questionType;
    private QuestionType answerType;

    public QuestionAnswerTypePair(QuestionType questionType, QuestionType answerType) {
        this.questionType = questionType;
        this.answerType = answerType;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(QuestionType answerType) {
        this.answerType = answerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAnswerTypePair questionAnswerTypePair = (QuestionAnswerTypePair) o;
        return questionType == questionAnswerTypePair.questionType &&
                answerType == questionAnswerTypePair.answerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionType, answerType);
    }
}
