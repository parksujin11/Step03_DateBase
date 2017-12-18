package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.FriendsDao;
import test.dto.FriendsDto;

@WebServlet("/friends/update")
public class FriendsUpdateServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num=Integer.parseInt(request.getParameter("num"));
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		FriendsDto dto=new FriendsDto(num, name, phone);
		FriendsDao.getInstance().update(dto);
		
		//3. 응답하기 
		//응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		//응답 컨텐츠 설정
		response.setContentType("text/html;charset=utf-8");
		// 클라이언트에게 문자열을 출력할 수 있는 객체 얻어오기
		PrintWriter pw = response.getWriter();//출력하면 클라이언트에게 보여줌
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8' />");
		pw.println("<title>알림</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<script>");
		pw.println("alert('수정했습니다.');");
		pw.println("location.href='list';");
		pw.println("</script>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
