package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;

import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.ModeManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * This class represents the RemoveLower command in the program. When executed, it removes all
 * elements from the collection that have a students count lower than the specified study group.
 */
public class RemoveLower extends Command {

    ModeManager<StudyGroup> handler;

    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public RemoveLower(ModeManager<StudyGroup> handler) {
        super(false);
        this.handler = handler;
    }

    public RemoveLower() {
        super(false);
    }

    @Override
    public String getName() {
        return "RemoveLower";
    }

    @Override
    public String getDescription() {
        return "Removes all elements from the collection that have a students count lower than the specified study group.";}

    /**
     * Removes all elements from the collection that have a students count lower than the specified study group.
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
            Iterator<StudyGroup> iter = manager.getCollection().iterator();

            int i = 0;
            while(iter.hasNext()) {
                if (newStudyGroup.getStudentsCount() > iter.next().getStudentsCount()) {
                    iter.remove();
                    i ++;
                }
            }

            if (i == 1)
                System.out.println(i + "object removed");
            else System.out.println(i + "objects removed");
        }
    }


    /**
     * Checks whether the given argument is valid for this command. Since this command doesn't
     * require an argument, this method always returns true.
     *
     * @param inputArgument the argument to be checked
     * @return true since this command doesn't require an argument
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Remove_greater hasn't arguments in this lane!");
            return false;
        }
    }



}