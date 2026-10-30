package com.hmmap.controller;

import java.io.IOException;

import com.hmmap.util.LangUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            String lang = LangUtil.getLang(request);            // 지금 언어를 기억해 두고
            session.invalidate();                               // 세션 전체 삭제 (로그인 정보 포함)
            request.getSession().setAttribute("lang", lang);    // 새 세션에 언어만 다시 저장
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}