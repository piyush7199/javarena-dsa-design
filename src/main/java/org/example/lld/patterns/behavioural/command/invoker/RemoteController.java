package org.example.lld.patterns.behavioural.command.invoker;

import org.example.lld.patterns.behavioural.command.command.Command;

public class RemoteController {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}
