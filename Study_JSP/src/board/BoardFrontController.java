package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import board.command.*;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bbs")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String requestURI 	= request.getRequestURI();
		String contextPath 	= request.getContextPath();
		String cmdURI		= requestURI.substring(contextPath.length());
		
		BoardCmd cmd = null;
		String viewPage = null;
		
		
		if(cmdURI.equals("/boardList.bbs")){
			cmd = new BoardListCmd();
			cmd.excute(request, response);
			viewPage = "boardList.jsp";
				}
		if(cmdURI.equals("/boardWriteForm.bbs")){
			viewPage = "boardWrite.jsp";
		}
		if(cmdURI.equals("/boardWrite.bbs")){
			cmd = new BoardWriteCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
		if(cmdURI.equals("/boardRead.bbs")){
			cmd = new BoardReadCmd();
			cmd.excute(request, response);
			viewPage = "boardRead.jsp";
		}
		if(cmdURI.equals("/boardUpdatePassword.bbs")){
			cmd = new BoardUpdatePasswordCmd();
			cmd.excute(request, response);
			viewPage = "boardUpdatePassword.jsp";
		}
		if(cmdURI.equals("/boardUpdateCheck.bbs")){
			cmd = new BoardUpdateCheckCmd();
			cmd.excute(request, response);
			
			BoardUpdateCheckCmd checkCmd = (BoardUpdateCheckCmd) cmd;
			
			if(checkCmd.password_check){
				viewPage = "boardUpdateForm.bbs";
			}else{
				viewPage = "boardUpdateError.bbs";
			}
		}
		
		//글비밀번호 오류
		
		if(cmdURI.equals("/boardUpdateError.bbs")){
			viewPage = "boardUpdateError.jsp";
		}
		
		if(cmdURI.equals("/boardUpdateForm.bbs")){
			cmd = new BoardUpdateFormCmd();
			cmd.excute(request, response);
			viewPage = "boardUpdateForm.jsp";
		}
		
		if(cmdURI.equals("/boardUpdate.bbs")){
			cmd = new boardUpdateCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
		if(cmdURI.equals("/boardDeletePassword.bbs")){
			cmd = new BoardDeletePasswordCmd();
			cmd.excute(request, response);
			viewPage = "borderDeletePassword.jsp";
		}
		if(cmdURI.equals("/boardDeleteCheck.bbs")){
			cmd = new BoardDeleteCheckCmd();
			cmd.excute(request, response);
			
			BoardDeleteCheckCmd checkCmd = (BoardDeleteCheckCmd) cmd;
			if(checkCmd.password_check && checkCmd.reply_check){
				viewPage = "boardDelete.bbs";
			}else {
				viewPage = "boardDeleteError.bbs";
			}
		}
		if(cmdURI.equals("/boardDeleteError.bbs")){
			viewPage = "boardDeleteError.jsp";
		}
		if(cmdURI.equals("/boardDelete.bbs")){
			cmd = new BoardDeleteCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
		if(cmdURI.equals("/boardSearch.bbs")){
			cmd = new BoardSearchCmd();
			cmd.excute(request, response);
			viewPage = "boardSearch.jsp";
		}
		if(cmdURI.equals("/Login.bbs")){
			viewPage = "Login.jsp";
		}
		if(cmdURI.equals("/LoginCheck.bbs")){
			cmd = new LoginCheckCmd();
			cmd.excute(request, response);
			
			LoginCheckCmd checkCmd = (LoginCheckCmd) cmd;
			if(checkCmd.login_check){
				viewPage = "cookse.jsp";
			}else {
				viewPage = "LoginError.bbs";
			}
		}
		if(cmdURI.equals("/LoginError.bbs")){
			viewPage = "LoginError.jsp";
		}
		if(cmdURI.equals("/logout.bbs")){
			viewPage = "LogQuit.jsp";
		}
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
		}
	}
