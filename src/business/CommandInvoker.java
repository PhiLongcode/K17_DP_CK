package business;

import java.util.Stack;

public class CommandInvoker {
    private Stack<Command<?, ?>> commandHistory = new Stack<>();
    
    public <T, U> T executeCommand(Command<T, U> command, U parameter) {
        T result = command.execute(parameter);
        commandHistory.push(command);
        return result;
    }
    
    public void undo() {
        if (!commandHistory.isEmpty()) {
            commandHistory.pop();
        }
    }
    
    public int getCommandHistorySize() {
        return commandHistory.size();
    }
} 