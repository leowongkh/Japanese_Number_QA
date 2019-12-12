package hk.edu.nihongokoza.constant;

import hk.edu.nihongokoza.model.NumberWordModel;

import java.util.Map;

public class NumberMappingConstant {
    public static final Map<Integer, NumberWordModel> NUMBER_WORD_MODEL_MAP = Map.ofEntries(
            Map.entry(1, new NumberWordModel(1, "一", "いち")),
            Map.entry(2, new NumberWordModel(2, "二", "に")),
            Map.entry( 3, new NumberWordModel(3, "三", "さん")),
            Map.entry( 4, new NumberWordModel(4, "四", "よん")),
            Map.entry( 5, new NumberWordModel(5, "五", "ご")),
            Map.entry( 6, new NumberWordModel(6, "六", "ろく")),
            Map.entry( 7, new NumberWordModel(7, "七", "なな")),
            Map.entry( 8, new NumberWordModel(8, "八", "はち")),
            Map.entry( 9, new NumberWordModel(9, "九", "きゅう")),
            Map.entry( 10, new NumberWordModel(10, "十", "じゅう")),
            Map.entry(100, new NumberWordModel(100, "百", "ひゃく")),
            Map.entry(300, new NumberWordModel(300, "三百", "さんびゃく")),
            Map.entry( 600, new NumberWordModel(600, "六百", "ろっぴゃく")),
            Map.entry( 800, new NumberWordModel(800, "八百", "はっぴゃく")),
            Map.entry( 1000, new NumberWordModel(1000, "千", "せん")),
            Map.entry( 3000, new NumberWordModel(3000, "三千", "さんぜん")),
            Map.entry( 8000, new NumberWordModel(8000, "八千", "はっせん")),
            Map.entry( 10000, new NumberWordModel(10000, "万", "まん")),
            Map.entry(10000000, new NumberWordModel(10000000, "一千", "いっせん")),
            Map.entry( 100000000, new NumberWordModel(100000000, "億", "おく"))
    );
}
