package hw02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FindUser
 */
//@WebServlet("/FindUser")
public class FindUser extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();
		
		String cName=req.getParameter("name");
		
		if(cName==null) {
			HttpSession session=req.getSession();
			cName=((UserVO)session.getAttribute("userVO")).getName();
					
		}
		
		UserDao userDao=new UserDao();
		UserVO userVO=userDao.findUser(cName);
		
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2>내정보 보기</h2>");
		
		if(userVO.isActive()) {
			out.println("이름 : "+userVO.getName()+"<br/>");
			out.println("성별 : "+userVO.getSex()+"<br/>");
			out.println("생년월일 : "+userVO.getYear()+"년 "+userVO.getBirthMonth()+"월 "+userVO.getBirthDay()+"일<br/>");
			out.println("직업 : "+userVO.getOccupation()+"<br/>");
			out.println("휴대폰 : "+userVO.getFirstNumberM()+" "+userVO.getMiddleNumberM()+" "+userVO.getLastNumberM()+"<br/>");
		}else {
			out.println("정확한 이름을 입력하세요.");
		}
	
		
		out.println("</body>");
		out.println("</html>");
	
	}
	

}