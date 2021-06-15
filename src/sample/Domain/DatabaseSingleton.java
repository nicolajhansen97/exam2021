package sample.Domain;

import sample.Database.Database;
import sample.Main;

public class DatabaseSingleton {

        // static variable single_instance of type Singleton
        private static DatabaseIF single_instance;


        /**
         * static method to create instance of Singleton class
         */
        public static DatabaseIF getInstance()
        {
            if (single_instance == null){
                single_instance = new Database();
            }
            return single_instance;
        }

        /**
         * Getter of the project name single instance
         * @return returns the name of project
         */

    }


