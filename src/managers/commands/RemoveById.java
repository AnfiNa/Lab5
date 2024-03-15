package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managerscollection.CollectionManager;
import managerscollection.IdManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Class RemoveById provides removal of an element from the collection by its ID number.
 * Extends the Command class.
 */
public class RemoveById extends Command {

    /**
     * Constructs a new command with the specified argument requirement.
     */
    public RemoveById() {
        super(true);
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Removes element from collection by id.";
    }

    /**
     * Overrides the method execute() in the Command class.
     * Removes an element from the collection by its ID number.
     * Checks the argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }
            if (IdManager.checkGroupById(Integer.parseInt((String) this.argument)) != null){
                StudyGroup studyGroup = IdManager.checkGroupById(Integer.parseInt((String) this.argument));
                manager.getCollection().remove(studyGroup);
                System.out.println("Element successfully removed!");
            } else System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
        }

    }
    @Override
    public void execute(Object arg) throws BuildObjectException {
        this.setArgument(arg);
        this.execute();
    }




    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Remove_by_id has 1 argument of type int!");
            return false;
        }
        return true;
    }

}
