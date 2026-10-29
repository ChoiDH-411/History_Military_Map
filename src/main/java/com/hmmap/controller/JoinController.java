package com.hmmap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hmmap.dao.MemberDao;
import com.hmmap.dto.Member;
import com.hmmap.util.LangUtil;
import com.hmmap.util.PasswordUtil;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/member/join")
public class JoinController extends HttpServlet {

    private static final String VIEW = "/WEB-INF/views/member/join.jsp";
    private final MemberDao memberDao = new MemberDao();

    // 가입 폼 보여주기
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    // 가입 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 입력값 꺼내기 (앞뒤 공백 제거, null이면 빈 문자열)
        String loginId         = trim(request.getParameter("loginId"));
        String password        = nvl(request.getParameter("password"));
        String passwordConfirm = nvl(request.getParameter("passwordConfirm"));
        String nickname        = trim(request.getParameter("nickname"));
        String joinLang        = request.getParameter("lang");
        if (!LangUtil.isSupported(joinLang)) {
            joinLang = LangUtil.DEFAULT_LANG;
        }

        // 2. 검사 → 오류 키 (문제없으면 null)
        String error = validate(loginId, password, passwordConfirm, nickname);
        if (error == null && memberDao.findByLoginId(loginId) != null) {
            error = "error.loginId.duplicate";
        }

        // 3. 오류가 있으면 입력값과 함께 폼으로 되돌리기
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("loginId", loginId);
            request.setAttribute("nickname", nickname);
            request.setAttribute("joinLang", joinLang);
            request.getRequestDispatcher(VIEW).forward(request, response);
            return;
        }

        // 4. 회원 객체를 만들어 DB에 저장
        Member member = new Member();              // 빈 Member 상자 만들기
        member.setLoginId(loginId);                // 아이디 (검사를 통과한 값)
        member.setPassword(PasswordUtil.hash(password));  // ★ 평문이 아니라 해시값을 저장
        member.setNickname(nickname);              // 닉네임 (앞뒤 공백 제거된 값)
        member.setLang(joinLang);                  // 선호 언어 (ko/ja로 검증된 값)
        memberDao.insert(member);                  // INSERT 실행 (role, created_at은 DB 기본값)

        // 5. 완료 → 메인으로 redirect (3-C에서 로그인 페이지로 바꿈)
        response.sendRedirect(request.getContextPath() + "/");
    }

    // 입력값 검사: 첫 번째로 걸린 오류의 properties 키를 반환 (모두 통과하면 null)
    private String validate(String loginId, String password, String passwordConfirm, String nickname) {

        // 아이디: 영문 대소문자 + 숫자만, 4~20글자
        //   [A-Za-z0-9] = 이 중 한 글자 / {4,20} = 4번 이상 20번 이하 반복
        if (!loginId.matches("[A-Za-z0-9]{4,20}")) {
            return "error.loginId.format";
        }

        // (나) 비밀번호: 8글자 미만이면 오류
        //   length()는 글자 수를 돌려줌 (한글 1글자도 1)
        if (password.length() < 8) {
            return "error.password.length";
        }

        // (다) 비밀번호와 확인이 다르면 오류
        //   문자열은 == 가 아니라 equals()로 비교 (== 는 "같은 객체인지"를 비교해서 내용이 같아도 false가 될 수 있음)
        //   "다르면"이니 앞에 ! (not)
        if (!password.equals(passwordConfirm)) {
            return "error.password.mismatch";
        }

        // (라) 닉네임: 비어 있거나(isEmpty) 20글자를 넘으면 오류
        //   || = 둘 중 하나라도 true면 true
        //   trim()을 이미 했기 때문에 공백만 입력한 경우도 isEmpty()에 걸림
        if (nickname.isEmpty() || nickname.length() > 20) {
            return "error.nickname.length";
        }

        return null;   // 모든 검사 통과
    }

    private String nvl(String s)  { return s == null ? "" : s; }
    private String trim(String s) { return s == null ? "" : s.trim(); }
}