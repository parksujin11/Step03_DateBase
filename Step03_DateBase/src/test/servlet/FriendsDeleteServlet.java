package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.FriendsDao;

@WebServlet("/friends/delete")
public class FriendsDeleteServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	//1. 삭제할 회원의 번호를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));	
		
	FriendsDao.getInstance().delete(num);
	
	response.setCharacterEncoding("utf-8");
	
	PrintWriter pw = response.getWriter();//출력하면 클라이언트에게 보여줌
	pw.println("<doctype html>");
	pw.println("<html>");
	pw.println("<head>");
	pw.println("<meta charset='utf-8' />");
	pw.println("<title>알림</title>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<p>친구 정보 삭제</p>");
	pw.println("<a href='list'>친구 목록 보기</a>");
	pw.println("</body>");
	pw.println("</html>");
	
	}
}
