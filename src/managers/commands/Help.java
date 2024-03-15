package managers.commands;
import exceptions.BuildObjectException;
import managers.Command;
import managers.CommandManager;

import java.util.HashMap;
import java.util.Optional;

/**
 * The Help class is a command that provides information about the available commands in the program.
 * It extends the Command class and overrides its execute() and checkArgument() methods.
 */
public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows reference about available commands.";
    }

    /**
     * Prints information about the available commands in the program to the console.
     * Overrides execute() method of the Command class.
     */
    @Override
    public void execute() {
        CommandManager manager = new CommandManager();

        if (this.getArgument() == null)
            manager.getCommandMap().forEach((name, command) -> System.out.println(name + " : " + command.getDescription()));
    }

    /**
     * Checks if the argument of the Exit command is null.
     * If the argument is not null, prints a message to the console indicating that the Exit command does not take any arguments.
     * Overrides the checkArgument() method of the Command class.
     * @param inputArgument the argument of the Exit command
     * @return true if the inputArgument is null, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Help 0 arguments!");
            return false;
        }
    }


}