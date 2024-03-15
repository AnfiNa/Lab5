
import managers.CommandExecutor;
import managers.CommandMode;
import managers.commands.Save;
import managerscollection.CollectionManager;
import managerscollection.ReadJSONManager;
import managerscollection.StudyGroupManager;
import managerscollection.WriteJSONManager;
import objects.*;

import java.util.TreeSet;

public class Main {
    private static final String ENV_KEY = "F:\\Lab5\\src\\main.json";;

    public static void main(String[] args) {
        try {
            ReadJSONManager.readFromFile(ENV_KEY);
            Save.setFileName(ENV_KEY);
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();

            // commands
            System.out.println("Welcome to CLI! Now you are operating with collection");
            System.out.println("Now you can enter the commands. Use help for reference.");
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Now you can enter the commands, but user manager works partially, because there is no collection whatsoever. Use help for reference.");
            System.out.println("!Please add any object of collection using add command to start workflow!");
        }
        CommandExecutor executor = new CommandExecutor();
        executor.startExecuting(System.in, CommandMode.CLI_UserMode);
    }
}
