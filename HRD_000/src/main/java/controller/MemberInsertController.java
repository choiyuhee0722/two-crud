package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.MemberService;
import vo.MemberVO;

public class MemberInsertController implements Controller{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
		if(req.getMethod().equals("POST")) {
					return processInsertService(req,resp);				
		}else if(req.getMethod().equals("GET")) {
					return processInsertView(req,resp);
		}
		return "redirect::/";
	}
	
	private String processInsertService(HttpServletRequest req, HttpServletResponse resp){
			
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
			n = service.memberInsert(member);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(n > 0) {
			return "redirect::memberList";
		}else {
			return "memberInput";
			
		}
		
	}
	private String processInsertView(HttpServletRequest req, HttpServletResponse resp){
		MemberService service = new MemberService();
		int custno = service.getMaxCustNo();
		req.setAttribute("custno", custno);
		
		
		return "memberInput";
	}
}
