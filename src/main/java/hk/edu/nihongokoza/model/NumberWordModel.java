package hk.edu.nihongokoza.model;

import java.util.Objects;

public class NumberWordModel {
    private int number;
    private String kanji;
    private String hiragana;

    public NumberWordModel() {
    }

    public NumberWordModel(int number, String kanji, String hiragana) {
        this.number = number;
        this.kanji = kanji;
        this.hiragana = hiragana;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWordModel that = (NumberWordModel) o;
        return number == that.number &&
                Objects.equals(kanji, that.kanji) &&
                Objects.equals(hiragana, that.hiragana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, kanji, hiragana);
    }

    @Override
    public String toString() {
        return String.format("NumberWordModel{number=%d, kanji='%s', hiragana='%s'}", number, kanji, hiragana);
    }
}
