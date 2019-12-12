package hk.edu.nihongokoza.service;

import hk.edu.nihongokoza.constant.NumberMappingConstant;
import hk.edu.nihongokoza.model.NumberWordModel;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Random;

public class NumberGenerationService {
    private static NumberGenerationService instance;

    private NumberGenerationService() {
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static NumberGenerationService getInstance() {
        if (instance == null) {
            instance = new NumberGenerationService();
        }
        return instance;
    }

    private int generateNumber(int bound) {
        var random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return random.nextInt(bound) + 1;
    }

    /**
     * Creates a {@link NumberWordModel} with the number, and put its corresponding Kanji and Hiragana.
     *
     * @param number the number that is to be converted to Japanese, must be &le; <code>100,000,000</code>
     * @return the model created with corresponding Kanji and Hiragana
     */
    @NotNull
    public NumberWordModel convertGeneratedNumberToModel(int number) {
        var numberWordModel = new NumberWordModel();

        numberWordModel.setNumber(number);

        var currentNumberString = Integer.toString(number).split("");
        String[] kanjiCorrespondent = new String[currentNumberString.length];
        String[] hiraganaCorrespondent = new String[currentNumberString.length];

        for (int i = currentNumberString.length - 1; i >= 0; i--) {
            int power = Math.abs(i - currentNumberString.length + 1);

            // current digit == 0 or blank
            if (currentNumberString[i].isBlank() || (currentNumberString[i].equals("0") && power != 4)) {
                continue;
            }

            int currentNumber = Integer.parseInt(currentNumberString[i]);
            String currentKanji = "", currentHiragana = "";
            var product = BigDecimal.TEN.pow(power).intValue() * currentNumber;
            switch (power) {
                case 0: {
                    currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getKanji();
                    currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getHiragana();
                }
                break;
                case 1:
                case 2:
                case 3: {
                    if (NumberMappingConstant.NUMBER_WORD_MODEL_MAP.containsKey(product)) {
                        currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(product).getKanji();
                        currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(product).getHiragana();
                    } else {
                        currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getKanji()
                                + NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power).intValue()).getKanji();
                        currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getHiragana()
                                + NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power).intValue()).getHiragana();
                    }
                }
                break;
                case 4:
                case 8: {
                    if (currentNumber == 0) {
                        currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power).intValue()).getKanji();
                        currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power).intValue()).getHiragana();
                    } else {
                        currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getKanji()
                                + NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power).intValue()).getKanji();
                        currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getHiragana()
                                + NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power).intValue()).getHiragana();
                    }
                }
                break;
                case 5:
                case 6:
                case 7: {
                    if (NumberMappingConstant.NUMBER_WORD_MODEL_MAP.containsKey(product)) {
                        currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(product).getKanji();
                        currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(product).getHiragana();
                    } else {
                        product = BigDecimal.TEN.pow(power % 4).intValue() * currentNumber;
                        if (NumberMappingConstant.NUMBER_WORD_MODEL_MAP.containsKey(product)) {
                            currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(product).getKanji();
                            currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(product).getHiragana();
                        } else {
                            currentKanji = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getKanji()
                                    + NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power % 4).intValue()).getKanji();
                            currentHiragana = NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(currentNumber).getHiragana()
                                    + NumberMappingConstant.NUMBER_WORD_MODEL_MAP.get(BigDecimal.TEN.pow(power % 4).intValue()).getHiragana();
                        }
                    }
                }
            }
            kanjiCorrespondent[i] = currentKanji;
            hiraganaCorrespondent[i] = currentHiragana;
        }
        String kanji = Arrays.stream(kanjiCorrespondent).reduce("", (s, s2) -> s2 != null ? s.concat(s2) : s);
        kanji = kanji.replace("億万", "億");
        String hiragana = Arrays.stream(hiraganaCorrespondent).reduce("", (s, s2) -> s2 != null ? s.concat(s2) : s);
        hiragana = hiragana.replace("おくまん", "おく");
        numberWordModel.setKanji(kanji);
        numberWordModel.setHiragana(hiragana);
        return numberWordModel;
    }


    /**
     * generates a question in game, which the maximum number is specified in the bound
     *
     * @param bound maximum number to generate, which will resulted in <math>[1,bound]</math>
     * @return generated question
     */
    public NumberWordModel generateQuestion(int bound) {
        int generatedNumber = generateNumber(bound);
        return convertGeneratedNumberToModel(generatedNumber);
    }


}
