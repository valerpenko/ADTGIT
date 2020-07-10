import ADT.Set;
import Exceptions.SetFullException;
import arraybased.ArraySet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SpellChecker
{
    private static Set<String> dictionary;
    public static void main(String[] args) throws IOException, SetFullException
    {
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        SpellChecker.Check(word);
        //show proposed words
    }

    static
    {
        dictionary = new ArraySet<String>(100000);

        Path p = Paths.get("D:/desktop/dictionary.txt");
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dictionary.add(line);
        } catch (SetFullException e) {
            e.printStackTrace();
        }
    }
//    public  SpellChecker() throws IOException, SetFullException
//     {
//        dictionary = new ArraySet<String>(100000);
//
//        Path p = Paths.get("D:/desktop/dictionary.txt");
//        BufferedReader reader = Files.newBufferedReader(p);
//        String line = reader.readLine();
//        dictionary.add(line);
//    }
    public Set<String> Check(String word) throws SetFullException {
        Set<String> result= new ArraySet<>();

        if (dictionary.contains(word))
            return result;
        else {
            Iterator<String> mIterator = new MispellingIterator(word);
            String mp;
            while(mp=mIterator.Next()!=null)
            {
                if (dictionary.contains(mp))
                    result.add(mp);
            }
            return mp;
        }
    }
    class MispellingIterator
    {
        private Set<String> mispellings=new ArraySet<>();
        public MispellingIterator(String word)
        {
            //fill misspelling
        }
        public String Next()
        {

        }
    }
}
