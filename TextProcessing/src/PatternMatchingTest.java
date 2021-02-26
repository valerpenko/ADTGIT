public class PatternMatchingTest
{
    public static void main(String[] args)
    {
        char[] text = "abacaabaccabacabaabb".toCharArray();
        char[] pattern = "abacab".toCharArray();

        int result;
        result= BruteForce.findBrute(text, pattern);
        System.out.println(result);

        result = KMP.findKMP(text, pattern);
        System.out.println(result);

        result = BoyerMoore.findBoyerMoore(text, pattern);
        System.out.println(result);


//        String text1 = "abcabcabc";
//
//        Integer count = 0;
//        String template = "";
//        StringIteration.decompose(text1);
//        System.out.println( StringIteration.template);
//        System.out.println( StringIteration.count);
    }
}
