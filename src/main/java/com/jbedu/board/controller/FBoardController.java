package com.jbedu.board.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.naming.InitialContext;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FBoardController {
	
	DataSource dataSource;
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value = "write")
	public String write(HttpServletRequest request) {
		String fbtitle = request.getParameter("fbtitle");
		String fbname = request.getParameter("fbname");
		String fbcontent = request.getParameter("fbcontent");
		
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracleDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		return "write_form";
	}

}
