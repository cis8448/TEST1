package board.command;
import javax.servlet.http.*;
	public class BoardUpdatePasswordCmd implements BoardCmd{
		public boolean password_check;
		
		public void excute(HttpServletRequest request, HttpServletResponse response) {
			String inputNum = request.getParameter("num");
			request.setAttribute("num", inputNum);
		}
}
