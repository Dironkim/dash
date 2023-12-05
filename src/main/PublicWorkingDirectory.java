package main;

import main.command.WorkingDirectory;

import java.io.File;

public class PublicWorkingDirectory {
    private static WorkingDirectory instance;
    private final String directoryName;
    public PublicWorkingDirectory(String directoryName){
        instance=WorkingDirectory.getInstance(directoryName);
        this.directoryName=directoryName;
    }
    public void Contents(){
        String[]list=instance.contents();
        for (String con:list) {
            System.out.println(con);
        }
    }
    public void Parent()
    {
        System.out.println(instance.parent());
    }
    public void GoToParent() {
        String parentName= instance.parent();
        if (parentName!=null)
            instance=new WorkingDirectory(parentName);
        else System.out.println("Current directory: "+ this.directoryName);
    }
    public void HasChildNamed(String ChildName){
        boolean res=instance.hasChildNamed(ChildName);
        if (res)
            System.out.println("Found");
        else
            System.out.println("Not found");
    }
    public void CreateChildDirectory(String NewDirectoryName){
        boolean created=instance.createChildDirectory(NewDirectoryName);
        if (created) System.out.println("Directory created");
        else System.out.println("Directory could not be created");
    }
    public void GoToChild(String ChildName) {
        if (instance.hasChildNamed(ChildName)){
            instance = new WorkingDirectory(this.directoryName + "\\" + ChildName);
            System.out.println("Current directory: "+this.directoryName);
        }
        else System.out.println("Could not find the directory");
    }
    public void DeleteSubDirectories(){
        boolean success=true;
        for (String name : instance.contents()){
            File file=new File(this.directoryName+"\\"+name);
            success =WorkingDirectory.deleteDirectory(file);
        }
        if (!success) System.out.println("One or more files could not be deleted");
        else System.out.println("Deletion completed successfully");
    }
    public void DisplayChildrenWithFormat(String Extension){
        String[] names= instance.getChildrenWithExtension(Extension);
        for (String name :names){
            System.out.println(name);
        }
    }
    public void DisplayContents(){
        String[]children= instance.contents();
        File dir = new File(this.directoryName);
        if (instance.contents().length!=0) {
            WorkingDirectory.listFilesAndDirectories(dir,0);
        }
    }
    public void ExistsSubFolder(String FolderName){
        boolean res=instance.existsSubFolder(FolderName);
        if (res) System.out.println("The directory found");
        else System.out.println("The directory could not be found");
    }

}
