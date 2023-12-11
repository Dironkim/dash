package command;

public class HasChildNamed extends Command {
    public HasChildNamed(String directoryName) {
        super(directoryName);
    }
    public void Execute(String ChildName){
        boolean res=instance.hasChildNamed(ChildName);
        if (res)
            System.out.println("Found");
        else
            System.out.println("Not found");
    }
}
