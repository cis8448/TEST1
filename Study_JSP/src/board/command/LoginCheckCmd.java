package board.command;
import javax.servlet.http.*;

import board.model.*;
public class LoginCheckCmd implements BoardCmd{
	
	public boolean login_check;
	
	public void excute(HttpServletRequest request, HttpServletResponse response){
		String id 		= request.getParameter("id");
		String pass 	= request.getParameter("pass");
		
		BoardDAO dao = new BoardDAO();
		login_check = dao.LoginCheck(id, pass);
		System.out.println("password_check : "    + login_check);
	}
}
