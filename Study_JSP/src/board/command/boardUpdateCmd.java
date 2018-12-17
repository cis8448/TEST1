package board.command;
import javax.servlet.http.*;
import board.model.*;
	public class boardUpdateCmd implements BoardCmd{
		public void excute(HttpServletRequest request, HttpServletResponse response){
			String inputNum = request.getParameter("num");
			String inputSubject = request.getParameter("subject");
			String inputContent = request.getParameter("content");
			String inputName = request.getParameter("name");
			String inputPassword = request.getParameter("password");
			
			BoardDAO dao = new BoardDAO();
			dao.boardUpdate(inputNum, inputSubject, inputContent, inputName, inputPassword);
		}
}
