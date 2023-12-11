import command.*;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.*;
public class DashCommands {
    private Map<String, Command> classMap;
    private final String directoryName;
    public DashCommands(String path){
        this.directoryName=path;
        ClassDictionary();
    }
    public void ClassDictionary() {
        this.classMap = Stream.of(new Object[][]{
                {"show_contents", new Contents(this.directoryName)},
                {"show_parent", new Parent(this.directoryName)},
                {"go_to_parent", new GoToParent(this.directoryName)},
                {"check_child_directory", new HasChildNamed(this.directoryName)},
                {"create_child_directory", new CreateChildDirectory(this.directoryName)},
                {"go_to_child", new GoToChild(this.directoryName)},
                {"delete_child_directory", new DeleteSubDirectories(this.directoryName)},
                {"show_contents_with_extension", new DisplayChildrenWithExtension(this.directoryName)},
                {"display_hierarchy", new DisplayContents(this.directoryName)},
                {"search_recursive", new SearchRecursive(this.directoryName)},
                {"help",new Help(this.directoryName)}
        }).collect(Collectors.toMap(data->(String) data[0],data-> (Command) data[1]));
    }
    public void RunCommand(String commandName, String[] args) {
        Command command = classMap.get(commandName);

        if (command != null) {
            if (args== null || args.length == 0) {
                // Выполнение команды без параметров
                command.Execute();
            } else if (args.length == 1) {
                // Выполнение команды с одним параметром
                command.Execute(args[0]);
            } else {
                // Вывод сообщения об ошибке для случая, когда параметров больше 1
                System.out.println("Слишком много параметров. Команда принимает не более одного параметра.");
            }
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }
    public static Map.Entry<String, String[]> parseCommandAndParams(String[] input) {
        if (input == null || input.length == 0) {
            return new AbstractMap.SimpleEntry<>("", new String[0]);
        }

        String command = input[0];
        String[] parameters = Arrays.copyOfRange(input, 1, input.length);

        return new AbstractMap.SimpleEntry<>(command, parameters);
    }


}
