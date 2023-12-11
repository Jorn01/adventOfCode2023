import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {
    public static String readFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        StringBuilder sb = new StringBuilder();

        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
            sb.append("\n");
        }

        scanner.close();
        return sb.toString();
    }
}
