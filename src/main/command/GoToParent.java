package main.command;

public class GoToParent extends Command {
    public GoToParent(String directoryName) {
        super(directoryName);
    }
    public void Execute(){
        String parentName= instance.parent();
        if (parentName!=null)
            instance=new WorkingDirectory(parentName);
        else System.out.println("Current directory: "+ this.directoryName);
    }
}
