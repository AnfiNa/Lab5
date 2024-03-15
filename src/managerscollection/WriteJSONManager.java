package managerscollection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import objects.StudyGroup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collection;

public class WriteJSONManager {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(ZonedDateTime.class, new DateAdapter())
            .create();

    public static void writeCollection(String fileName, Collection<StudyGroup> collection) {
        String text = gson.toJson(collection); // строка для записи
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
