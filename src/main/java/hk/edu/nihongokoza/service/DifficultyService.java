package hk.edu.nihongokoza.service;

import hk.edu.nihongokoza.constant.Difficulties;

public class DifficultyService {
    private Difficulties difficulty;
    private static DifficultyService instance;

    private DifficultyService() {
    }

    public Difficulties getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulties difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static DifficultyService getInstance() {
        if (instance == null){
            instance = new DifficultyService();
        }
        return instance;
    }
}
