package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.IdManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Class CountLessThanTransferredStudents provides output the number of elements whose transferredStudents field value is less than the specified one
 * Extends the Command class.
 */
public class CountLessThanTransferredStudents extends Command {

    public CountLessThanTransferredStudents() {
        super(true);
    }

    @Override
    public String getName() {
        return "count_less_than_transferred_students";
    }

    @Override
    public String getDescription() {
        return "Prints the number of elements whose transferredStudents field value is less than the specified one.";
    }

    /**
     * Overrides the method execute() in the Command class.
     * Prints the number of elements whose transferredStudents field value is less than the specified one.
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
            long commandArgument = Long.parseLong((String) receiver.getArg());
            long result = 0;
            for (StudyGroup studyGroup: manager.getCollection().descendingSet()){
                if (studyGroup.getTransferredStudents() < commandArgument){
                    result++;
                }
            }
            System.out.print("The number of elements whose transferredStudents field value is less than the specified one: " + Long.toString(result));
        }
    }


    /**
     * Checks if the input argument is valid for the UpdateId command.
     * The input argument should be a non-null string that can be parsed to a long.
     * @param inputArgument the input argument to be checked
     * @return true if the input argument is valid, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда count_less_than_transferred_students имеет аргумент типа данных long!");
            return false;
        } else {
            try {
                Long.parseLong((String) inputArgument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("count_less_than_transferred_students has 1 argument of type long!");
                return false;
            }
        }
    }

}
