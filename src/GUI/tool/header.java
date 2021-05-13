package GUI.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class header extends JPanel{
    private int height,width;
    private JFrame frame;
    public header(int w,int h)
    {
        width = w;
        height = h;
        init();
    }
    public void init()
    {
        setLayout(null);
        setSize(width,height);
        setBackground(null);
        
        
  
        Font font = new Font("Segoe UI",Font.BOLD,15);
        JLabel name = new JLabel("QUẢN LÝ SIÊU THỊ",JLabel.CENTER);
        name.setFont(font);
        name.setForeground(Color.white);
        name.setBounds(new Rectangle(width/2-90, 0, 150, 40));
        add(name);
    }
}