package managers;
import exceptions.BuildObjectException;

public interface CommandInterface {
    void execute() throws BuildObjectException;
    void execute(Object arg) throws BuildObjectException;
    String getName();
    String getDescription();
    boolean checkArgument(Object argument);
}
