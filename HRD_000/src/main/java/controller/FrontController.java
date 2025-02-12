package controller;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
		String charset = null;
		HashMap<String, Controller> list = null;
		
		private final String prefix = "/WEB-INF/view/";
		private final String postfix = ".jsp";
		
		@Override
		public void init(ServletConfig config) throws ServletException {
			charset = config.getInitParameter("charset");
			list = new HashMap<String, Controller>();
			list.put("/", new MainController());
			list.put("/memberInsert", new MemberInsertController());
			list.put("/memberList", new MemberListController());
			list.put("/memberMoneyList", new MemberMoneyListController());
			list.put("/memberUpdate", new MemberUpdateController());
		}
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.setCharacterEncoding(charset);
			
			String url = req.getRequestURI();
			String contextPath =req.getContextPath();
			String path = url.substring(contextPath.length());
			System.out.println("URL"+url);
			System.out.println(contextPath);
			System.out.println(path);
			Controller subController = list.get(path);
			
			if(subController == null) {
					resp.sendRedirect("/"); 
					return;
			}
			
			String result = subController.execute(req,resp);
			System.out.println(result);
			if(result.indexOf("redirect::") != 0) {
				//일반적인 상황
				RequestDispatcher dispatcher = req.getRequestDispatcher(prefix + result + postfix);
				System.out.println(prefix + result + postfix);
				dispatcher.forward(req, resp);
			}else {
				//리다이렉트 상황
				String reUrl = result.substring("redirect::".length());
				resp.sendRedirect(reUrl);
			}
		}
		
}
