package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectNameSingleton
{
    // static variable single_instance of type Singleton
    private static ProjectNameSingleton single_instance = null;

    // variable of type String
    public String s = null;

    // static method to create instance of Singleton class
    public static ProjectNameSingleton getInstance()
    {
        if (single_instance == null){
            single_instance = new ProjectNameSingleton();
        }
        return single_instance;
    }

    public String getS() {
        Main.primaryStage.setTitle("Sticky notes - " + s);
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}