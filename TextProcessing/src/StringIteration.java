import java.util.Comparator;

public class StringIteration
{
    public static void decompose(String str, int count, String template)
    {
        template = Character.toString(str.charAt(0));
        count = 1;
        int curPos = 1;

        while(curPos < str.length())
        {
            int templatePos = 0;
            while(templatePos < template.length())
            {
                if(str.charAt(curPos + templatePos) != template.charAt(templatePos))
                {
                    break;
                }
                else
                {
                    templatePos++;
                }
            }
            if(templatePos < template.length())
            {
                template = str.substring(0, curPos+1);
                count = 1;
                curPos = templatePos;
            }
            else
            {
                count++;
                curPos+=templatePos;
            }
            //curPos++;
        }

    }
}
