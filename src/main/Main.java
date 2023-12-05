package main;

import java.util.Map;

import static main.DashCommands.*;

public class Main {
    public static void main(String[] args) {
        String path = "E:\\дз";
        PublicWorkingDirectory one = new PublicWorkingDirectory(path);
        DashCommands commands = new DashCommands(path);
        Map.Entry<String, String[]> result = parseCommandAndParams(args);
        commands.RunCommand(result.getKey(),result.getValue());
    }
}