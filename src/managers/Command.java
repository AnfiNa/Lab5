package managers;

import exceptions.BuildObjectException;

public abstract class Command implements CommandInterface {
    private final boolean HAS_ARGUMENT;
    protected Object argument;
    protected Command(boolean hasArgument) {
        this.HAS_ARGUMENT = hasArgument;
    }

    public boolean getHasArgument() {
        return HAS_ARGUMENT;
    }


    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = (String) argument;
    }
}