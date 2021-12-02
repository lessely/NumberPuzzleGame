package Games;
import java.sql.*;

import javax.swing.JOptionPane;

 class database {
	private Connection connection=null;
	private Statement stmt = null;
	private PreparedStatement pstmt=null;
	
	public void Connection() {
		String drivername= "com.mysql.jdbc.Driver";
		String urlString="jdbc:mysql://localhost:3306/computer_dept?characterEncoding=utf-8";
		String usernameString="javase";
		String passwordString="javase";
	try {
		Class.forName(drivername);
		connection=DriverManager.getConnection(urlString,usernameString,passwordString);
		stmt = connection.createStatement();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public boolean query(String num, String password) {// ��ָ֤�����û��˺�����
		boolean result = false;
		try {
			String sql = "select * from users where num=? and password=?";
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = false;
			} else {
				result = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean query(String num) {// ��ѯָ�����û��Ƿ����
		boolean result = false;
		try {
			String sql = "select * from users where num=?";
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = false;
			} else {
				result = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insert(String num, String name, String password, String repassword) {// д���ֻ������email
		boolean result = false;
		PreparedStatement stmt=null;
		int a=0;	
		boolean p=query(num);//�ж��Ƿ��Ѿ�ע��
		boolean pa=password.equals(repassword);//�ж����������Ƿ�һ��

		if(p==true) {
			System.out.println("�û��ѱ�ע��");
			JOptionPane.showMessageDialog(null, "�û��ѱ�ע��","��ʾ",JOptionPane.ERROR_MESSAGE);	
		}
		if(pa==false) {
			System.out.println("�������벻һ��");
			JOptionPane.showMessageDialog(null, "�������벻һ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);	
		}
		if(num!=null&&p!=true&&pa==true) {
		try {
			String insert_sql="Insert into users set num=?,name=?,password=?";
			//�뽫���벹������
			stmt=connection.prepareStatement(insert_sql);
			stmt.setString(1,num);
			stmt.setString(2,name);
			stmt.setString(3,password);

			a=stmt.executeUpdate();
			if(a>0) result=true;
			else {
				result=false;
				System.out.println(insert_sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}}
		return result;
	}
	
	
	public boolean update(String num, String grade) {// д���ֻ������email
		boolean result = false;
		PreparedStatement stmt=null;
		int a=0;
		try {
			String update_sql="Update users set grade=? where num=?";
			//�뽫���벹������
			stmt=connection.prepareStatement(update_sql);
			stmt.setString(1,grade);
			stmt.setString(2,num);

			a=stmt.executeUpdate();
			if(a>0) result=true;
			else {
				result=false;
				System.out.println(update_sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	}

	