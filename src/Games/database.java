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

	public boolean query(String num, String password) {// 验证指定的用户账号密码
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
	
	public boolean query(String num) {// 查询指定的用户是否存在
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
	
	public boolean insert(String num, String name, String password, String repassword) {// 写入手机号码和email
		boolean result = false;
		PreparedStatement stmt=null;
		int a=0;	
		boolean p=query(num);//判断是否已经注册
		boolean pa=password.equals(repassword);//判断两次密码是否一致

		if(p==true) {
			System.out.println("用户已被注册");
			JOptionPane.showMessageDialog(null, "用户已被注册","提示",JOptionPane.ERROR_MESSAGE);	
		}
		if(pa==false) {
			System.out.println("两次密码不一致");
			JOptionPane.showMessageDialog(null, "两次密码不一致","提示",JOptionPane.INFORMATION_MESSAGE);	
		}
		if(num!=null&&p!=true&&pa==true) {
		try {
			String insert_sql="Insert into users set num=?,name=?,password=?";
			//请将代码补充完整
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
	
	
	public boolean update(String num, String grade) {// 写入手机号码和email
		boolean result = false;
		PreparedStatement stmt=null;
		int a=0;
		try {
			String update_sql="Update users set grade=? where num=?";
			//请将代码补充完整
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

	