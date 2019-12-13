package hk.edu.nihongokoza.service;

import java.io.IOException;

public class GetJSONTextService {
    private static GetJSONTextService instance;

    private JSONService jsonService = JSONService.getInstance();

    private GetJSONTextService() {
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static GetJSONTextService getInstance() {
        if (instance == null) {
            instance = new GetJSONTextService();
        }
        return instance;
    }

    /**
     * @param sceneName name of the root scene for json
     * @param jsonKeys  <i>Required</i> key values for JSON to get the value, in path order.
     * @return The value obtained if the order key values can lead to a text JSON Node.
     * @throws NullPointerException if the value cannot be obtained
     */
    public String getJSONTextValue(final String sceneName, final String... jsonKeys) {
        try {
            var jsonNode = jsonService.getJSONNode(sceneName);
            for (var jsonKey : jsonKeys) {
                if (jsonNode.isTextual()) {
                    return jsonNode.textValue();
                }
                jsonNode = jsonNode.findValue(jsonKey);
                if (jsonNode.isTextual()) {
                    return jsonNode.textValue();
                }
            }
            var errorMessageBuilder = new StringBuilder("Value not found for key: ")
                    .append(sceneName);
            for (var jsonKey: jsonKeys) {
                errorMessageBuilder.append(".").append(jsonKey);
            }
            throw new NullPointerException(errorMessageBuilder.toString());
        } catch (IOException|NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
