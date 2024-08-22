import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtil {
    private BufferedReader br;
    IOUtil() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readString() throws IOException {
        return br.readLine();
    }

    public void writeLine(String text) {
        System.out.println(text);
    }
}
