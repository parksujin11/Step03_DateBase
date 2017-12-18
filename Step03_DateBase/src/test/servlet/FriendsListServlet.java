package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.FriendsDao;
import test.dto.FriendsDto;
@WebServlet("/friends/list")
public class FriendsListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<FriendsDto> list=FriendsDao.getInstance().getList();
		System.out.println(list);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8' />");
		pw.println("<title>친구 목록 페이지 </title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h3>친구목록 입니다.</h3>");
		pw.println("<table>");
		pw.println("<thead>");
		pw.println("<tr>");
		pw.println("<th>번호</th>");
		pw.println("<th>이름</th>");
		pw.println("<th>전화번호</th>");
		pw.println("<tr>");
		pw.println("<thead>");
		pw.println("<tbody>");
		//반복문 이용해서 회원 목록 출력 하기 
		for(FriendsDto tmp:list) {
			pw.println("<tr>");
			pw.println("<td>"+tmp.getNum()+"</td>");
			pw.println("<td>"+tmp.getName()+"</td>");
			pw.println("<td>"+tmp.getPhone()+"</td>");
			pw.println("</tr>");
		}
		pw.println("</tbody>");
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
