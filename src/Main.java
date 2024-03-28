
import managers.CommandExecutor;
import managers.CommandMode;
import managers.commands.Save;
import managerscollection.CollectionManager;
import managerscollection.ReadJSONManager;
import managerscollection.StudyGroupManager;
import objects.*;

import java.nio.file.FileSystems;
import java.util.Objects;
import java.util.TreeSet;

public class Main {
    private static final String ENV_KEY = "F:\\Lab5\\src\\main.json";;

    public static void main(String[] args) {
        System.setErr(System.out);

        String fileName = null;
        try {
            fileName = Objects.requireNonNull(System.getenv("collectionFileName"));
        } catch (Throwable e){
            System.err.println("An error occurred in setting the path to the file. The default file path will be used ~/main.json");
            fileName = "main.json";
        }

        fileName = FileSystems.getDefault().getPath(fileName).normalize().toAbsolutePath().toString();
        System.out.println(fileName);
        try {
            ReadJSONManager.readFromFile(fileName);
            Save.setFileName(fileName);
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
