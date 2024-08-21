package com.sopra.cls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
	Connection conn=null;
	public DbHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/radhadb", "root", "admin");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Persons> retList() throws SQLException{
		List<Persons> lp=new ArrayList<Persons>();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from persons");
		while(rs.next()) {
			Persons pp=new Persons();
			pp.setPid(rs.getInt(1));
			pp.setPname(rs.getString(2));
			pp.setPemail(rs.getString(3));
			lp.add(pp);
		}
		return lp;
	}
	
	public String insRec(Persons p) {
		String status="not done";
		String query="insert into persons values("+p.getPid()+",'"+p.getPname()+"','"+p.getPemail()+"')";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.execute();
			status="done";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public String upsRec(Persons p) {
		String status="not done";
		String query="update persons set pname='"+p.getPname()+"',pemail='"+p.getPemail()+"' where pid="+p.getPid();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			status="done";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public String delRec(int i) {
		String status="not done";
		String query="delete from persons where pid="+i;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			status="done";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
