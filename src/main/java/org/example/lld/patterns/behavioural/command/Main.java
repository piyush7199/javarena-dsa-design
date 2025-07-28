package org.example.lld.patterns.behavioural.command;

import org.example.lld.patterns.behavioural.command.command.Command;
import org.example.lld.patterns.behavioural.command.command.LightOffCommand;
import org.example.lld.patterns.behavioural.command.command.LightOnCommand;
import org.example.lld.patterns.behavioural.command.invoker.RemoteController;
import org.example.lld.patterns.behavioural.command.receiver.Light;

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteController remote = new RemoteController();

        remote.setCommand(lightOn);
        remote.pressButton();
        remote.pressUndo();
    }
}
