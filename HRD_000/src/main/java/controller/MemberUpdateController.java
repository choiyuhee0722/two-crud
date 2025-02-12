package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.MemberService;
import vo.MemberVO;

public class MemberUpdateController implements Controller{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp){
			if(req.getMethod().equals("POST")) {
				return processUpdateService(req,resp);
			}else if(req.getMethod().equals("GET")) {
				return processUpdateView(req,resp);				
			}
			return "redirect::/";
	}
	
	private String processUpdateService(HttpServletRequest req, HttpServletResponse resp) {
		//파라미터 추출
		int custno = Integer.parseInt(req.getParameter("custno"));
		String custname = req.getParameter("custname");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		Date joindate = Date.valueOf(req.getParameter("joindate"));
		String grade = req.getParameter("grade");
		String city = req.getParameter("city");
		
		//vo객체에 데이터 바인딩 
		MemberVO member = new MemberVO();
		member.setCustno(custno);
		member.setCustname(custname);
		member.setPhone(phone);
		member.setAddress(address);
		member.setJoindate(joindate);
		member.setGrade(grade);
		member.setCity(city);
		
		//service객체의 메소드 호출 
		MemberService service = new MemberService();
		int n = 0;
		try {
				n = service.memberUpdate(member);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(n > 0) {
			return "redirect::memberList";
		}else {
			return "memberUpdate";			
		}
	}
	private String processUpdateView(HttpServletRequest req, HttpServletResponse resp) {
			int custno = Integer.parseInt(req.getParameter("custno"));
			//service 객체의 메소드 호출
			MemberService service = new MemberService();
			MemberVO member = service.getMember(custno);
			req.setAttribute("member", member);
			return "memberUpdate";
	}
	
}
