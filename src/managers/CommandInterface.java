package managers;
import exceptions.BuildObjectException;

public interface CommandInterface {
    void execute(Receiver receiver) throws BuildObjectException;
    String getName();
    String getDescription();
    boolean checkArgument(Object argument);
}
