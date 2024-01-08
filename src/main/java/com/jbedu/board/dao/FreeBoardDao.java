package com.jbedu.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FreeBoardDao {

	
	DataSource dataSource;

	public FreeBoardDao() {
		super();
		// TODO Auto-generated constructor stub
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracleDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void write(String fbtitle, String fbname, String fbcontent) {
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO freeboard(fbnum, fbtitle, fbcontent, fbhit) VALUES (freeboard_seq.nextval, ?, ?, ?, 0)";	
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fbtitle);
			pstmt.setString(2, fbname);
			pstmt.setString(3, fbcontent);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					conn.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
