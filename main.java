package snakegame;

import java.awt.Color;
import javax.swing.JFrame;

public class main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        project obj1 = new project();
        obj.setBounds(15, 15, 915, 700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(obj1);

    }

}
