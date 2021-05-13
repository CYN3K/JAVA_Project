package GUI;
import GUI.mainFrame;
import DTO.TaiKhoanDTO;
import BUS.TaiKhoanBUS;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class loginGUI extends JFrame{
    private JTextField user;
    private JPasswordField pass;
    private int DEFAULT_HEIGHT = 600 ,DEFAULT_WIDTH = 400;
    private JButton btnLogin;
    public loginGUI()
    {
        init();
    }
    public void init()
    {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel background = new JPanel();
        background.setBackground(new Color(55, 63, 81));
        background.setLayout(null);
        background.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        
        
        JLabel exit = new JLabel(new ImageIcon("/image/Lock.png"),JLabel.CENTER);
        exit.setBounds(DEFAULT_WIDTH - 40, 10, 30, 30);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel lbUser = new JLabel(new ImageIcon(loginGUI.class.getResource("/image/Male.png")),JLabel.CENTER);
        lbUser.setBounds(80, 197, 30, 30);
        JLabel lbPass = new JLabel(new ImageIcon(loginGUI.class.getResource("/image/Lock.png")),JLabel.CENTER);
        lbPass.setBounds(80, 277, 30, 30);
        
        user = new JTextField("Username");
        user.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
        			String username = user.getText();
            		char[] password = pass.getPassword();
            	
            		TaiKhoanBUS uBUS=new TaiKhoanBUS();
            		uBUS.list();
            		String role="";
            		role=uBUS.check(username, password);
            		if(role!="") {
            			JOptionPane.showMessageDialog(null, "đăng nhập thành công");
            			
            			dispose();
            			new qlst(username,role).setVisible(true);
            	    }
            		else JOptionPane.showMessageDialog(null, "đăng nhập thất bại");
            	}
        	}
        });
        user.setForeground(Color.WHITE);
        user.setCaretColor(Color.WHITE);
        user.setBounds(120,200,180,30);
        user.setBorder(null);
        user.setOpaque(false);
        pass = new JPasswordField("Password");
        pass.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
        			String username = user.getText();
            		char[] password = pass.getPassword();
            	
            		TaiKhoanBUS uBUS=new TaiKhoanBUS();
            		uBUS.list();
            		String role="";
            		role=uBUS.check(username, password);
            		if(role!="") {
            			JOptionPane.showMessageDialog(null, "đăng nhập thành công");
            			
            			dispose();
            			new qlst(username,role).setVisible(true);
            	    }
            		else JOptionPane.showMessageDialog(null, "đăng nhập thất bại");
            	}
        	}
        });
        
        pass.setForeground(Color.WHITE);
        pass.setCaretColor(Color.WHITE);
        pass.setBorder(null);
        pass.setBounds(120,280,150,30);
        pass.setOpaque(false);    
        
        JSeparator sp1 = new JSeparator();
        sp1.setBounds(80,230,220,10);
        
        JSeparator sp2 = new JSeparator();
        sp2.setBounds(80,310,220,10);

        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, 30, 30));
        background.add(exit);
        background.add(lbUser);
        background.add(user);
        background.add(sp1);
        background.add(lbPass);
        background.add(pass);
        background.add(sp2);
        
        JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(87, 32, 213, 73);
        background.add(lblNewLabel);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(background, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(background, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        JButton btnNewButton = new JButton("ĐĂNG NHẬP");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton.setIcon(null);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String username = user.getText();
        		char[] password = pass.getPassword();
        	
        		TaiKhoanBUS uBUS=new TaiKhoanBUS();
        		uBUS.list();
        		String role="";
        		role=uBUS.check(username, password);
        		if(role!="") {
        			JOptionPane.showMessageDialog(null, "đăng nhập thành công");
        			
        			dispose();
        			new qlst(username,role).setVisible(true);
        	    }
        		else JOptionPane.showMessageDialog(null, "đăng nhập thất bại");
        	}
        });
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(52, 387, 119, 40);
        background.add(btnNewButton);
        
        JButton btnLmMi = new JButton("LÀM MỚI");
        btnLmMi.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLmMi.setIcon(null);
        btnLmMi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		user.setText("");
        		pass.setText("");
        	}
        });
        btnLmMi.setBackground(Color.WHITE);
        btnLmMi.setForeground(Color.BLACK);
        btnLmMi.setBounds(221, 387, 119, 40);
        background.add(btnLmMi);
        
        JCheckBox show = new JCheckBox("HIỂN THỊ PASSWORD");
        show.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(show.isSelected()) {
        			pass.setEchoChar((char)0);
        		}
        		else pass.setEchoChar('*');
        	}
        	
        });
        show.setBounds(126, 342, 145, 30);
        background.add(show);
        
        JButton btnThot = new JButton("Thoát");
        btnThot.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnThot.setIcon(new ImageIcon(loginGUI.class.getResource("/image/Close.png")));
        btnThot.setBackground(Color.WHITE);
        btnThot.setBounds(148, 438, 103, 40);
        background.add(btnThot);
        getContentPane().setLayout(groupLayout);
        
        
        setSize(DEFAULT_WIDTH ,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
        
    
    public static void main(String[]args)
    {
        new loginGUI().setVisible(true);
    }
}