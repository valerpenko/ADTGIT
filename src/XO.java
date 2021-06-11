import java.util.Arrays;

class XOModel {
}
class XOView
{
    public static void Show(int [][] Board)
    {
        for (int[] row : Board)
        {
            for (int cell : row)
            {
                if (cell == 1)
                {
                    System.out.println("X");
                }
                else if (cell == 0)
                {
                    System.out.println("O");
                }
                else
                {
                    System.out.println("_");
                }
            }
        }
    }
}
public class XO {
    public static void main(String[] args) {
        int X = 1;
        int O = 0;
        int Empty = -1;

        int[][] Board = {{Empty, Empty, Empty},
                {Empty, Empty, Empty},
                {Empty, Empty, Empty}};

        //for (int[] row : Board)
        System.out.println(Arrays.toString(Board));
        Show(Board);
    }
}
