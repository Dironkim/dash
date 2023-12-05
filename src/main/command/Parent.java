package main.command;

public class Parent extends Command {
    public Parent(String directoryName) {
        super(directoryName);
    }
    public void Execute(){
        System.out.println(instance.parent());
    }
}
