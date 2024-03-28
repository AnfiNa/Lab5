package managers.commands;

import managers.Command;
import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.*;

/**
 * Class PrintFieldDescendingStudentsCount provides output of the students count in descending order.
 * Extends the Command class.
 */
public class PrintFieldDescendingStudentsCount extends Command {

    public PrintFieldDescendingStudentsCount() {
        super(false);
    }

    @Override
    public String getName() {
        return "print_field_descending_students_count";
    }

    @Override
    public String getDescription() {
        return "Prints the field values student's count of all elements in descending order.";
    }

    /**
     * Overrides the method execute() in the Command class.
     * Prints the student count in descending order.
     * Checks the argument before execution.
     */
    @Override
    public void execute(Receiver receiver) {
        if (checkArgument(receiver.getArg())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }
            ArrayList<Integer> groups= new ArrayList<>();
            for (StudyGroup studyGroup: manager.getCollection().descendingSet()){
                groups.add(- studyGroup.getStudentsCount());
            }
            Collections.sort(groups);
            for (Integer group: groups){
                System.out.println(-group);
            }

        }
    }


    /**
     * Overrides the method checkArgument(Object inputArgument) in the Command class.
     * Checks if the argument is null.
     * @param inputArgument the argument to be checked.
     * @return true if the argument is null, false if not.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Print_field_descending_student_count has no arguments!");
            return false;
        }
    }


}
