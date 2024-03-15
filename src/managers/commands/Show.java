package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managerscollection.CollectionManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to show all cities in the collection.
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
     * Executes the Show command by printing all the cities in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
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

    @Override
    public void execute(Object arg) throws BuildObjectException {
    }
}
