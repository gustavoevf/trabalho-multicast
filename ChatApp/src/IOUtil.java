import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtil {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() throws IOException {
        return br.readLine();
    }

    public static void writeLine(String text) {
        System.out.println(text);
    }
}
