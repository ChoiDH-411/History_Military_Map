package com.hmmap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hmmap.util.LangUtil;

/**
 * Servlet implementation class LangController
 */
@WebServlet("/lang")
public class LangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LangController() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // URL의 ?code=ja 에서 값 꺼내기
        String code = request.getParameter("code");

        // 허용되지 않은 값이면 기본 언어로
        if (!LangUtil.isSupported(code)) {
            code = LangUtil.DEFAULT_LANG;
        }

        // 세션에 "lang"이라는 이름으로 code 저장
        request.getSession().setAttribute("lang", code);

        // 보던 페이지로 돌아가기
        response.sendRedirect(getBackUrl(request));
    }

    // 돌아갈 주소: 직전 페이지(Referer)가 우리 사이트면 거기로, 아니면 메인으로
    private String getBackUrl(HttpServletRequest request) {
        String home = request.getContextPath() + "/";
        String referer = request.getHeader("Referer");
        if (referer == null) {
            return home;
        }
        try {
            String host = java.net.URI.create(referer).getHost();
            if (request.getServerName().equals(host)) {
                return referer;
            }
        } catch (IllegalArgumentException e) {
            // 주소 형식이 이상하면 메인으로
        }
        return home;
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
