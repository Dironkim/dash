package command;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkingDirectory {
    private static WorkingDirectory instance;
    public final String directoryName;
    public WorkingDirectory(String directoryName) {
        this.directoryName = directoryName;
        saveDirectoryNameToFile();
    }
    private void saveDirectoryNameToFile() {
        try {
            // Получение пути к JAR-файлу
            String jarPath = WorkingDirectory.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            // Преобразование в объект Path и получение родительской директории
            //Path jarDirectory = Paths.get(jarPath).getParent();
            String jarDirectory = System.getProperty("user.dir");
            // Файл "working_directory.txt" в директории JAR-файла
            File configFile = new File(jarDirectory, "working_directory.txt");

            // Запись текущего пути в файл
            try (PrintWriter writer = new PrintWriter(new FileWriter(configFile))) {
                writer.println(directoryName);
                //System.out.println("Текущий путь сохранен в файл: " + configFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при сохранении текущего пути в файл.");
        }
    }
    public static WorkingDirectory getInstance(String directoryName) {
        if (instance == null) {
            instance = new WorkingDirectory(directoryName);
        }
        return instance;
    }

    public String[] contents() {
        File dir = new File(this.directoryName);
        if (!dir.exists() || !dir.isDirectory()) {
            return null;
        }
        String[] fileList = dir.list();
        return fileList;
    }

    public String parent() {
        File dir = new File(this.directoryName);
        String parentName=dir.getParent();
        return parentName;
    }

    public boolean hasChildNamed(String ChildName) {
        String ChildPath=this.directoryName+"\\"+ChildName;
        File Child=new File(ChildPath);
        return Child.exists();
    }
    boolean isValidName(String Name) {
        String regex = "^(?!.*[<>:\"/\\\\|?*])[\\p{L}\\p{S}0-1]+$";
        if (!Name.matches(regex)){
            return false;
        }
        String[] illegalPatterns = new String[]{"^con$", "^prn$", "^aux$", "^nul$", "^com[0-9]$", "^lpt[0-9]$"};
        for (String illegalPattern : illegalPatterns) {
            if (Name.matches(illegalPattern)) {
                return false;
            }
        }
        return true;
    }

    public boolean createChildDirectory(String NewDirectoryName) {
        if(!isValidName(NewDirectoryName)) return false;
        File dir = new File(directoryName+"\\"+NewDirectoryName);
        return dir.mkdir();
    }

    public static boolean deleteDirectory(File directory) {
        boolean flag=true;
        if (!directory.isDirectory())
            if (!directory.delete()) {
                flag=false;
                System.out.println(directory.getAbsolutePath() + " could not be deleted");
            }
        File[] children = directory.listFiles();

        if (children != null) {
            for (File fileOrDir : children) {
                if(!deleteDirectory(fileOrDir)) flag=false;
            }
        }

        if (!directory.delete()) {
            flag=false;
            System.out.println(directory.getAbsolutePath() + " could not be deleted");
        }
        return flag;
    }
    public String[] getChildrenWithExtension(String Extension){
        File directory=new File(this.directoryName);
        String[]children = instance.contents();
        if (children.length==0) return null;
        File[] matchingFiles = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("." + Extension);
            }
        });
        String[] names= new String[matchingFiles.length];
        for(int i=0;i<matchingFiles.length;i++){
            names[i]=matchingFiles[i].getName();
        }
        return names;
    }
    public boolean existsSubFolder(String FolderName) {
        return searchRecursive(new File(this.directoryName), FolderName);
    }
    public static boolean searchRecursive(File currentDirectory, String wantedFolderName) {
        boolean found=false;
        File[]contents=currentDirectory.listFiles();
        if (contents != null)
            for (File fileOrDirectory : contents){
                if (fileOrDirectory.isDirectory() && searchRecursive(fileOrDirectory,wantedFolderName))
                    return true;
            }
        String FolderPath=currentDirectory.getPath()+"\\"+wantedFolderName;
        return new File(FolderPath).isDirectory();
    }
    public static void listFilesAndDirectories(File directory, int level) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                String indent = "  ".repeat(level);
                String formattedName = String.format("%s%s", indent, file.getName());
                System.out.println(formattedName);
                if (file.isDirectory()) {
                    listFilesAndDirectories(file, level + 1);
                }
            }
        }
    }

}
