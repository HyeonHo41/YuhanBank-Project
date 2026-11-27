package bank.member.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bank.common.exception.BankException;
import bank.member.dto.LoginUser;
import bank.member.dto.MemberDTO;
import bank.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/*")  // member 시작 경로 다 받은 다음 -> switch로 분기처리 
public class MemberController extends HttpServlet{
	
	private final MemberService memberService = new MemberService();
	
	@Override //Get요청 Read처리
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getPathInfo();
		// 잘못된 경로 처리
		if (path == null) path = "/";
		
		switch (path) {
		case "/login":  //로그인
			forward(req, resp, "member/login");
			break;
		case "/join":
			forward(req, resp, "member/join");
			break;
		case "/logout":
			logout(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		
		}
	}
	

	@Override //Create, Update, Delete 요청 처리
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getPathInfo();
		// 잘못된 경로 처리
		if (path == null) path = "/";
		
		switch (path) {
		case "/login":
			login(req, resp);
			break;
		case "/join":
			join(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	
	
	
	// 회원가입 처리
	private void join(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		
		dto.setMemberId(trim(req.getParameter("memberId")));
		dto.setMemberPw(req.getParameter("memberPw"));          //
		dto.setMemberName(trim(req.getParameter("memberName")));
		dto.setPhone(trim(req.getParameter("phone")));
		dto.setEmail(trim(req.getParameter("email")));
		String memberPw2 = req.getParameter("memberPw2");

		try {
			dto.setBirthDate(parseDate(req.getParameter("birthDate")));
			
			memberService.join(dto, memberPw2);
			resp.sendRedirect(req.getContextPath() + "/member/login?joined=1");
			
		} catch (BankException e) {
			dto.setMemberPw(null);
			req.setAttribute("error", e.getMessage());
			req.setAttribute("member", dto);
			
			forward(req, resp, "member/join");
			
		}
		
	}
	


	// 로그인 처리
	private void login(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		
		try {
			LoginUser loginUser = memberService.login(memberId, memberPw);
			
			// 세션 없을 경우 만들고 로그인 후 세션 ID 교체
			req.getSession();
			req.changeSessionId();
			
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", loginUser);
			
			// 로그인 성공 -> main화면으로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/main");
			
		} catch (BankException e) {
			req.setAttribute("error", e.getMessage()); 
			req.setAttribute("memberId", memberId);
			forward(req, resp, "member/login");
		} 
		
	}
	// 로그아웃 처리
	private void logout(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		if (session != null) {
			// 세션 전부 삭제
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath() + "/member/login");
	}
	
	private String trim(String value) {
		if (value == null) return null;
		value = value.trim();
		return value.isEmpty() ? null : value;
	}
	
	
	private LocalDate parseDate(String value) {
		value = trim(value);
		if (value == null) return null;
		try {
			return LocalDate.parse(value);
		} catch (DateTimeParseException e) {
			throw new BankException("생년월일 형식이 올바르지 않습니다.");
		}
	}


	private void forward(HttpServletRequest req, HttpServletResponse resp, String view) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(req, resp);		
	}

}
