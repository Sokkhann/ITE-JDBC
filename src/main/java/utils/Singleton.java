package utils;

import service.IPersonService;
import service.PersonServiceImp;
import view.PersonView;

public class Singleton {
    private static IPersonService iPersonService;
    private static PersonView personView;

    public static IPersonService getPersonServiceImp() {
        if (iPersonService == null) {
            iPersonService = new PersonServiceImp();
        }
        return iPersonService;
    }

    public static PersonView getPersonView() {
        if (personView == null) {
            personView = new PersonView();
        }
        return personView;
    }
}
