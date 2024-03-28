package managers.commands;

import managers.Command;
import managers.Receiver;
import managerscollection.StudyGroupManager;
import managerscollection.WriteJSONManager;

/**
 * Save class represents the Save command in the program. When executed, it writes the current
 * collection to the file.
 */
public class Save extends Command {
    static String fileName;

    public Save() {
        super(false);
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Saves collection to file.";
    }

    /**
     * Executes the Save command. Writes the current collection to the file.
     */
    @Override
    public void execute(Receiver receiver) {
        if (checkArgument(receiver.getArg())) {
            StudyGroupManager collectionManager = StudyGroupManager.getStudyGroupManager();
            WriteJSONManager.writeCollection(fileName, collectionManager.getCollection());
        }
    }


    /**
     * Checks whether the given argument is valid for this command. Since this command doesn't
     * require an argument, this method always returns true.
     * @param inputArgument the argument to be checked
     * @return true since this command doesn't require an argument
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Save has no arguments!");
            return false;
        }
    }

    public static void setFileName(String name) {
        fileName = name;
    }


}
