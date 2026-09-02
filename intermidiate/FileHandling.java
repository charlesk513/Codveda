import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class FileManager {

    public void read(BufferedReader reader) throws IOException {
        String content;
        while ((content = reader.readLine()) != null) {
            System.out.println(content);
        }
        if (content == null) {
            System.out.println("Empty file nothing to read!");
        }
    }

    public void write(FileWriter writer, String phrase) {
        try {
            writer.write(phrase);
            writer.close();
            System.out.println("Successfully written data to the file");
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }

    }

    public void append(FileWriter writer, String words) {
        try {
            writer.append(words);
            writer.close();
            System.out.println("Successfully appended data to the file");
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }

    }

}

public class FileHandling {
    public static void main(String[] args) throws IOException {
        FileManager manage = new FileManager();
        BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"));
        FileWriter appender = new FileWriter("data/input.txt", true);
        FileWriter writer = new FileWriter("data/input.txt");

        System.out.print("read/write/append?: ");
        String input = enter.readLine().toLowerCase();
        if (input.equals("read")) {
            manage.read(reader);
        } else if (input.equals("write")) {
            System.out.print("Enter the data: ");
            String data = enter.readLine();
            manage.write(writer, data);
        }
        if (input.equals("append")) {
            System.out.print("Enter the data: ");
            String data = enter.readLine();
            manage.append(appender, data);
        }

    }
}
