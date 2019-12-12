package hk.edu.nihongokoza;

import hk.edu.nihongokoza.service.JSONService;
import hk.edu.nihongokoza.service.LocaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Locale;

import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.MAIN_MENU_SCENE_NAME;

public class JSONServiceTest {
    @BeforeAll
    static void beforeAll(){
        LocaleService.getInstance().setCurrentLocale(Locale.CHINESE);
    }

    @Test
    public void JSONTest() throws IOException {
        Assertions.assertEquals(4, JSONService
                .getInstance()
                .getJSONNode(MAIN_MENU_SCENE_NAME).size());
    }
}
