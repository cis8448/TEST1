package board.command;
import javax.servlet.http.*;
import board.model.*;
public class BoardReadCmd implements BoardCmd{
	public void excute(HttpServletRequest request, HttpServletResponse response){
		String inputNum = request.getParameter("num");
		BoardDAO dao	= new BoardDAO();
		BoardDTO writing= dao.boardRead(inputNum);
		
		request.setAttribute("boardRead", writing);
	}
}
