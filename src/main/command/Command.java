package main.command;

public class Command {
    protected static WorkingDirectory instance;
    protected final String directoryName;
    public Command(String directoryName){
        instance= WorkingDirectory.getInstance(directoryName);
        this.directoryName=directoryName;
    }
    public void Execute() { throw new RuntimeException("Not implemented");}
    public void Execute(String parameter){ throw new RuntimeException("Not implemented");}
}
