package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.FriendsDao;
import test.dao.MemberDao;
import test.dto.FriendsDto;
import test.dto.MemberDto;

@WebServlet("/friends/insert")
public class FriendsInsertServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		FriendsDto dto=new FriendsDto();
		dto.setName(name);
		dto.setPhone(phone);

		FriendsDao.getInstance().insert(dto);
		
		response.setCharacterEncoding("utf-8");
		//응답 컨텐츠 설정
		response.setContentType("text/html;charset=utf-8");
		// 클라이언트에게 문자열을 출력할 수 있는 객체 얻어오기
		PrintWriter pw = response.getWriter();//출력하면 클라이언트에게 보여줌
		pw.println("<doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8' />");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>친구 정보를 추가 했습니다.</p>");
		pw.println("<a href='list'>친구 목록 보기</a>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
