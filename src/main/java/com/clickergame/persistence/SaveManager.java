package com.clickergame.persistence;
import java.io.*;

public class SaveManager {

    private static final String SAVE_PATH = "savegame.dat";

    public void save(Object data) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            out.writeObject("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            Object obj = in.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object();
        }
    }
}

