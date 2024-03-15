package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managerscollection.CollectionManager;
import managerscollection.IdManager;
import managerscollection.ModeManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to update a city by its id number.
 */
public class Update extends Command {

    ModeManager<StudyGroup> handler;

    /**
     * Default constructor
     */
    public Update() {
        super(true);
    }

    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public Update(ModeManager<StudyGroup> handler) {
        super(true);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "update id {element}";
    }

    @Override
    public String getDescription() {
        return "Updates the value of the collection element whose id is equal to the given.";
    }

    /**
     * Executes the UpdateId command by updating a city with a specified id number in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }

            StudyGroup finalGroup = IdManager.checkGroupById((int) this.getArgument());
            if (finalGroup == null) {
                System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
                return;
            } else manager.getCollection().remove(finalGroup);
            StudyGroup newGroup = handler.buildObject();
            newGroup.setId((int) this.getArgument());
            manager.addElementToCollection(newGroup);
            System.out.println("StudyGroup Object successfully added!");

            System.out.println("Updated StudyGroup object with id : " + this.getArgument());
        }
    }


    /**
     * Checks if the input argument is valid for the UpdateId command.
     * The input argument should be a non-null string that can be parsed to an integer.
     * @param inputArgument the input argument to be checked
     * @return true if the input argument is valid, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда update_id имеет аргумент типа данных int!");
            return false;
        } else if (inputArgument instanceof String) {
            try {
                Integer.parseInt((String) inputArgument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Update_id has 1 argument of type int!");
                return false;
            }
        }
        return false;
    }
    @Override
    public void execute(Object arg) throws BuildObjectException {
        System.out.println(arg);
        this.setArgument(arg);
        this.execute();
    }

}
