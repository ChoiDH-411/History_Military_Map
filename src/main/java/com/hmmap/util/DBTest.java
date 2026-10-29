package com.hmmap.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;

import com.hmmap.dao.MemberDao;
import com.hmmap.dao.PlaceDao;
import com.hmmap.dto.Member;
import com.hmmap.dto.Place;

public class DBTest {
	
	public static void main(String[] args) {
	    MemberDao dao = new MemberDao();

	    // 1. 가입
	    Member m = new Member();
	    m.setLoginId("test01");
	    m.setPassword(PasswordUtil.hash("1234"));   // 해시해서 저장
	    m.setNickname("테스터");
	    m.setLang("ko");
	    dao.insert(m);

	    // 2. 조회
	    Member found = dao.findByLoginId("test01");
	    System.out.println(found);

	    // 3. 로그인 비교 흉내
	    System.out.println("1234 맞음? " + found.getPassword().equals(PasswordUtil.hash("1234")));
	    System.out.println("9999 맞음? " + found.getPassword().equals(PasswordUtil.hash("9999")));
	}
	
	/*
	public static void main(String[] args) {
		PlaceDao dao = new PlaceDao();
		for (Place p : dao.findAll("ko")) {
		    System.out.println(p);   // toString() 결과 출력
		}
	}
	*/
	
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
