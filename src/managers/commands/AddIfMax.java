package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managerscollection.CollectionManager;
import managerscollection.ModeManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to add a new studyGroup to the collection if it has a bigger than the biggest city in the collection.
 */
public class AddIfMax extends Command {
    ModeManager<StudyGroup> handler;

    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public AddIfMax(ModeManager<StudyGroup> handler) {
        super(false);
        this.handler = handler;
    }

    public AddIfMax() {
        super(false);
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "Adds element if it's value bigger than max value.";
    }

    /**
     * Adds a new study group to the collection if its student's count is bigger than student's count of the biggest study group in the collection.
     * Prints a message indicating that the command does not take arguments if an argument is provided.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }
            StudyGroup newStudyGroup = handler.buildObject();

            if (newStudyGroup.getStudentsCount() > manager.getCollection().first().getStudentsCount()) {
                manager.addElementToCollection(newStudyGroup);
                System.out.println("Element added!");
            }
            {
                System.out.println("Element not added: it's not bigger than max value.");
            }

        }
    }

    /**
     * Checks whether an argument is provided or not.
     *
     * @param inputArgument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Add_if_max hasn't arguments in this line!");
            return false;
        }
    }

    @Override
    public void execute(Object arg) throws BuildObjectException {
    }
}