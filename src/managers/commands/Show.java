package managers.commands;

import managers.Command;
import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to show all study groups in the collection.
 */
public class Show extends Command {

    public Show() {
        super(false);
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Prints to standard output all elements of the collection in String representation.";
    }

    /**
     * Executes the Show command by printing all the study groups in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute(Receiver receiver) {
        if (checkArgument(receiver.getArg())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null)
                System.out.println("There's nothing to show.");
            else {
                manager.getCollection().forEach(System.out::println);
            }
        }
    }


    /**
     * Checks if the input argument is valid for the Show command.
     * The input argument should be null.
     *
     * @param inputArgument the input argument to be checked
     * @return true if the input argument is valid, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Show has no arguments!");
            return false;
        }
    }

}
