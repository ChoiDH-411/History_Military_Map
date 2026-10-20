package com.hmmap.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;

import com.hmmap.dao.PlaceDao;
import com.hmmap.dto.Place;

public class DBTest {
	public static void main(String[] args) {
		PlaceDao dao = new PlaceDao();
		for (Place p : dao.findAll("ko")) {
		    System.out.println(p);   // toString() 결과 출력
		}
	}
	
	/*
	public static void main(String[] args) {
	
		String sql = "SELECT COUNT(*) FROM place";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        if (rs.next()) {
	            System.out.println("장소 수: " + rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	}
	*/
}
