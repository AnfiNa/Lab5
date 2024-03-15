package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managerscollection.CollectionManager;
import managerscollection.ModeManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.TreeSet;

/**
 * Command to add a new study group to the collection.
 */
public class Add extends Command {
    ModeManager<StudyGroup> handler;

    /**
     * Default constructor
     */
    public Add() {
        super(false);
    }

    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public Add(ModeManager<StudyGroup> handler) {
        super(false);
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "Add";
    }

    @Override
    public String getDescription() {
        return "Adds new element to collection.";
    }

    /**
     * Checks whether an argument is provided or not.
     *
     * @param argument the argument to be checked.
     * @return true if no argument is provided and false otherwise.
     */
    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null)
            return true;
        else {
            System.out.println("Add hasn't arguments in this line!");
            return false;
        }
    }

    /**
     * Adds a new studyGroup to the collection if an argument is not provided.
     * Prints a message indicating that the command does not take arguments otherwise.
     */
    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            manager.addElementToCollection(handler.buildObject());
            System.out.println("StudyGroup Object successfully added!");
        }
    }
    @Override
    public void execute(Object arg) throws BuildObjectException {
    }

}
