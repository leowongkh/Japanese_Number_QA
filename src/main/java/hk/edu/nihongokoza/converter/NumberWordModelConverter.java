package hk.edu.nihongokoza.converter;

import hk.edu.nihongokoza.model.NumberWordModel;
import hk.edu.nihongokoza.viewmodel.NumberWordViewModel;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Converter class that converts between {@link NumberWordModel} and {@link NumberWordViewModel}.
 */
public class NumberWordModelConverter {
    private static NumberWordModelConverter instance;

    /**
     * Converts from a {@link NumberWordModel} source to a {@link NumberWordViewModel} target
     *
     * @param numberWordModel the source
     * @param numberWordViewModel the target
     */
    public void convertFromModelToViewModel(@NotNull NumberWordModel numberWordModel, @NotNull NumberWordViewModel numberWordViewModel){
        numberWordViewModel.setNumber(new BigDecimal(numberWordModel.getNumber()));
        numberWordViewModel.setHiragana(numberWordModel.getHiragana());
        numberWordViewModel.setKanji(numberWordModel.getKanji());
    }

    /**
     * Converts from a {@link NumberWordViewModel} source to a {@link NumberWordModel} target.
     *
     * @param numberWordViewModel the source
     * @param numberWordModel the target
     */
    public void convertFromViewModelToModel(@NotNull NumberWordViewModel numberWordViewModel, @NotNull NumberWordModel numberWordModel){
        numberWordModel.setNumber(numberWordViewModel.getNumber().intValue());
        numberWordModel.setHiragana(numberWordViewModel.getHiragana());
        numberWordModel.setKanji(numberWordViewModel.getKanji());
    }

    private NumberWordModelConverter() {
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static NumberWordModelConverter getInstance() {
        if (instance == null){
            instance = new NumberWordModelConverter();
        }
        return instance;
    }
}
