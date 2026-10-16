package com.hmmap.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private	static final Properties props = new Properties();
	
	 static {
	        // db.properties를 찾아서 읽음
	        try (InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
	            if (in == null) {
	                throw new IllegalStateException("db.properties 파일을 찾을 수 없습니다.");
	            }
	            props.load(in);
	            // 드라이버 클래스를 메모리에 올림
	            Class.forName(props.getProperty("db.driver"));
	        } catch (IOException | ClassNotFoundException e) {
	            throw new IllegalStateException("DB 설정 로딩 실패", e);
	        }
	 }
	 	// 객체 생성을 막음 (static 메서드만 쓰는 유틸 클래스)
	    private DBUtil() {}

	    // 호출할 때마다 새 Connection을 만들어 돌려줌
	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(
	                props.getProperty("db.url"),
	                props.getProperty("db.user"),
	                props.getProperty("db.password"));
	    }
}


