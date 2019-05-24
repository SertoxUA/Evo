package ua.sertox.evo.objects;

import android.app.Application;

import java.util.ArrayList;

public class AppContext extends Application {

    public static final String ACTION_TYPE = "ru.sertox.evo.AppContext.ActionType";
    public static final String DOC_INDEX = "ru.sertox.evo.AppContext.ActionIndex";

    public static final int ACTION_NEW_TASK = 0;
    public static final int ACTION_UPDATE = 1;

    private ArrayList<EvoDocument> listDocuments = new ArrayList<EvoDocument>();

    public ArrayList<EvoDocument> getListDocuments() {
        return listDocuments;
    }

    public void setListDocuments(ArrayList<EvoDocument> listDocuments) {
        this.listDocuments = listDocuments;
    }

}
