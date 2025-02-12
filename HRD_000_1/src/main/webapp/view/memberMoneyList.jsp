<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MemberMoneyVO"%>
<%@ page import="dao.MemberDao"%>
<%@ page import="java.util.ArrayList"%>

<%
		MemberDao dao = new MemberDao();
	ArrayList<MemberMoneyVO> list = dao.memberMoneyList();
%>
<jsp:include page="../master/header.jsp"/>
<section>
	<div class="container">
			<p>회원매출조회</p>
<%
	if(!list.isEmpty()){
%>			
		<table>
			<tr>
				<td>회원번호</td>
				<td>회원성명</td>
				<td>고객등급</td>
				<td>매출</td>
			</tr>
<%		
	for(MemberMoneyVO member : list){  %>		
		<tr>
			<td><%=member.getCustno() %></td>
			<td><%=member.getCustname() %></td>
			<td><%=member.getGrade() %></td>
			<td><%=member.getPrice() %></td>
		</tr>
<% 	}
%>	
<% }
	else { %>
		<p style="text-align: center">등록된 매출 정보가 없습니다.</p>
<%
	}
%>
		</table>
	</div>
</section>
<jsp:include page="../master/footer.jsp"/>