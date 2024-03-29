package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.ModeManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to add a new studyGroup to the collection if it has a smaller  than the smallest study group in the collection.
 */
public class AddIfMin extends Command {
    ModeManager<StudyGroup> handler;


    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public AddIfMin(ModeManager<StudyGroup> handler) {
        super(false);
        this.handler = handler;
    }

    public AddIfMin() {
        super(false);
    }

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return "Adds element if it's value lower than min value.";
    }

    /**
     * Adds a new study group to the collection if its student's count is less than student's count of the smallest study group in the collection.
     * Prints a message indicating that the command does not take arguments if an argument is provided.
     */
    @Override
    public void execute(Receiver receiver) throws BuildObjectException {
        if (checkArgument(receiver.getArg())) {
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
                System.out.println("Element not added: it's not lower than min value.");
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
            System.out.println("Add_if_min hasn't arguments in this line!");
            return false;
        }
    }

}
