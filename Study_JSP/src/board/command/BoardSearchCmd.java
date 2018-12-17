package board.command;
import javax.servlet.http.*;
import java.util.ArrayList;
import board.model.*;
	public class BoardSearchCmd implements BoardCmd{
		public void excute(HttpServletRequest request, HttpServletResponse response) {
			BoardDAO dao = new BoardDAO();
			String searchOption = request.getParameter("searchOption");
			String searchWord = request.getParameter("searchWord");
			
			ArrayList<BoardDTO> list = dao.boardSearch(searchOption, searchWord);
			request.setAttribute("boardList", list);
		}
}
