package command;

public class GoToParent extends Command {
    public GoToParent(String directoryName) {
        super(directoryName);
    }
    public void Execute(){
        String parentName= instance.parent();
        if (parentName!=null) {
            instance = new WorkingDirectory(parentName);
            System.out.println("Current directory: "+ instance.directoryName);
        }
        else System.out.println("Error...");
    }
}
