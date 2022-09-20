import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class BookShop {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table_1;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShop window = new BookShop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookShop() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
     public void Connect() {
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
    		 con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop","root","");
    	 }
    	 catch(ClassNotFoundException ex) {
    		 
    	 }
    	 catch(SQLException ex) {}
     }
     
     public void table_load() {
    	 try {
    		 pst = con.prepareStatement("select * from booktable");
    		 rs = pst.executeQuery();
    		 table_1.setModel(DbUtils.resultSetToTableModel(rs));
    		 
    	 }
    	 catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 824, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(314, 11, 171, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 64)));
		panel.setBounds(10, 59, 370, 222);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(30, 31, 109, 45);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setBounds(30, 87, 109, 45);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setBounds(30, 152, 109, 45);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(142, 48, 194, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setBounds(142, 104, 194, 20);
		txtedition.setColumns(10);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(142, 169, 194, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,edition,price;
				name = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				try {
					pst = con.prepareStatement("insert into booktable(name,edition,price)values(?,?,?)");
					pst.setString(1, name);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					          
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
			}}
			});
		btnNewButton.setBounds(31, 324, 99, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(140, 324, 99, 37);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
			}
		});
		btnClear.setBounds(260, 324, 99, 37);
		frame.getContentPane().add(btnClear);
		
		table_1 = new JTable();
		table_1.setBounds(390, 65, 418, 275);
		frame.getContentPane().add(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(31, 388, 344, 66);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Book Id");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(44, 11, 98, 29);
		panel_1.add(lblNewLabel_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = txtbid.getText();
		 
		                pst = con.prepareStatement("select name,edition,price from booktable where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		                
		                
		            }  
		            else
		            {
		             txtbname.setText("");
		             txtedition.setText("");
		                txtprice.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
		}	
		});
		txtbid.setBounds(152, 20, 182, 20);
		panel_1.add(txtbid);
		txtbid.setColumns(10);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,edition,price,bid;
				name = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
				try {
					pst = con.prepareStatement("update booktable set name=?,edition=?,price=? where id = ?");
					pst.setString(1, name);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4,bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!!");
					table_load();
					          
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
			}
				
				
			}
		});
		btnUpdate.setBounds(449, 400, 99, 37);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				bid = txtbid.getText();
				try {
					pst = con.prepareStatement("delete from booktable where id = ?");
					
					pst.setString(1,bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!!");
					table_load();
					          
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
			}
				
				
			}
		});
		btnDelete.setBounds(620, 400, 99, 37);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClear_1 = new JButton("CLEAR");
		btnClear_1.setBounds(407, 99, 99, 37);
		frame.getContentPane().add(btnClear_1);
	}
}
