import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest
{
    public static void main(String[] args)
    {
//        JFrame frame = new JFrame();
//        frame.setSize(300,300);
//        frame.setLayout(null);
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//
//        JTextField textf1 =new JTextField();
//        textf1.setBounds(10,10,60, 30);
//
//        JTextField textf2 =new JTextField();
//        textf2.setBounds(90,10,60, 30);
//
//        JTextField textf3 =new JTextField();
//        textf3.setBounds(90,60,60, 30);
//
//        JButton button = new JButton("Sum");
//        button.setBounds(10,60,60, 30);
//
//        frame.add(button);
//        frame.add(textf1);
//        frame.add(textf2);
//        frame.add(textf3);
//        frame.setVisible(true);
//
//        button.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent e)
//            {
//                int n1 = Integer.parseInt(textf1.getText());
//                int n2 = Integer.parseInt(textf2.getText());
//                textf3.setText(String.valueOf(Sum(n1, n2)));
//            }
//        });
        new XOView1(3,3);
    }
    private static class XOView1
    {
        int btnCount = 1;
        JFrame fr;

        XOView1(int row, int col)
        {
            fr = new JFrame();
            while (btnCount <= row * col)
            {
                JButton b = new JButton();
                fr.add(b);
                btnCount++;
            }

            fr.setSize(400, 500);
            fr.setLayout(new GridLayout(row, col));
            fr.setVisible(true);
            fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        }
    }

    public static int Sum(int num1, int num2)
    {
        return num1 + num2;
    }
}
