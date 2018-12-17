package board.command;
import javax.servlet.http.*;

public interface BoardCmd {
	public void excute(HttpServletRequest request, HttpServletResponse response);

}
