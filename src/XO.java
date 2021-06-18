import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JButton [][] buttons;
    JFrame fr;

    XOView(XOModel mod)
    {
        fr = new JFrame();
        fr.setSize(400,400);
        fr.setLayout(new GridLayout(mod.height, mod.width));
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);

        buttons = new JButton[mod.height][mod.width];

        for (JButton[] row : buttons)
        {
            for (JButton cell : row)

            {
                cell = new JButton();
                JButton finalCell = cell;
                cell.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
//                    mod.Move(0,0);
                        if(mod.XTurn) { finalCell.setText("X"); }
                        else{ finalCell.setText("O"); }
                    }
                });
                fr.add(cell);
            }
        }

        fr.setVisible(true);

    }
    public  void Check()
    {
        for (int i = 0; i < buttons.length; i++)
        {
            for (int j = 0; j < buttons[i].length; j++)
            {

            }
        }
    }
}


public class XO
{
    public static void main(String[] args)
    {
        XOModel model = new XOModel();
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
