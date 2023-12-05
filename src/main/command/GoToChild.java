package main.command;

public class GoToChild extends Command {
    public GoToChild(String directoryName) {
        super(directoryName);
    }
    public void Execute(String ChildName){
        if (instance.hasChildNamed(ChildName)){
            instance = new WorkingDirectory(this.directoryName + "\\" + ChildName);
            System.out.println("Current directory: "+this.directoryName);
        }
        else System.out.println("Could not find the directory");
    }
}
