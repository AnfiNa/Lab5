package managers.commands;

import exceptions.BuildObjectException;
import managers.Command;
import managers.CommandExecutor;
import managers.CommandMode;
import managers.Receiver;
import managerscollection.CollectionManager;
import managerscollection.StudyGroupManager;
import objects.StudyGroup;

import java.io.*;
import java.nio.file.*;

import java.util.ArrayDeque;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 ExecuteScript is a class that represents the execute_script command.
 It reads and executes a script from a file that contains a sequence of commands and arguments.
 It also prevents recursion by keeping track of file paths that have already been executed.
 */
public class ExecuteScript extends Command {

    /**
     Constructs a new ExecuteScript object.
     Initializes commandMap with the command map from CommandManager, and creates an empty ArrayList of filePaths.
     */
    public ExecuteScript() {
        super(true);
    }
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "Reads and executes script from file.";
    }

    /**
     Executes the command by reading and parsing the script file.
     Each command in the file is executed using its corresponding command object from the command map.
     The method also checks for recursion by ensuring that a file path is not executed twice.
     */
    @Override
    public void execute(Receiver receiver) throws IllegalArgumentException {
        String fileName = FileSystems.getDefault().getPath(receiver.getArg()).normalize().toAbsolutePath().toString();
        System.out.println(fileName);
        try {
            CollectionManager<TreeSet<StudyGroup>, StudyGroup> manager = StudyGroupManager.getStudyGroupManager();
            if (manager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return;
            }

            if (checkRecursion(Path.of(fileName), new ArrayDeque<>())) {
                System.out.println("При анализе скрипта обнаружена рекурсия. Устраните ее перед исполнением.");
                return;
            }
            System.out.println("Executing script");
            CommandExecutor executor = new CommandExecutor();
            executor.startExecuting(new FileInputStream(fileName), CommandMode.NonUserMode);
        } catch (InvalidPathException e) {
            System.out.println("Provided argument path isn't legal. Try again.");
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("File not found! Try again.");
        } catch (SecurityException e) {
            System.out.println("Access denied. Try again with another file.");
        } catch (IOException e) {
            System.out.println("Something went wrong during file handling. Try again. (" + e.getMessage() + ")");
        }
    }


    /**
     * Checks if a given file path leads to a recursive call of a script.
     *
     * @param path the file path to check for recursion
     * @param stack a stack containing previously visited file paths
     * @return true if the given file path leads to a recursive call, false otherwise
     * @throws IOException if an I/O error occurs while reading the file
     */
    private boolean checkRecursion(Path path, ArrayDeque<Path> stack) throws IOException {
        if (stack.contains(path)) return true;
        stack.addLast(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find())
        {
            Path newPath = Path.of(patternMatcher.group().split(" ")[1]);
            if(checkRecursion(newPath, stack)) return true;
        }
        stack.removeLast();
        return false;
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Execute_script has 1 argument - path to file!");
            return false;
        } else if (inputArgument instanceof String) {
            return true;
        }
        return false;

    }

}
