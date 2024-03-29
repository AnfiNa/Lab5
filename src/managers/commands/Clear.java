package managers.commands;

import managers.Command;
import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to clear the collection.
 */
public class Clear extends Command {

    public Clear() {
        super(false);
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Clears collection";
    }

    /**
     * Clears the collection if no argument is provided.
     * Prints a message indicating that the command does not take arguments otherwise.
     */
    @Override
    public void execute(Receiver receiver) {
        if (checkArgument(receiver.getArg())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null) {
                System.out.println("There is nothing to clear)");
                return;
            }
            manager.clearCollection();
            System.out.println("Collection successfully cleared!");
        }
    }

    /**
     * Checks whether an argument is provided or not.
     * @param inputArgument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Clear has no arguments!");
            return false;
        }
    }

}