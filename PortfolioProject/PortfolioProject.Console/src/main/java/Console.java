import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    public static void WriteLine(){
        WriteLine("##########################");
    }

    public static void WriteLine(String consoleText){
        WriteTime();
        System.out.println(consoleText);
    }

    static void WriteTime(){
        System.out.print(String.format("[%s] - ", new SimpleDateFormat("HH:mm").format(new Date())));
    }

    static void WriteHeader(String consoleText){
        System.out.println(String.format("« %s »", consoleText));
    }
}
