package hk.edu.nihongokoza.service;

import javafx.scene.control.*;

public class DisplayTextService {
    private static DisplayTextService instance;

    private GetJSONTextService getJSONTextService = GetJSONTextService.getInstance();

    private DisplayTextService(){
    }

    /**
     * Sets the text of the {@link Labeled} to the specified JSON value, which is supplied by the root node with the JSON key.
     * <p>
     *     Common controls that are applicable:
     *     <ol>
     *         <li>{@link Label}</li>
     *         <li>{@link Button}</li>
     *         <li>{@link Cell}</li>
     *     </ol>
     * </p>
     *
     * @param sceneName the root key for finding the text value
     * @param labeled the labeled whose text is to be set
     * @param values the order JSON key that leads to the text value
     */
    public void initializeSingleLabeledDisplayText(String sceneName, Labeled labeled, String... values) {
        var displayText = getJSONTextService.getJSONTextValue(sceneName, values);
        if (displayText == null) {
            throw new NullPointerException();
        }
        labeled.setText(displayText);
    }

    /**
     * Sets the text of the {@link TableColumnBase} to the specified JSON value, which is supplied by the root node with the JSON key.
     * <p>
     *     Common controls that are applicable:
     *     <ol>
     *         <li>{@link TableColumn}</li>
     *         <li>{@link TreeTableColumn}</li>
     *     </ol>
     * </p>
     *
     * @param sceneName the root key for finding the text value
     * @param tableColumn the column whose text is to be set
     * @param values the order JSON key that leads to the text value
     */
    public void initializeSingleTableColumnDisplay(String sceneName, TableColumnBase<?, ?> tableColumn, String... values) {
        var displayText = getJSONTextService.getJSONTextValue(sceneName,values);
        if (displayText == null) {
            throw new NullPointerException();
        }
        tableColumn.setText(displayText);
    }

    /**
     * Sets the text of the {@link ComboBoxBase} to the specified JSON value, which is supplied by the root node with the JSON key.
     * <p>
     *     Common controls that are applicable:
     *     <ol>
     *         <li>{@link ColorPicker}</li>
     *         <li>{@link ComboBox}</li>
     *         <li>{@link DatePicker}</li>
     *     </ol>
     * </p>
     *
     * @param sceneName the root key for finding the text value
     * @param comboBox the combo box / picker whose text is to be set
     * @param values the order JSON key that leads to the text value
     */
    public void initializeSingleComboBoxPromptText(String sceneName, ComboBoxBase<?> comboBox, String... values){
        var displayText = getJSONTextService.getJSONTextValue(sceneName,values);
        if (displayText == null) {
            throw new NullPointerException();
        }
        comboBox.setPromptText(displayText);
    }

    /**
     * Sets the text of the {@link TextInputControl} to the specified JSON value, which is supplied by the root node with the JSON key.
     * <p>
     *     Common controls that are applicable:
     *     <ol>
     *         <li>{@link TextField}</li>
     *         <li>{@link TextArea}</li>
     *         <li>{@link PasswordField}</li>
     *     </ol>
     * </p>
     *
     * @param sceneName the root key for finding the text value
     * @param textInputControl the text input control whose text is to be set
     * @param values the order JSON key that leads to the text value
     */
    public void initializeSingleTextInputControlPromptText(String sceneName, TextInputControl textInputControl, String... values){
        var displayText = getJSONTextService.getJSONTextValue(sceneName, values);
        if (displayText == null){
            throw new NullPointerException();
        }
        textInputControl.setPromptText(displayText);
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static DisplayTextService getInstance() {
        if (instance == null){
            instance = new DisplayTextService();
        }
        return instance;
    }
}
