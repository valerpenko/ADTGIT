import ADT.Set;
import Exceptions.SetFullException;
import arraybased.ArraySet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class SpellChecker
{
    private static ArraySet<String> dictionary;
    public static void main(String[] args) throws IOException, SetFullException
    {
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        Set<String> prompts = SpellChecker.Check(word);

        //show proposed words
    }

    static
    {
        dictionary = new ArraySet<String>(100000);

        Path p = Paths.get("D:\\Project_files\\ADTGIT\\SetClient\\dictionary.txt");
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line=null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(line!=null)
        {
            try {
                dictionary.add(line);
            } catch (SetFullException e) {
                e.printStackTrace();
            }

            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    Set<String> dictionary = new ArraySet<String>(100000);
//
//    Path p = Paths.get("D:/desktop/dictionary.txt");
//    BufferedReader reader = Files.newBufferedReader(p);
//    String line;
//        while((line = reader.readLine()) !=null)
//        dictionary.add(line);
//
//    Scanner scan = new Scanner(System.in);
//    String word = scan.nextLine();
//        if (!dictionary.contains(word))  // must compare words!
//        System.out.println("Incorrect spelling");
//        else
//                return word;
//        return line;
    public static Set<String> Check(String word) throws SetFullException
    {
        Set<String> result= new ArraySet<>(100);

        if (dictionary.contains(word))
        {
            return result; //empty!
        }
        else
        {
            MisspellingIterator<String> mIterator = new MisspellingIterator<>(word);
            //String mp = null;
            while(mIterator.hasNext())//mp=mIterator.hasNext()!=null
            {
                String w=mIterator.Next();
                if (dictionary.contains(w))
                    result.add(w);
            }
            return result;
        }
    }
    private static class MisspellingIterator<E>
    {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        private List<String> mispellings = new ArrayList<>();
        private int currIndex=-1;
        //Iterator<String> iter = mispellings.iterator();

        public MisspellingIterator(String word) throws SetFullException
        {
            mispellings.add(word);//fill misspelling
            AddExtraChar(word);
            ReplaceChar(word);
            DeleteChar(word);

        }
        private void ReplaceChar(String word)
        {
            for(char ch : alphabet)
            {
                for (int j = 0; j < word.length(); j++)
                {
                    mispellings.add(word.replace(word.charAt(j), ch));
                }
            }

        }
        private void AddExtraChar(String word)
        {
            StringBuilder str = new StringBuilder(word);

            for(char ch : alphabet)
            {
                for (int j = 0; j < str.length(); j++)
                {
                    mispellings.add(str.insert(j, ch).toString());
                }
            }
        }
        private void DeleteChar(String word)
        {
            char del = 0;

            for (int i = 0; i < word.length(); i++)
                mispellings.add(word.replace(word.charAt(i),del));
        }

        public String Next()
        {
            if(hasNext())
            {
                currIndex++;
                return mispellings.get(currIndex);
            }
            else return null;

        }
        public boolean hasNext()
        {
            if (currIndex<mispellings.size()-1)
                return true;
            else
                return false;
        }
    }
}
