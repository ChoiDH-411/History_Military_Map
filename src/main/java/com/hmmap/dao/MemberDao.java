package com.hmmap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.hmmap.dto.Member;
import com.hmmap.dto.Place;
import com.hmmap.util.DBUtil;

public class MemberDao {

    // 회원 가입 (password에는 이미 해시된 값이 들어 있어야 함)
    public void insert(Member member) {
        String sql = """
                INSERT INTO member (login_id, password, nickname, lang)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // ? 4개에 member의 값 넣기
        	pstmt.setString(1, member.getLoginId());
        	pstmt.setString(2, member.getPassword());
        	pstmt.setString(3, member.getNickname());
        	pstmt.setString(4, member.getLang());

            pstmt.executeUpdate();   // INSERT/UPDATE/DELETE는 executeUpdate

        } catch (SQLException e) {
            throw new RuntimeException("회원 가입 실패", e);
        }
    }

    // 아이디로 회원 1명 조회 (없으면 null)
    public Member findByLoginId(String loginId) {
        // ✍️ (나) PlaceDao.findById를 참고해서 작성
        //   - SQL: member 테이블의 7개 컬럼, WHERE login_id = ?
        //   - ? 1개
        //   - if (rs.next())로 Member에 7개 담아서 return, 없으면 null
        //   - created_at은: rs.getObject("created_at", LocalDateTime.class)
		String sql = """
				SELECT m.member_id, m.login_id, m.password, m.nickname, m.lang, m.role, m.created_at
				FROM member m
				WHERE login_id = ?
                """;
		
		try (Connection conn = DBUtil.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setString(1, loginId);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    Member member = new Member();
	                    member.setMemberId(rs.getInt("member_id"));
	                    member.setLoginId(rs.getString("login_id"));
	                    member.setPassword(rs.getString("password"));
	                    member.setNickname(rs.getString("nickname"));
	                    member.setLang(rs.getString("lang"));
	                    member.setRole(rs.getString("role"));
	                    member.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
	                    return member;
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("회원 조회 실패", e);
	        }

        return null;
    }
}