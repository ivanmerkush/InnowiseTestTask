package ivanmerkush.views;

import ivanmerkush.commands.Command;
import ivanmerkush.controllers.Controller;

import java.util.Map;

public abstract class View {
    protected Map<Integer, Command> commands;
    protected Controller controller;

    public View() {

    }

}
