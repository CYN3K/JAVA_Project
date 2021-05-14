package GUI;

import BUS.*;

import DTO.*;
import DAO.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class TaiKhoanGUI extends JPanel {
	JComboBox quyen;
	TaiKhoanBUS tkbus = new TaiKhoanBUS();
	String usernamend;
	int idend;
	ArrayList<TaiKhoanDTO> tk;
	String[] header = new String [] {
            "username", "password", "QUYỀN"
        };
	DefaultTableModel model = new DefaultTableModel(header,0);
    private NhanVienBUS nvBUS = new NhanVienBUS();
    ArrayList<NhanVienDTO> nv;
    String username;
    String manvend;
	/**
	 * Create the panel.
	 */
		public TaiKhoanGUI(String username) {
			this.username=username;
			System.out.print(this.username);
			initComponents();
			tableNV.setModel(model);
		     listNV();
		     getusernamend();
		     getmanvend();
		}
		public void listNV() {
	        if (nvBUS.getList() == null) {
	            nvBUS.list();
	        }
	        nv = nvBUS.getList();
	        model.setRowCount(0);
	        if(tkbus.getList()==null) {
	        	tkbus.list();
	        }
	        tk=tkbus.getList();
	        loadNhanVien(tk);
	       
	        
	    }
		public void getusernamend() {
			String user = null;
			int id = 0;
			for(TaiKhoanDTO s:tk) {
				user=s.getUsername();
				id=s.getId();
			}
			id++;
			idend=id;
			String[] split= new String [3];
			split=user.split("0");
			usernamend="NV00"+String.valueOf(Integer.parseInt(split[2])+1);
		}
		public void getmanvend() {
	    	for(NhanVienDTO s : nv) {
	    		manvend=s.getMaNV();
	    	}
	    	//System.out.print(manvend);
	    }
		public String getData(int row, int colum) {
	        return String.valueOf(tableNV.getValueAt(row, colum));
	    }
		public static String getMd5(String input) 
	    { 
	        try { 
	            // Static getInstance method is called with hashing MD5 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	  
	            // digest() method is called to calculate message digest 
	            //  of an input digest() return array of byte 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            // Convert byte array into signum representation 
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            // Convert message digest into hex value 
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	            return hashtext; 
	        }  
	        // For specifying wrong message digest algorithms 
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	    }
		public void loadNhanVien(ArrayList<TaiKhoanDTO> tk) {
	        Vector data;
	        model.setRowCount(0);
	        for (TaiKhoanDTO s : tk) {
	        	if(s.getEnable()==1) {
	            data = new Vector();
	            data.add(s.getUsername());
	            String pass = getMd5(s.getPass());
	            data.add(pass);
	            data.add(s.getQuyen());
	            model.addRow(data);
	        	}
	        }
	        tableNV.setModel(model);
	    }
		private void initComponents() {
	    	this.setBounds(0, 0,1500,650);
	        jScrollPane1 = new javax.swing.JScrollPane();
	        tableNV = new javax.swing.JTable();
	        tfusername = new javax.swing.JTextField();
	        tfpassword = new javax.swing.JTextField();
	        btnthem = new javax.swing.JButton();
	        btnsua = new javax.swing.JButton();
	        btnxoa = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        btnreset = new javax.swing.JButton();
	        tfusername.setEditable(false);
	        setPreferredSize(new java.awt.Dimension(1500, 650));
	        tableNV.setDefaultEditor(Object.class, null);
	        tableNV.setAutoCreateRowSorter(true);
	        tableNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        tableNV.setPreferredSize(new java.awt.Dimension(1000, 300));
	        tableNV.setRowHeight(25);
	        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	tableNVMouseClicked(evt);
	            }
	        });
	        jScrollPane1.setViewportView(tableNV);

	        tfusername.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                actionPerformed(evt);
	            }
	        });

	        btnthem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        btnthem.setText("Thêm");
	        btnthem.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 btnthemActionPerformed(evt);
	            }

				
	        });
	        
	        btnsua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        btnsua.setText("Sửa");
	        btnsua.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	btnsuaActionPerformed(evt);
	            }

	        });

	        btnxoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        btnxoa.setText("Xóa");
	        btnxoa.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 btnxoaActionPerformed(evt);
	            }

				
	        });

	        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        jLabel1.setText("Username\r\n");

	        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        jLabel2.setText("Quyền\r\n");

	        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        jLabel4.setText("Password");

	        btnreset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	        btnreset.setText("Reset");
	        btnreset.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	btnresetActionPerformed();
	            }

				
	        });
	        
	        String[] quyens = new String[] {
		    		   "NV","AD"
		       };
	       quyen = new JComboBox(quyens);
	       
	        GroupLayout groupLayout = new GroupLayout(this);
	        groupLayout.setHorizontalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(10)
	        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addGap(10)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(btnthem, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnsua, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnxoa, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnreset, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
	        			.addGap(610))
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(130)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(jLabel1)
	        				.addComponent(jLabel2))
	        			.addGap(60)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(groupLayout.createSequentialGroup()
	        					.addComponent(tfusername, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	        					.addGap(157)
	        					.addComponent(jLabel4)
	        					.addGap(55)
	        					.addComponent(tfpassword, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
	        				.addComponent(quyen, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
	        );
	        groupLayout.setVerticalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addGap(41)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(groupLayout.createSequentialGroup()
	        					.addGap(3)
	        					.addComponent(jLabel1))
	        				.addComponent(tfusername, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        				.addGroup(groupLayout.createSequentialGroup()
	        					.addGap(3)
	        					.addComponent(jLabel4))
	        				.addComponent(tfpassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
	        			.addGap(34)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(jLabel2)
	        				.addComponent(quyen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addGap(36)
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(groupLayout.createSequentialGroup()
	        					.addGap(3)
	        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(groupLayout.createSequentialGroup()
	        					.addComponent(btnthem, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        					.addGap(21)
	        					.addComponent(btnsua, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        					.addGap(18)
	        					.addComponent(btnxoa, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        					.addGap(27)
	        					.addComponent(btnreset, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(27))
	        );
	        setLayout(groupLayout);
	    }// </editor-fold>//GEN-END:initComponents
		private void btnresetActionPerformed() {
			// TODO Auto-generated method stub
			tfusername.setText("");
			tfpassword.setText("");
			quyen.setSelectedIndex(0);
	        listNV();
			
		}
		private void btnsuaActionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			String user=tfusername.getText();
			String pass=tfpassword.getText();
			int c= quyen.getSelectedIndex();
			String quyenu = null;
			if(c==0)quyenu="NV";
			else if(c==1)quyenu="AD";
			for(TaiKhoanDTO s : tk) {
				if(s.getUsername().equals(user)) {
					if(!quyenu.equals("AD") && user.equals(this.username)) {
						JOptionPane.showMessageDialog(null,"chỉ có thể đổi quyền admin bằng một tài khoản admin khác");
						break;
					}
					String test = getMd5(s.getPass());
					if(test.equals(pass)) {
						TaiKhoanDTO uset = new TaiKhoanDTO(s.getId(),user,s.getPass(),quyenu,1);
						tkbus.set(uset);
					}
					else {
						TaiKhoanDTO uset = new TaiKhoanDTO(s.getId(),user,pass,quyenu,1);
						tkbus.set(uset);
					}
					
					tkbus.list();
					listNV();
					break;
				}
			}
			
		}
		private void btnxoaActionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			String user = tfusername.getText();
			if(user.equals(this.username)) {
				JOptionPane.showMessageDialog(null,"chỉ có thể xóa admin bằng một tài khoản admin khác");
			}
			else {
			tkbus.delete(user);
			listNV();
			}
		}
		private void btnthemActionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			if(usernamend.equals(manvend)) {
			if(usernamend.equals(tfusername.getText())) {
				String user=tfusername.getText();
				String pass=tfpassword.getText();
				int c=quyen.getSelectedIndex();
				String quyenu;
				if(c==0)quyenu="NV";
				else quyenu="AD";
				if(!user.equals(null) && !pass.equals(null)) {
				TaiKhoanDTO tknew = new TaiKhoanDTO(idend,user,pass,quyenu,1);
				try {
					tkbus.add(tknew);
					tkbus.list();
					listNV();
					getusernamend();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else JOptionPane.showMessageDialog(null,"xin nhập đầy đủ thông tin");
			}
			else {
				tfusername.setText(usernamend);
				tfpassword.setText("");
				quyen.setSelectedIndex(0);
			}
			}
			else JOptionPane.showMessageDialog(null,"cần tạo nhân viên mới trước khi tạo một tài khoản mới");
		}
		private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
	        int row = tableNV.getSelectedRow();
	        tfusername.setText(getData(row, 0));
	        tfpassword.setText(getData(row, 1));
	        if( getData(row,2).equals("AD")) {
	        	quyen.setSelectedIndex(1);
	        }
	        else quyen.setSelectedIndex(0);
	   
	    }//GEN-LAST:event_tableNVMouseClicked
		 
		private javax.swing.JButton btnreset;
		    private javax.swing.JButton btnsua;
		    private javax.swing.JButton btnthem;
		    private javax.swing.JButton btnxoa;
		    private javax.swing.JLabel jLabel1;
		    private javax.swing.JLabel jLabel2;
		    private javax.swing.JLabel jLabel4;
		    private javax.swing.JScrollPane jScrollPane1;
		    private javax.swing.JTable tableNV;
		    private javax.swing.JTextField tfpassword;
		    private javax.swing.JTextField tfusername;
}
