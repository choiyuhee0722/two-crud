<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDao"%>

<jsp:include page="../master/header.jsp"/>
<%
		MemberDao dao = new MemberDao();
	int custno = dao.getMaxCustNo();
%>
<section>
	<div class="container">
		<p class="title">홈쇼핑 회원 등록</p>
		<form action="memberInputProc.jsp" method="post" name="frm">
			<table width="600px">
			<tr>
					<td>회원번호(자동발생)</td>
					<td><input type="number" value="<%= custno %>" name="custno" id="custno" readonly="readonly"></td>
			</tr>
			<tr>
					<td>회원성명</td>
					<td><input type="text" id="custname" name="custname"></td>
			</tr>
			<tr>
					<td>회원전화</td>
					<td><input type="text" id="phone" name="phone"></td>
			</tr>
			<tr>
					<td>회원주소</td>
					<td><input type="text" id="address" name="address"></td>
			</tr>
			<tr>
					<td>가입일자</td>
					<td><input type="date" id="joindate" name="joindate"></td>
			</tr>
			<tr>
					<td>고객등급[A:VIP, B:일반 C:직원]</td>
					<td><select>
							<option value="A">VIP</option>					
							<option value="B">일반</option>					
							<option value="C">직원</option>					
						</select>
					</td>
			</tr>
			<tr>
					<td>도시코드</td>
					<td><input type="text" id="city" name="city"></td>
			</tr>
			<tr>
					<td colspan="2" align="center">
					<input type="submit" value="가입하기 " onclick="return checkForm()">
					</td>
			</tr>
		</table>
	</form>
	</div>
</section>
<script>
	fuction checkForm(){
		if(documemt.frm.custname.value.trim() == ""){
			alert('회원성명을 입력하세요');
			document.frm.custname.focus();
			return false;
		}
		if(documemt.frm.phone.value == ""){
			alert('회원전화을 입력하세요');
			document.frm.phone.focus();
			return false;
		}
		if(documemt.frm.address.value == ""){
			alert('회원주소을 입력하세요');
			document.frm.address.focus();
			return false;
		}
		if(documemt.frm.joindate.value == ""){
			alert('가입일자을 입력하세요');
			document.frm.joindate.focus();
			return false;
		}
		if(documemt.frm.grade.value == ""){
			alert('고객등급을 입력하세요');
			document.frm.grade.focus();
			return false;
		}
		if(documemt.frm.city.value == ""){
			alert('도시코드를 입력하세요');
			document.frm.city.focus();
			return false;
		}
		document.frm.submit();
	}
</script>
<jsp:include page="../master/footer.jsp"/>