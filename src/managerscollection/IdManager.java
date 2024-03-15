package managerscollection;


import objects.StudyGroup;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class IdManager {
    private static ArrayList<Integer> array;

    static {
        array = new ArrayList<>();
    }

    public static StudyGroup checkGroupById(Integer id) {
        CollectionManager<TreeSet<StudyGroup>, StudyGroup> collectionHandler = StudyGroupManager.getStudyGroupManager();
        for (StudyGroup studyGroup : collectionHandler.getCollection()) {
            if (Objects.equals(studyGroup.getId(), id)) {
                return studyGroup;
            }
        }
        return null;
    }

    public static Integer generateId() {
        Integer id = 1;
        while (array.contains(id)) {
            id++;
        }
        array.add(id);
        return id;
    }
    public static void clearId(){
        array = new ArrayList<>();
    }

    public static void addNewId(int c) {
        array.add(c);
    }

    public static ArrayList<Integer> getArray() {
        return array;
    }
}
