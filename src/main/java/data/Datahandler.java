package data;

import java.io.File;

public class Datahandler {

    private static Datahandler instance;

    private Datahandler(){}

    public static Datahandler getInstance() {
        if(Datahandler.instance == null){
            Datahandler.instance = new Datahandler();
        }
        return instance;
    }


    public void ReadFile(File file){

    }
}
