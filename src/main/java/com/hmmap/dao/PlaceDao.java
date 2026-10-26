package com.hmmap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hmmap.dto.Place;
import com.hmmap.util.DBUtil;

public class PlaceDao {
	// 언어별 장소 목록 (숨김 제외)
    // 언어별 장소 목록 (숨김 제외) → 메인 화면용
    public List<Place> findAll(String lang) {
        String sql = """
                SELECT p.place_id, p.country_code, p.category,
                       p.latitude, p.longitude, p.image_file, i.name
                FROM place p
                JOIN place_i18n i ON p.place_id = i.place_id
                WHERE i.lang = ?
                  AND p.is_active = 1
                ORDER BY p.place_id
                """;

        List<Place> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lang);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Place place = new Place();
                    place.setPlaceId(rs.getInt("place_id"));
                    place.setCountryCode(rs.getString("country_code"));
                    place.setCategory(rs.getString("category"));
                    place.setLatitude(rs.getDouble("latitude"));
                    place.setLongitude(rs.getDouble("longitude"));
                    place.setImageFile(rs.getString("image_file"));
                    place.setName(rs.getString("name"));
                    list.add(place);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("장소 목록 조회 실패", e);
        }
        return list;
    }
	
	public Place findById(int placeId, String lang) {
		String sql = """
				SELECT p.place_id, p.country_code, p.category,
					p.latitude, p.longitude, p.image_file, i.name, i.address, i.description
				FROM place p
				JOIN place_i18n i ON p.place_id = i.place_id
				WHERE p.place_id = ?
					AND i.lang = ?
					AND p.is_active = 1
				ORDER BY p.place_id
                """;

        try (Connection conn = DBUtil.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1, placeId);
        	pstmt.setString(2, lang);     // i.lang = ?에 매개변수 lang 값을 넣음
        	

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	Place place = new Place();
                    place.setPlaceId(rs.getInt("place_id")); 
                    place.setCountryCode(rs.getString("country_code"));
                    place.setCategory(rs.getString("category"));
                    place.setLatitude(rs.getDouble("latitude"));
                    place.setLongitude(rs.getDouble("longitude"));
                    place.setImageFile(rs.getString("image_file"));
                    place.setName(rs.getString("name"));
                    place.setAddress(rs.getString("address"));
                    place.setDescription(rs.getString("description"));
                    return place;
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("장소 목록 조회 실패", e);
        }
        return null;
    }
}