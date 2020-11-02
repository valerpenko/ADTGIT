public class PatternMatchingTest
{
    public static void main(String[] args)
    {
        char[] text = "abacaabaccabacabaabb".toCharArray();
        char[] pattern = "abacab".toCharArray();

        //int result = BruteForce.findBrute(text, pattern);
        int result = BoyerMoore.findBoyerMoore(text, pattern);

        System.out.println(result);
    }
}
