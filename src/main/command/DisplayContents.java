package main.command;

import java.io.File;

public class DisplayContents extends Command {
    public DisplayContents(String directoryName) {
        super(directoryName);
    }
    public void Execute(){
        String[]children= instance.contents();
        File dir = new File(this.directoryName);
        if (instance.contents().length!=0) {
            WorkingDirectory.listFilesAndDirectories(dir,0);
        }
    }
}
