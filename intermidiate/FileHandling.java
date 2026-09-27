import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class FileManager {

    public void read(BufferedReader reader) throws IOException {
        String content;
        boolean empty = true;

        while ((content = reader.readLine()) != null) {
            System.out.println(content);
            empty = false;
        }

        if (empty) {
            System.out.println("Empty file, nothing to read!");
        }

        reader.close();
    }

    public void write(FileWriter writer, String phrase) {
        try {
            writer.write(phrase);
            writer.close();
            System.out.println("Successfully written data to the file");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void append(FileWriter writer, String words) {
        try {
            writer.append('\n' + words);
            writer.close();
            System.out.println("Successfully appended data to the file");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
}

public class FileHandling {

    public static void main(String[] args) throws IOException {

        FileManager manage = new FileManager();
        BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("read/write/append?: ");
        String input = enter.readLine().toLowerCase();

        if (input.equals("read")) {

            BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"));

            manage.read(reader);

        } else if (input.equals("write")) {

            System.out.print("Enter the data: ");
            String data = enter.readLine();

            FileWriter writer = new FileWriter("data/data.txt");

            manage.write(writer, data);

        } else if (input.equals("append")) {

            System.out.print("Enter the data: ");
            String data = enter.readLine();

            FileWriter appender = new FileWriter("data/data.txt", true);

            manage.append(appender, data);

        } else {
            System.out.println("Invalid operation!");
        }

        enter.close();
    }
}