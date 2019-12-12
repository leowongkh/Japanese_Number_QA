package hk.edu.nihongokoza.viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class NumberWordViewModel {
    private SimpleObjectProperty<BigDecimal> number;
    private SimpleStringProperty kanji;
    private SimpleStringProperty hiragana;

    public NumberWordViewModel() {
        number = new SimpleObjectProperty<>();
        kanji = new SimpleStringProperty();
        hiragana = new SimpleStringProperty();
    }

    public BigDecimal getNumber() {
        return number.get();
    }

    public SimpleObjectProperty<BigDecimal> numberProperty() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number.set(number);
    }

    public String getKanji() {
        return kanji.get();
    }

    public void setKanji(String kanji) {
        this.kanji.set(kanji);
    }

    public SimpleStringProperty kanjiProperty() {
        return kanji;
    }

    public String getHiragana() {
        return hiragana.get();
    }

    public void setHiragana(String hiragana) {
        this.hiragana.set(hiragana);
    }

    public SimpleStringProperty hiraganaProperty() {
        return hiragana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWordViewModel that = (NumberWordViewModel) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(kanji, that.kanji) &&
                Objects.equals(hiragana, that.hiragana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, kanji, hiragana);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NumberWordViewModel{");
        sb.append("number=").append(number);
        sb.append(", kanji=").append(kanji);
        sb.append(", hiragana=").append(hiragana);
        sb.append('}');
        return sb.toString();
    }
}
