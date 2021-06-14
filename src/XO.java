import java.util.Arrays;
enum XOCell {X,O,Empty}
enum XOStatus {XWin,OWin,Draw,Continue}
class XOModel
{
    XOCell[][] board;
    boolean XTurn;
    int moveCount;
    int height;
    int width;
    public XOModel(int h,int w)
    {
        height = h; width = w;
        board=new XOCell[height][width];
        XTurn=true;
        moveCount=0;
    }
    public XOModel()
    {
        this(3,3);
    }
    public void Move(int row, int col)
    {
        if (board[row][col]==XOCell.Empty) {
            board[row][col] = (XTurn ? XOCell.X : XOCell.O);
            XTurn = !XTurn;
            moveCount++;
        }
    }
    public  XOStatus GameStatus()
    {
        if (victory(XTurn)) {
            if (XTurn)
                return XOStatus.XWin;
            else
                return XOStatus.OWin;
        }
        if(moveCount < height*width)
        {
            return XOStatus.Draw;
        }
        else {return XOStatus.Continue;}

    }

    private boolean victory(boolean player)
    {return true;}
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
        //Show(Board);
    }
}
