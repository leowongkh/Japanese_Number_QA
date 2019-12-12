package hk.edu.nihongokoza.service;

import java.util.Locale;

public class LocaleService {
    private static LocaleService instance;

    private Locale currentLocale;

    private LocaleService() {
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static LocaleService getInstance() {
        if (instance == null) {
            instance = new LocaleService();
        }
        return instance;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }


}
