package com.hmmap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hmmap.dao.PlaceDao;
import com.hmmap.dto.Place;
import com.hmmap.util.LangUtil;

/**
 * Servlet implementation class PlaceDetailController
 */
@WebServlet("/place/detail")
public class PlaceDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceDetailController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. URL의 ?id=1 에서 "1"을 꺼내 숫자로 바꾸기
		String idParam = request.getParameter("id");
		int placeId;
		try {
			placeId = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
        	// id가 없거나(?id 생략) 숫자가 아님(?id=abc) → 404
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
		
		// 2. Dao 장소 조회
		PlaceDao placeDao = new PlaceDao();
		Place place = placeDao.findById(placeId, LangUtil.getLang(request));

		// 3. 없는 번호이거나 숨김 장소 → 404
		if (place == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 4. JSP에서 꺼낼 수 있게 저장
		request.setAttribute("place", place);

		request.getRequestDispatcher("/WEB-INF/views/place/detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
