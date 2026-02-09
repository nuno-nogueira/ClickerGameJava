package com.clickergame.persistence;
import java.io.*;

public class SaveManager {

    private static final String SAVE_PATH = "savegame.dat";

    public void save(SaveData data) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SaveData load() {
        File file = new File(SAVE_PATH);
        if (!file.exists()) {
            SaveData defaultSave = new SaveData();
            save(defaultSave);
            return defaultSave;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            return (SaveData) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}

