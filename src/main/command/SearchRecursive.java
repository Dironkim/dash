package main.command;

public class SearchRecursive extends Command {
    public SearchRecursive(String directoryName) {
        super(directoryName);
    }
    public void Execute(String FolderName){
        boolean res=instance.existsSubFolder(FolderName);
        if (res) System.out.println("The directory found");
        else System.out.println("The directory could not be found");
    }
}
