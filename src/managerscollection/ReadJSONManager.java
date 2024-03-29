package managerscollection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import objects.StudyGroup;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.TreeSet;

public class ReadJSONManager {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(ZonedDateTime.class, new DateAdapter())
            .create();

    public static StringBuilder readTextFromFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try (FileReader fr = new FileReader(fileName)) {
            int i = fr.read();
            while (i != -1) {
                result.append((char) i);
                i = fr.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Try again.");
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void readFromFile(String fileName){
        StringBuilder result = readTextFromFile(fileName);
        var collectionType = new TypeToken<TreeSet<StudyGroup>>() {
        }.getType();
        TreeSet<StudyGroup> collection = gson.fromJson(result.toString(), collectionType);
        IdManager.clearId();
        CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
        manager.setCollection(collection);
        for (StudyGroup element : collection) {
            IdManager.addNewId(element.getId());
        }
    }
}