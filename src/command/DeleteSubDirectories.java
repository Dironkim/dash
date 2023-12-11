package command;

import java.io.File;

public class DeleteSubDirectories extends Command {
    public DeleteSubDirectories(String directoryName) {
        super(directoryName);
    }
    public void Execute(){
        boolean success=true;
        for (String name : instance.contents()){
            File file=new File(this.directoryName+"\\"+name);
            success =WorkingDirectory.deleteDirectory(file);
        }
        if (!success) System.out.println("One or more files could not be deleted");
        else System.out.println("Deletion completed successfully");
    }
}
