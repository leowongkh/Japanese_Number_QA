package hk.edu.nihongokoza.viewmodel;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AnswerHistoryViewModel {
    private SimpleStringProperty difficulty;
    private SimpleStringProperty question;
    private SimpleStringProperty questionType;
    private SimpleStringProperty correctAnswer;
    private SimpleStringProperty yourAnswer;

    public AnswerHistoryViewModel() {
        difficulty = new SimpleStringProperty();
        question = new SimpleStringProperty();
        questionType = new SimpleStringProperty();
        correctAnswer = new SimpleStringProperty();
        yourAnswer = new SimpleStringProperty();
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public SimpleStringProperty difficultyProperty() {
        return difficulty;
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public String getQuestionType() {
        return questionType.get();
    }

    public void setQuestionType(String questionType) {
        this.questionType.set(questionType);
    }

    public SimpleStringProperty questionTypeProperty() {
        return questionType;
    }

    public String getCorrectAnswer() {
        return correctAnswer.get();
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer.set(correctAnswer);
    }

    public SimpleStringProperty correctAnswerProperty() {
        return correctAnswer;
    }

    public String getYourAnswer() {
        return yourAnswer.get();
    }

    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer.set(yourAnswer);
    }

    public SimpleStringProperty yourAnswerProperty() {
        return yourAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerHistoryViewModel that = (AnswerHistoryViewModel) o;
        return Objects.equals(difficulty, that.difficulty) &&
                Objects.equals(question, that.question) &&
                Objects.equals(questionType, that.questionType) &&
                Objects.equals(correctAnswer, that.correctAnswer) &&
                Objects.equals(yourAnswer, that.yourAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(difficulty, question, questionType, correctAnswer, yourAnswer);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AnswerHistoryViewModel{");
        sb.append("difficulty=").append(difficulty);
        sb.append(", question=").append(question);
        sb.append(", questionType=").append(questionType);
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", yourAnswer=").append(yourAnswer);
        sb.append('}');
        return sb.toString();
    }
}
