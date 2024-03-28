package managers;

import exceptions.BuildObjectException;

import javax.sound.midi.Receiver;

public abstract class Command implements CommandInterface {
    private final boolean HAS_ARGUMENT;
    protected Command(boolean hasArgument) {
        this.HAS_ARGUMENT = hasArgument;
    }

    public boolean getHasArgument() {
        return HAS_ARGUMENT;
    }

}