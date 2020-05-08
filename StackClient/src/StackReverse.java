import Exceptions.*;
import arraybased.ArrayStack;

public class StackReverse
{
    public static void main(String[] args) throws StackFullException, StackEmptyException
    {
        int[] x = new int[]{1,2,3,4,5};
        x = Reverse(x);
        for(int i : x)//foreach
            System.out.println(i);
    }

    public static int[] Reverse(int[] a) throws StackFullException, StackEmptyException
{
    ArrayStack S = new ArrayStack(a.length);
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++)
        S.Push(a[i]);
    for (int i = 0; i < a.length; i++)
        b[i] = (int)(S.Pop()) ;
    return b;
}
}
