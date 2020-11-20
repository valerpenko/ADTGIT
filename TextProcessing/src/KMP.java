public class KMP
{
    // Returns the lowest index at which substring pattern begins in text (or else − 1).
    public static int findKMP(char[ ] text, char[ ] pattern)
    {
        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0; // trivial search for empty string
        int[ ] fail = computeFailKMP(pattern); // computed by private utility
        int j = 0; // index into text
        int k = 0; // index into pattern
        while (j < n)
        {
            if (text[j] == pattern[k])
            { // pattern[0..k] matched thus far
                if (k == m - 1) return j - m + 1; // match is complete
                j++; // otherwise, try to extend match
                k++;
            }
            else if (k > 0)
            k = fail[k - 1]; // reuse suffix of P[0..k-1]
            else
                j++;
        }
        return - 1; // reached end without match
    }

    private static int[ ] computeFailKMP(char[ ] pattern)
    {
        int m = pattern.length;
        int[ ] fail = new int[m]; // by default, all overlaps are zero
        int j = 1;
        int k = 0;
        while (j < m)
        { // compute fail[j] during this pass, if nonzero
            if (pattern[j] == pattern[k])
            { // k + 1 characters match thus far
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) // k follows a matching prefix
                k = fail[k - 1];
            else // no match found starting at j
             j++;
        }
        return fail;
    }
}
