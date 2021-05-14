package GUI;

import GUI.tool.header;

import GUI.tool.item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DTO.SanPhamDTO;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

public class qlst extends JFrame implements MouseListener {

	private JPanel contentPane;
	private String username;
	private String role;
	private int DEFAULT_HEIGHT = 730,DEFAULT_WIDTH = 1500 ;
	private JPanel main,menubar;
	private ArrayList<String> menu = new ArrayList<>();
	private ArrayList<item> option = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					qlst frame = new qlst("NV002","US");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
					
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public qlst() {
		Toolkit screen = Toolkit.getDefaultToolkit();
        init();
	}
    public qlst(String username,String role) {
    	this.username=username;
    	this.role=role;
    	Toolkit screen = Toolkit.getDefaultToolkit();
        init();
    }
    public void init() {
    	Font font = new Font("Segoe UI",Font.BOLD,14);
    	setTitle("Quản Lý siêu thị ");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, 30, 30));
        
        /*****HEADER*****/
        
        header hmain = new header(DEFAULT_WIDTH,40);
        hmain.setBackground(new Color(27, 27, 30));
        if(username != null)
        {	
        	JLabel user = new JLabel("Chào, "+username);
            if(role.equals("AD")) {
            user = new JLabel("Chào, "+"ADMIN");
            }
            user.setFont(font);
            user.setForeground(Color.WHITE);
            user.setBounds(new Rectangle(DEFAULT_WIDTH-350,-7,150,50));
            hmain.add(user);
            
    }
        item btnLogOut = new item("Đăng xuất", new Rectangle(DEFAULT_WIDTH, -8, 120, 50),null,null,null, new Color(80, 80, 80));
        btnLogOut.setLocation(DEFAULT_WIDTH-220, 0);
        GroupLayout gl_btnLogOut = new GroupLayout(btnLogOut);
        gl_btnLogOut.setHorizontalGroup(
        	gl_btnLogOut.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 50, Short.MAX_VALUE)
        );
        gl_btnLogOut.setVerticalGroup(
        	gl_btnLogOut.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 50, Short.MAX_VALUE)
        );
        btnLogOut.setLayout(gl_btnLogOut);
        hmain.add(btnLogOut.isButton());
        btnLogOut.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e)
           {
              new loginGUI().setVisible(true);
              dispose();
           }
        });
        item minimize = new item("", new Rectangle(DEFAULT_WIDTH-100, -8, 50, 50),"minimize.png","minimize.png","minimize.png",new Color(80,80,80));
        minimize.setLocation(DEFAULT_WIDTH-100, 0);
        minimize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
               setState(Frame.ICONIFIED);
            }
         });
        hmain.add(minimize);
        item btnexit=new item("", new Rectangle(DEFAULT_WIDTH-50, -8, 50, 50), "exita.png", "exita.png", "exita.png", new Color(240, 71, 74));
        btnexit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
               System.exit(0);
            }	
         });
        hmain.add(btnexit.isButton());	
        /*****menu*****/
        menubar=new JPanel(null);
        menubar.setBackground(new Color(55, 63, 81));
        menubar.setPreferredSize(new Dimension(DEFAULT_WIDTH,100));
        menu.add("Bán hàng");
        menu.add("Nhập Hàng");
        menu.add("Sản Phẩm");
        menu.add("Khách Hàng");
        menu.add("hóa đơn");
        //if(role.equals("AD") ) {
        menu.add("nhân viên");
        menu.add("tài khoản");
        menu.add("Thống kê");
        //}
        out();
        main = new JPanel(null);
        main.setBackground(Color.WHITE);
        changemainform(0);
        add(hmain);
        add(menubar,BorderLayout.NORTH);
        add(main,BorderLayout.CENTER);
    }
    public static void main1(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
            
        }
        qlst ql = new qlst();
        
    }
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        for(int i  = 0 ; i<option.size();i++)
        {
            item item = option.get(i);
            if(e.getSource()== item)
            {
                item.doActive();
                changemainform(i);
            }
            else
            {
                item.noActive();
            }
        }
    }
    public void changemainform(int c) {
    		
    	switch(c) {
    	
    	case 1:{
    		main.removeAll();
            main.add(new NhapHangGUI());
            main.repaint();
            main.revalidate();
            break;
    	}
    	case 2:{
    		main.removeAll();
            main.add(new SanPhamGUI());
            main.repaint();
            main.revalidate();
            break;
    	}
    	case 3:{
    		main.removeAll();
            main.add(new KhachHangGUI());
            main.repaint();
            main.revalidate();
            break;
    	}
    	case 4:{
    			main.removeAll();
                main.add(new HoaDonGUI());
                main.repaint();
                main.revalidate();
                break;
    	}
    	case 5:{
    		main.removeAll();
            main.add(new NhanVienGUI());
            main.repaint();
            main.revalidate();
            break;
    	}
    	case 6:{
    		main.removeAll();
            main.add(new TaiKhoanGUI(this.username));
            main.repaint();
            main.revalidate();
            break;
    	}
    	case 7:{
			main.removeAll();
            main.add(new ThongKeGUI());
            main.repaint();
            main.revalidate();
            break;
	}
    	
    	default:{
    		main.removeAll();
            main.add(new BanHangGUI(this.username));
            main.repaint();
            main.revalidate();
            break;
    	}
    	}
    }
    
    
    public void out()
    {
        
        option.clear();
        if(role.equals("AD")) {
        for(int i = 0 ; i < menu.size() ; i++)
        {
           String s = menu.get(i);
           option.add(new item(s, new Rectangle(DEFAULT_WIDTH/menu.size()*i,40,150,50),null,null));
           option.get(i).addMouseListener(this);
        }
        
        
        menubar.removeAll();
        for(item n : option)
        {
            menubar.add(n); 
        }
        }
        else {
        	for(int i = 0 ; i < menu.size() ; i++)
            {
               String s = menu.get(i);
               option.add(new item(s, new Rectangle(DEFAULT_WIDTH/menu.size()*i,40,150,50),null,null));
               option.get(i).addMouseListener(this);
            }
            
            
            menubar.removeAll();
            for(item n : option)
            {
                menubar.add(n); 
            }
        }
        repaint();
        revalidate();
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}