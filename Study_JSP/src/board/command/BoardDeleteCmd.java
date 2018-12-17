package board.command;
import javax.servlet.http.*;
import board.model.*;

public class BoardDeleteCmd implements BoardCmd{
	public void excute(HttpServletRequest request, HttpServletResponse response){
		String inputNum = request.getParameter("num");
		
		BoardDAO dao 	= new BoardDAO();
		dao.boardDelete(inputNum);
	}
}
