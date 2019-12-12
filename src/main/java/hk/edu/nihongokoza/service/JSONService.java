package hk.edu.nihongokoza.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import hk.edu.nihongokoza.App;

import java.io.IOException;
import java.io.InputStream;

public class JSONService {
    private static JSONService instance;

    private LocaleService localeService = LocaleService.getInstance();

    private JSONService() {
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static JSONService getInstance() {
        if (instance == null) {
            instance = new JSONService();
        }
        return instance;
    }

    public JsonNode getJSONNode(String sceneName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = App.class.getResourceAsStream("display.json");
        var root = objectMapper.readTree(inputStream);
        JsonNode langRoot;
        if (root.isArray()) {
            var rootArray = ((ArrayNode) root);
            langRoot = rootArray.findValue(localeService.getCurrentLocale().getLanguage());
        } else {
            langRoot = root.findValue(localeService.getCurrentLocale().getLanguage());
        }

        return langRoot.get(sceneName);
    }

}
