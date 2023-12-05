package main.command;

public class Contents extends Command {
    public Contents(String directoryName) {
        super(directoryName);
    }
    public void Execute(){
        String[]list=instance.contents();
        for (String con:list) {
            System.out.println(con);
        }
    }
}
