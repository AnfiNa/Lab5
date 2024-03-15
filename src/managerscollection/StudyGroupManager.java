package managerscollection;

import objects.GroupComparator;
import objects.StudyGroup;
import java.util.TreeSet;

public class StudyGroupManager implements CollectionManager<TreeSet<StudyGroup>, StudyGroup>{
    private static StudyGroupManager studyGroupManager;
    private TreeSet<StudyGroup> studyGroupTreeSet;
    private StudyGroupManager(){};
    public static StudyGroupManager getStudyGroupManager(){
        if (studyGroupManager == null){
            studyGroupManager = new StudyGroupManager();
        }
        return studyGroupManager;

    }

    @Override
    public TreeSet<StudyGroup> getCollection() {
        return this.studyGroupTreeSet;
    }

    @Override
    public void addElementToCollection(StudyGroup value) {
        if (studyGroupTreeSet != null){
            this.studyGroupTreeSet.add(value);
        } else {
            TreeSet<StudyGroup> studyGroups = new TreeSet<>(new GroupComparator());
            studyGroups.add(value);
            setCollection(studyGroups);
        }
    }

    @Override
    public void clearCollection() {
        this.studyGroupTreeSet.clear();
    }

    @Override
    public void setCollection(TreeSet<StudyGroup> studyGroupTreeSet) {
        this.studyGroupTreeSet = studyGroupTreeSet;
    }




}