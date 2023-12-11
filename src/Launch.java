import java.nio.file.Path;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
public class Launch {
    public static void main(String[] args) {
        String path = getWorkingDirectory("D:\\дз");
        PublicWorkingDirectory one = new PublicWorkingDirectory(path);
        DashCommands commands = new DashCommands(path);
        //args = new String[]{"show_contents"};
        //args =new String[]{"go_to_parent"};
        Map.Entry<String, String[]> result = DashCommands.parseCommandAndParams(args);
        commands.RunCommand(result.getKey(),result.getValue());
    }
    public static String getWorkingDirectory(String defaultValue) {
        try {
            // Получение пути к JAR-файлу
            String jarPath = Launch.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            // Преобразование в объект Path и получение родительской директории
            String jarDirectory = System.getProperty("user.dir");
            // Файл "working_directory.txt" в директории JAR-файла
            File configFile = new File(jarDirectory, "working_directory.txt");

            if (configFile.exists()) {
                // Если файл существует, попробовать считать строку из него
                BufferedReader reader = new BufferedReader(new FileReader(configFile));
                String directoryPath = reader.readLine();
                // Проверка, является ли считанная строка допустимым путем к директории
                File directory = new File(directoryPath);
                if (directory.exists() && directory.isDirectory()) {
                    return directoryPath;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return defaultValue;
        }

        // Если чтение не удалось или путь не является допустимым, вернуть значение по умолчанию
        return defaultValue;
    }
}