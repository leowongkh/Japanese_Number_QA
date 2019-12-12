package hk.edu.nihongokoza;

import hk.edu.nihongokoza.model.NumberWordModel;
import hk.edu.nihongokoza.service.NumberGenerationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberGenerationServiceTest {

    @Test
    void singleDigitTest() {
        Assertions.assertEquals(new NumberWordModel(1, "一", "いち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(1), "Number: 1 failed");
        Assertions.assertEquals(new NumberWordModel(2, "二", "に"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(2), "Number: 2 failed");
        Assertions.assertEquals(new NumberWordModel(4, "四", "よん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(4), "Number: 4 failed");
        Assertions.assertEquals(new NumberWordModel(9, "九", "きゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(9), "Number: 9 failed");
    }

    @Test
    void twoDigitTest() {
        Assertions.assertEquals(new NumberWordModel(10, "十", "じゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(10), "Number: 10 failed");
        Assertions.assertEquals(new NumberWordModel(11, "十一", "じゅういち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(11), "Number: 11 failed");
        Assertions.assertEquals(new NumberWordModel(20, "二十", "にじゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(20), "Number: 20 failed");
        Assertions.assertEquals(new NumberWordModel(33, "三十三", "さんじゅうさん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(33), "Number: 33 failed");
        Assertions.assertEquals(new NumberWordModel(59, "五十九", "ごじゅうきゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(59), "Number: 59 failed");
        Assertions.assertEquals(new NumberWordModel(97, "九十七", "きゅうじゅうなな"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(97), "Number: 97 failed");
    }

    @Test
    void threeDigitTest() {
        Assertions.assertEquals(new NumberWordModel(100, "百", "ひゃく"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(100), "Number: 100 failed");
        Assertions.assertEquals(new NumberWordModel(101, "百一", "ひゃくいち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(101), "Number: 101 failed");
        Assertions.assertEquals(new NumberWordModel(110, "百十", "ひゃくじゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(110), "Number: 110 failed");
        Assertions.assertEquals(new NumberWordModel(133, "百三十三", "ひゃくさんじゅうさん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(133), "Number: 133 failed");
        Assertions.assertEquals(new NumberWordModel(259, "二百五十九", "にひゃくごじゅうきゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(259), "Number: 259 failed");
        Assertions.assertEquals(new NumberWordModel(300, "三百", "さんびゃく"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(300), "Number: 300 failed");
        Assertions.assertEquals(new NumberWordModel(333, "三百三十三", "さんびゃくさんじゅうさん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(333), "Number: 333 failed");
        Assertions.assertEquals(new NumberWordModel(485, "四百八十五", "よんひゃくはちじゅうご"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(485), "Number: 485 failed");
        Assertions.assertEquals(new NumberWordModel(597, "五百九十七", "ごひゃくきゅうじゅうなな"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(597), "Number: 597 failed");
        Assertions.assertEquals(new NumberWordModel(600, "六百", "ろっぴゃく"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(600), "Number: 600 failed");
        Assertions.assertEquals(new NumberWordModel(619, "六百十九", "ろっぴゃくじゅうきゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(619), "Number: 619 failed");
        Assertions.assertEquals(new NumberWordModel(748, "七百四十八", "ななひゃくよんじゅうはち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(748), "Number: 748 failed");
        Assertions.assertEquals(new NumberWordModel(800, "八百", "はっぴゃく"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(800), "Number: 800 failed");
        Assertions.assertEquals(new NumberWordModel(808, "八百八", "はっぴゃくはち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(808), "Number: 808 failed");
        Assertions.assertEquals(new NumberWordModel(928, "九百二十八", "きゅうひゃくにじゅうはち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(928), "Number: 928 failed");
    }

    @Test
    void fourDigitTest() {
        Assertions.assertEquals(new NumberWordModel(1000, "千", "せん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(1000), "Number: 1,000 failed");
        Assertions.assertEquals(new NumberWordModel(1007, "千七", "せんなな"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(1007), "Number: 1,007 failed");
        Assertions.assertEquals(new NumberWordModel(1010, "千十", "せんじゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(1010), "Number: 1,010 failed");
        Assertions.assertEquals(new NumberWordModel(1300, "千三百", "せんさんびゃく"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(1300), "Number: 1,300 failed");
        Assertions.assertEquals(new NumberWordModel(2543, "二千五百四十三", "にせんごひゃくよんじゅうさん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(2543), "Number: 2,543 failed");
        Assertions.assertEquals(new NumberWordModel(3000, "三千", "さんぜん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(3000), "Number: 3,000 failed");
        Assertions.assertEquals(new NumberWordModel(3894, "三千八百九十四", "さんぜんはっぴゃくきゅうじゅうよん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(3894), "Number: 3,894 failed");
        Assertions.assertEquals(new NumberWordModel(8000, "八千", "はっせん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(8000), "Number: 8,000 failed");
        Assertions.assertEquals(new NumberWordModel(8192, "八千百九十二", "はっせんひゃくきゅうじゅうに"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(8192), "Number: 8,192 failed");
    }

    @Test
    void fiveDigitTest() {
        Assertions.assertEquals(new NumberWordModel(10000, "一万", "いちまん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(10000), "Number 10,000 failed");
        Assertions.assertEquals(new NumberWordModel(10005, "一万五", "いちまんご"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(10005), "Number 10,005 failed");
        Assertions.assertEquals(new NumberWordModel(98765, "九万八千七百六十五", "きゅうまんはっせんななひゃくろくじゅうご"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(98765), "Number: 98,765 failed");
    }

    @Test
    void sixToSevenDigitTest() {
        Assertions.assertEquals(new NumberWordModel(100000, "十万", "じゅうまん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(100000), "Number: 100,000 failed");
        Assertions.assertEquals(new NumberWordModel(345678, "三十四万五千六百七十八", "さんじゅうよんまんごせんろっぴゃくななじゅうはち"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(345678), "Number: 345,678 failed");
        Assertions.assertEquals(new NumberWordModel(1000000, "百万", "ひゃくまん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(1000000), "Number: 1,000,000 failed");
        Assertions.assertEquals(new NumberWordModel(3456789, "三百四十五万六千七百八十九", "さんびゃくよんじゅうごまんろくせんななひゃくはちじゅうきゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(3456789), "Number: 3,456,789 failed");
    }

    @Test
    void eightDigitTest() {
        Assertions.assertEquals(new NumberWordModel(10000000, "一千万", "いっせんまん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(10000000), "Number: 10,000,000 failed");
        Assertions.assertEquals(new NumberWordModel(10200000, "一千二十万", "いっせんにじゅうまん"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(10200000), "Number: 10,200,000 failed");
        Assertions.assertEquals(new NumberWordModel(30040230, "三千四万二百三十", "さんぜんよんまんにひゃくさんじゅう"), NumberGenerationService.getInstance().convertGeneratedNumberToModel(30040230), "Number: 30,040,230 failed");
    }
}
