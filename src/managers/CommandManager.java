package managers;

import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import managers.commands.*;
import managerscollection.ModeManager;
import objects.StudyGroup;
import exceptions.UnknownCommandException;

import java.util.*;

/**
 The CommandManager class is responsible for managing all available commands in the application.
 It provides a static method getCommandMap() that returns a HashMap of all commands where the key is
 the command name and the value is an instance of the corresponding Command subclass.
 */
public class CommandManager {


    /**
     A HashMap object that stores all available commands in the application. The key is the command name
     and the value is an instance of the corresponding Command subclass.
     */
    private LinkedHashMap<String, Command> commandMap;
    public CommandManager(){
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("update", new Update());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("count_less_than_transferred_students", new CountLessThanTransferredStudents());
        commandMap.put("print_field_descending_students_count", new PrintFieldDescendingStudentsCount());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("add", new Add());
        commandMap.put("add_if_min", new AddIfMin());
        commandMap.put("add_if_max", new AddIfMax());
        commandMap.put("update", new Update());
        commandMap.put("remove_lower", new RemoveLower());
    }
    /**
     * Default command manager setup.
     */
    public CommandManager(CommandMode mode, Scanner scanner){
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("update", new Update());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("count_less_than_transferred_students", new CountLessThanTransferredStudents());
        commandMap.put("print_field_descending_students_count", new PrintFieldDescendingStudentsCount());
        commandMap.put("remove_by_id", new RemoveById());
        ModeManager<StudyGroup> handler = null;
        switch (mode)
        {
            case CLI_UserMode -> handler = new StudyGroupCLIManager();
            case NonUserMode -> handler = new StudyGroupNonUserManager(scanner);
        }
        commandMap.put("add", new Add(handler));
        commandMap.put("add_if_min", new AddIfMin(handler));
        commandMap.put("add_if_max", new AddIfMax(handler));
        commandMap.put("update", new Update(handler));
        commandMap.put("remove_lower", new RemoveLower(handler));
    }

    /**
     Returns the commandMap HashMap that stores all available commands in the application.
     @return the HashMap of all commands where the key is the command name and the value is an instance
     of the corresponding Command subclass.
     */
    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
    /**
     * Universe method for command executing.
     */
    public void executeCommand(String[] args){
        try {
            if (args.length > 1){
                Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( ")).execute(args[1]);
            }
            else {
            Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( ")).execute();}
        } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
            System.err.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            throw new CommandInterruptedException(e);
        } catch (BuildObjectException | UnknownCommandException e) {
            System.err.println(e.getMessage());
            throw new CommandInterruptedException(e);
        } catch (Exception e) {
            System.err.println("В командном менеджере произошла непредвиденная ошибка! ");
            throw new CommandInterruptedException(e);
        }
    }


}
