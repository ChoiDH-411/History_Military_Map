package com.hmmap.controller;

import java.io.IOException;

import com.hmmap.dao.MemberDao;
import com.hmmap.dto.Member;
import com.hmmap.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/member/login.jsp";
    private final MemberDao memberDao = new MemberDao();

    // 로그인 폼
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    // 로그인 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loginId  = request.getParameter("loginId");
        String password = request.getParameter("password");
        loginId  = (loginId == null) ? "" : loginId.trim();
        password = (password == null) ? "" : password;

        // 아이디로 회원 조회
        Member member = memberDao.findByLoginId(loginId);
        
        // 회원이 없거나, 비밀번호 해시가 다르면 → 실패
        if (member == null || !member.getPassword().equals(PasswordUtil.hash(password))) { 
        	request.setAttribute("error", "error.login.failed");
            request.setAttribute("loginId", loginId);
            request.getRequestDispatcher(VIEW).forward(request, response);
            return;
        }

        // 로그인 성공
        request.changeSessionId(); // 보안: 로그인 순간 세션 번호를 새로 발급
        member.setPassword(null);  // 세션에는 비밀번호 해시를 넣지 않음

        // 세션에 저장 2개
        //   - "loginMember" ← member
        request.getSession().setAttribute("loginMember", member);    // 로그인 정보
        request.getSession().setAttribute("lang", member.getLang()); // 선호 언어 적용
        
        response.sendRedirect(request.getContextPath() + "/");
    }
}