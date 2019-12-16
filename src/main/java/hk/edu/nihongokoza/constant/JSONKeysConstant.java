package hk.edu.nihongokoza.constant;

public class JSONKeysConstant {
    public static final String CORRECT_KEY = "correct";
    public static final String INCORRECT_KEY = "incorrect";
    public static final String CSV_SYMBOL_KEY = "csv";
    public static final String FILE_SAVE_TITLE_KEY = "file_save_title";

    public static final String SYMBOL_KEY = "symbol";
    public static final String POP_UP_KEY = "pop_up";
    public static final String TITLE_KEY = "title";
    public static final String CONTENT_TEXT_KEY = "content_text";
    public static final String CONTENT_AFTER_TEXT_KEY = "content_after_text";

    public static final String TYPES_KEY = "types";
    public static final String KANJI_KEY = "kanji";
    public static final String HIRAGANA_KEY = "hiragana";
    public static final String NUMBER_KEY = "number";

    public static final String PUNCTUATION_KEY = "punctuation";
    public static final String QUOTATION_KEY = "quotation";
    public static final String OPEN_KEY = "open";
    public static final String CLOSE_KEY = "close";

    public static class SceneName {
        public static final String ANSWER_HISTORY_SCENE_NAME = "answer_history";
        public static final String MAIN_MENU_SCENE_NAME = "main_menu";
        public static final String IN_GAME_SCENE_NAME = "in_game";
        public static final String DIFFICULTY_SELECT_SCENE_NAME = "difficulty_select";
        public static final String COMMON_SCENE_NAME = "common";
    }

    public static final class MainMenuKeys{
        public static final String TITLE_KEY = "title";
        public static final String START_GAME_KEY = "startGame";
        public static final String ANSWER_HISTORY_KEY =  "answerHistory";
        public static final String EXIT_KEY = "exit";
    }

    public static class InGameKeys{
        public static final String INPUT_FIELD_PROMPT_TEXT_KEY = "input_text_field_prompt_text";
        public static final String PRE_QUESTION_TYPE_DISPLAY_KEY = "pre_question_type_display";
        public static final String PRE_ANSWER_TYPE_DISPLAY_KEY = "pre_answer_type_display";
        public static final String SUBMIT_KEY = "submit";
        public static final String NEXT_QUESTION_KEY = "next_question";
        public static final String RETURN_KEY = "return";

    }

    public static class DifficultySelectKeys{
        public static final String DIFFICULTY_PROMPT_TEXT_KEY = "difficulty_prompt";
        public static final String TITLE_KEY = "title";
        public static final String NUMBER_RANGE_KEY = "number_range";
        public static final String NUMBER_RANGE_TO_KEY = "number_range_to";
        public static final String UNKNOWN_NUMBER_KEY = "unknown_number";
        public static final String START_KEY = "start";
        public static final String RETURN_KEY = "return";
    }

    public static class AnswerHistoryKeys{
        public static final String DIFFICULTY_COLUMN_KEY = "difficulty";
        public static final String QUESTION_COLUMN_KEY = "question";
        public static final String QUESTION_TYPE_COLUMN_KEY = "question_type";
        public static final String CORRECT_ANSWER_COLUMN_KEY = "correct_answer";
        public static final String YOUR_ANSWER_COLUMN_KEY = "your_answer";
        public static final String CORRECT_RATE_KEY = "correctRate";
        public static final String CORRECT_HINT_KEY = "correctHint";
        public static final String EXPORT_TO_CSV_KEY = "export_to_csv";
        public static final String RETURN_KEY = "return";
    }
}
