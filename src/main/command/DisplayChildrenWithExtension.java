package main.command;

public class DisplayChildrenWithExtension extends Command {
    public DisplayChildrenWithExtension(String directoryName) {
        super(directoryName);
    }
    public void Execute(String Extension){
        String[] names= instance.getChildrenWithExtension(Extension);
        for (String name :names){
            System.out.println(name);
        }
    }
}
