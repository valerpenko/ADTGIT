import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
enum XOCell {X,O,Empty}
enum XOStatus {XWin,OWin,Draw,Continue}

class XOModel
{
    private XOCell[][] board;
    boolean XTurn;
    int moveCount;
    int height;
    int width;
    public XOModel(int h,int w)
    {
        height = h; width = w;
        board=new XOCell[height][width];
        for(int row=0;row<height;row++)
            for(int col=0;col<width;col++)
                board[row][col]=XOCell.Empty;

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
    public  XOStatus GameStatus(int row, int col)
    {//if (if state not changed see moveCount)
        //return oldStatus
        //else
        if (victory(!XTurn, row, col)) {
            if (!XTurn)
                return XOStatus.XWin;
            else
                return XOStatus.OWin;
        }
        if(moveCount < height*width)
        {
            //oldStatus = XOStatus.Continue
            return XOStatus.Continue;
        }
        else {return XOStatus.Draw;}

    }
    public XOCell GetCell(int row, int col)
    {
        return board[row][col];
    }
    private boolean victory(boolean XTurn, int row, int col)
    {
        XOCell player;
        if (XTurn)  player=XOCell.X;
        else player=XOCell.O;
            try {
                if (board[row][col] == player && board[row][col - 1] == player && board[row][col + 1] == player)
                    return true;
            }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row][col - 1] == player && board[row][col - 2] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row - 1][col] == player && board[row + 1][col] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row - 1][col] == player && board[row - 2][col] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row + 1][col] == player && board[row + 2][col] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row - 1][col - 1] == player && board[row + 1][col + 1] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row - 1][col - 1] == player && board[row - 2][col - 2] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row + 1][col + 1] == player && board[row + 2][col + 2] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row - 1][col + 1] == player && board[row + 1][col - 1] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row - 1][col + 1] == player && board[row - 1][col + 2] == player)
                return true;
        }catch (Exception e){}
        try {
            if (board[row][col] == player && board[row + 1][col - 1] == player && board[row + 2][col - 2] == player)
                return true;
        }catch (Exception e){}
        return false;
    }
}

class XOView
{
    JButton [][] buttons;
    JFrame fr;
    XOModel xoModel;
    XOStatus state;

    XOView(XOModel mod)
    {
        xoModel=mod;
        state = XOStatus.Continue;
        fr = new JFrame();
        fr.setSize(400,400);
        fr.setLayout(new GridLayout(mod.height, mod.width));
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        fr.setTitle("Continue");
        buttons = new JButton[mod.height][mod.width];
        for (int row = 0;  row< buttons.length; row++)
        {   for (int col = 0; col < buttons[row].length; col++)
            {   buttons[row][col] = new JButton();
                JButton finalCell = buttons[row][col];
                int finalRow = row;
                int finalCol = col;
                buttons[row][col].addActionListener(new ActionListener()
                {   public void actionPerformed(ActionEvent e)
                    {   if(state == XOStatus.Continue)
                        {
                            mod.Move(finalRow, finalCol);
                            Refresh();
                            state = mod.GameStatus(finalRow, finalCol);
                            switch (state) {
                                case Draw:
                                    fr.setTitle("Draw");
                                    break;
                                case XWin:
                                    fr.setTitle("player X won");
                                    break;
                                case OWin:
                                    fr.setTitle("player O won");
                                    break;
                            }
                        }
                    }
                });
                fr.add(buttons[row][col]);
            }
        }
        fr.setVisible(true);
    }
    void Refresh()
    {
        for (int row = 0;  row< buttons.length; row++)
        {
            for (int col = 0; col < buttons[row].length; col++)
            {
                switch (xoModel.GetCell(row,col))
                {
                    case X:
                        buttons[row][col].setText("X");break;
                    case O:
                        buttons[row][col].setText("O");break;
                    default:
                        buttons[row][col].setText("");break;
                }
            }
        }
    }
}


public class XO
{
    public static void main(String[] args)
    {
        XOModel model = new XOModel(6,6);
        XOView view = new XOView(model);
//        int X = 1;
//        int O = 0;
//        int Empty = -1;
//
//        int[][] Board = {{Empty, Empty, Empty},
//                {Empty, Empty, Empty},
//                {Empty, Empty, Empty}};
//
//        //for (int[] row : Board)
//        System.out.println(Arrays.toString(Board));
//        //Show(Board);
    }
}
