import ADT.Set;
import Exceptions.SetFullException;
import arraybased.ArraySet;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SpellCheck
{
    public static void main(String[] args) throws IOException, SetFullException
    {
        System.out.println(SpellChecker());
    }

    public static String SpellChecker() throws IOException, SetFullException
    {
        Set<String> dictionary = new ArraySet<>(100000);

        Path p = Paths.get("D:/desktop/dictionary.txt");
        BufferedReader reader = Files.newBufferedReader(p);
        String line;
        while((line = reader.readLine()) !=null)
            dictionary.add(line);

        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        if (!dictionary.contains(word))  // must compare words!
            System.out.println("Incorrect spelling");
        else
            return word;
        return line;
    }
}
