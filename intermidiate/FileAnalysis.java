import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAnalysis {

    /*
     * function name: analysis
     * argument: reader, writer,
     * 
     * /*
     * description: This function takes references to BufferedReader
     * and BufferedWriter objects, analyzes the file by counting the
     * number of lines and words in input.txt, and stores the analyzed
     * information in output.txt.
     * 
     * return type: void
     */
    public static void analysis(BufferedReader reader, BufferedWriter writer) throws IOException {

        String content;
        int numberOfLines = 0;
        int numberOfWords = 0;

        while ((content = reader.readLine()) != null) {

            numberOfLines++;

            if (!content.trim().isEmpty()) {
                String[] words = content.trim().split("\\s+");
                numberOfWords += words.length;
            }
        }

        writer.write("File Analysis Results");
        writer.newLine();
        writer.write("---------------------");
        writer.newLine();
        writer.write("Number of lines: " + numberOfLines);
        writer.newLine();
        writer.write("Number of words: " + numberOfWords);
        writer.newLine();
    }

    public static void main(String[] args) {

        try (
                BufferedReader reader = new BufferedReader(new FileReader("data/input.txt"));

                BufferedWriter writer = new BufferedWriter(new FileWriter("data/output.txt"))) {

            analysis(reader, writer);

            System.out.println("Analysis completed successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("File not found!");

        } catch (IOException e) {

            System.out.println("Error handling the file: " + e.getMessage());
        }
    }
}
