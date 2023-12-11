package command;

public class CreateChildDirectory extends Command {
    public CreateChildDirectory(String directoryName) {
        super(directoryName);
    }
    public void Execute(String NewDirectoryName){
        boolean created=instance.createChildDirectory(NewDirectoryName);
        if (created) System.out.println("Directory created");
        else System.out.println("Directory could not be created");
    }
}
