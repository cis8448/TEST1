package board.model;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.Cookie;

public class BoardDAO {
	
	Connection conn ;
	public static final int WRITING_PER_PAGE = 10;

	public ArrayList<BoardDTO> boardList(String curPage){
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection 		  	conn 	= 	null;
		PreparedStatement 	pstmt	= 	null;
		ResultSet			rs 		= 	null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
					"jspbook",
					"jspbook");
			
			pstmt = conn.prepareStatement("SELECT *"
										+ " FROM BOARD ORDER BY REF DESC, STEP ASC"
										+ " LIMIT ?,?");
			pstmt.setInt(1, WRITING_PER_PAGE * (Integer.parseInt(curPage)-1));
			pstmt.setInt(2, WRITING_PER_PAGE);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int num 			= rs.getInt("num");
				String name 		= rs.getString("name");
				String password 	= rs.getString("password");
				String subject 		= rs.getString("subject");
				String content 		= rs.getString("content");
				Date writeDate 		= rs.getDate("write_date");
				Time writeTime 		= rs.getTime("write_time");
				int ref				= rs.getInt("REF");
				int step			= rs.getInt("step");
				int lev 			= rs.getInt("lev");
				int readCnt 		= rs.getInt("read_Cnt");
				int childCnt 		= rs.getInt("child_Cnt");
				
				BoardDTO whiting = new BoardDTO();
				whiting.setNum(num);
				whiting.setName(name);
				whiting.setPassword(password);
				whiting.setSubject(subject);
				whiting.setContent(content);
				whiting.setWriteDate(writeDate);
				whiting.setWriteTime(writeTime);
				whiting.setRef(ref);
				whiting.setStep(step);
				whiting.setLev(lev);
				whiting.setReadCnt(readCnt);
				whiting.setChildCnt(childCnt);
				list.add(whiting);
			};
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}			
				} catch (SQLException e) {
					e.printStackTrace();
			}
			
		}
		return list;
	}
	public int boardPageCnt(){
		
		int PageCnt = 0;
		
		Connection 			conn 		=	null;
		PreparedStatement 	pstmt 		= 	null;
		ResultSet			rs			= 	null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			pstmt = conn.prepareStatement("SELECT COUNT(*) AS NUM FROM BOARD ");
			rs	  =	pstmt.executeQuery();
			
			if(rs.next()){
				PageCnt = rs.getInt("num") / WRITING_PER_PAGE +1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
				}catch(SQLException e){
					e.printStackTrace();
			}
		}
		return PageCnt;
	}
	
	//새글 쓰기
	public void boardWrite(String name, String subject, String content, String password){
		Connection 		  	conn 	= 	null;
		PreparedStatement 	pstmt	= 	null;
		ResultSet			rs 		= 	null;
		int 				num		= 	1	; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
					"jspbook",
					"jspbook");
			String sql = "SELECT IFNULL(MAX(NUM),0)+1 AS NUM FROM BOARD";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("num");
			}
			
			sql = "INSERT INTO BOARD (NUM, NAME, PASSWORD, SUBJECT, CONTENT, WRITE_DATE, WRITE_TIME, REF, STEP, LEV, READ_CNT, CHILD_CNT)"
					+	" VALUES (?,?,?,?,?,CURDATE(),CURTIME(),?,0,0,0,0)";
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt	(1, num);
			pstmt.setString	(2, name);
			pstmt.setString	(3, password);
			pstmt.setString	(4, subject);
			pstmt.setString	(5, content);
			pstmt.setInt	(6, num);
			
			pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs 		!= null) rs.close();
					if(conn		!= null) conn.close();
					if(pstmt 	!= null) pstmt.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
			}
	}
	public BoardDTO boardRead(String inputNum){
		BoardDTO writing= new BoardDTO();
		Connection 			conn 		=	null;
		PreparedStatement 	pstmt 		= 	null;
		ResultSet			rs			= 	null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			String sql = "UPDATE BOARD SET READ_CNT = READ_CNT+1 WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(inputNum));
			pstmt.executeUpdate();
			
			sql = 		"SELECT NUM, NAME, PASSWORD, SUBJECT, CONTENT, WRITE_DATE, WRITE_TIME, REF, STEP, LEV, READ_CNT, CHILD_CNT"
					+	" FROM BOARD WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			rs	  =	pstmt.executeQuery();
			
			if(rs.next()){
				int num 			= rs.getInt("num");
				String name 		= rs.getString("name");
				String password 	= rs.getString("password");
				String subject 		= rs.getString("subject");
				String content 		= rs.getString("content");
				Date writeDate 		= rs.getDate("write_date");
				Time writeTime 		= rs.getTime("write_time");
				int ref				= rs.getInt("REF");
				int step			= rs.getInt("step");
				int lev 			= rs.getInt("lev");
				int readCnt 		= rs.getInt("read_Cnt");
				int childCnt 		= rs.getInt("child_Cnt");
				
				writing.setNum(num);
				writing.setName(name);
				writing.setPassword(password);
				writing.setSubject(subject);
				writing.setContent(content);
				writing.setWriteDate(writeDate);
				writing.setWriteTime(writeTime);
				writing.setRef(ref);
				writing.setStep(step);
				writing.setLev(lev);
				writing.setReadCnt(readCnt);
				writing.setChildCnt(childCnt);
			}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			if(rs 	 != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn	 != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return writing;
}
	public void boardUpdate (String inputNum, String inputSubject, String inputContent, String inputName, String inputPassword){
		Connection 			conn 		=	null;
		PreparedStatement 	pstmt 		= 	null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			String sql = "UPDATE BOARD SET SUBJECT = ?, CONTENT = ?, NAME = ?, PASSWORD = ? WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputSubject);
			pstmt.setString(2, inputContent);
			pstmt.setString(3, inputName);
			pstmt.setString(4, inputPassword);
			pstmt.setInt   (5, Integer.parseInt(inputNum));
			pstmt.executeUpdate();
			
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(pstmt != null ) pstmt.close();
			if(conn != null ) conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
	public boolean boardPasswordCheck(String inputNum, String inputPassword){
		boolean passwordOk = false;
		int passwordCheck = 0;
		
		Connection 			conn 		=	null;
		PreparedStatement 	pstmt 		= 	null;
		ResultSet			rs			= 	null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			String sql = "SELECT COUNT(*) AS PASSWORD_CHECK FROM BOARD WHERE NUM = ? AND PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			pstmt.setString(2, inputPassword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) passwordCheck = rs.getInt("PASSWORD_CHECK");
			
			if(passwordCheck > 0) passwordOk = true;
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			if( rs 		!= null ) rs.close();
			if( pstmt 	!= null ) pstmt.close();
			if( conn 	!= null ) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return passwordOk;
	}
	public BoardDTO boardUpdateForm(String inputNum){
		BoardDTO writing= new BoardDTO();
		Connection 			conn 		=	null;
		PreparedStatement 	pstmt 		= 	null;
		ResultSet			rs			= 	null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			
			String sql = "SELECT NUM, NAME, PASSWORD, SUBJECT, CONTENT, WRITE_DATE, WRITE_TIME, REF, STEP, LEV, READ_CNT, CHILD_CNT"
					+	" FROM BOARD WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			rs	  =	pstmt.executeQuery();
			
			if(rs.next()){
				int num 			= rs.getInt("num");
				String name 		= rs.getString("name");
				String password 	= rs.getString("password");
				String subject 		= rs.getString("subject");
				String content 		= rs.getString("content");
				Date writeDate 		= rs.getDate("write_date");
				Time writeTime 		= rs.getTime("write_time");
				int ref				= rs.getInt("REF");
				int step			= rs.getInt("step");
				int lev 			= rs.getInt("lev");
				int readCnt 		= rs.getInt("read_Cnt");
				int childCnt 		= rs.getInt("child_Cnt");
				
				writing.setNum(num);
				writing.setName(name);
				writing.setPassword(password);
				writing.setSubject(subject);
				writing.setContent(content);
				writing.setWriteDate(writeDate);
				writing.setWriteTime(writeTime);
				writing.setRef(ref);
				writing.setStep(step);
				writing.setLev(lev);
				writing.setReadCnt(readCnt);
				writing.setChildCnt(childCnt);
	}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			if(rs 	 != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn	 != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return writing;
}
	public void boardDelete(String inputNum){
		Connection 			conn  	= null;
		PreparedStatement 	pstmt	= null;
		ResultSet 			rs		= null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			
			String sql = "SELECT REF, LEV, STEP FROM BOARD WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(inputNum));
			rs 	  = pstmt.executeQuery();
			
			if(rs.next()){
				int ref = rs.getInt(1);
				int lev = rs.getInt(2);
				int step = rs.getInt(3);
				boardDeleteChildCntUpdate(ref,lev,step);
			}
			
			sql 	= "DELETE FROM BOARD WHERE NUM = ?";
			pstmt	= conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			
			pstmt.executeUpdate();		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs 		!= null) 	rs.close();
				if(pstmt 	!= null) 	pstmt.close();
				if(conn 	!= null) 	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean boardReplyCheck(String inputNum){
		boolean replyCheck		= false;
		int 	replyCnt		= 0;
		
		Connection 			conn  	= null;
		PreparedStatement 	pstmt	= null;
		ResultSet 			rs		= null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			
			String sql = "SELECT CHILD_CNT AS REPLY_CHECK FROM BOARD WHERE NUM = ?";
			pstmt	   = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			
			rs		   = pstmt.executeQuery();
			
			if(rs.next()) 	  replyCnt	 = rs.getInt("REPLY_CHECK");
			if(replyCnt	== 0) replyCheck = true;
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			if( rs    != null) rs.close();
			if( pstmt != null) pstmt.close();
			if( conn  != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return replyCheck;
	}
	public void boardDeleteChildCntUpdate(int ref,int lev,int step) {
		Connection 			conn  	= null;
		PreparedStatement 	pstmt	= null;
		ResultSet 			rs		= null;
		String 				sql		= null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			
			for(int updateLev = lev-1; updateLev>=0;updateLev--){
				sql = "SELECT MAX(STEP) FROM BOARD WHERE REF = ? AND LEV ? AND STEP < ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, lev);
				pstmt.setInt(3, step);
				rs 	  =	pstmt.executeQuery();
				int maxStep = 0;
				
				if(rs.next()) maxStep = rs.getInt(1);
				sql = "UPDATE BOARD SET CHILD_CNT = CHILD_CNT - 1 WHERE REF = ? AND LEV = ? AND STEP = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, lev);
				pstmt.setInt(3, step);
				pstmt.executeUpdate();
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	try {
		if(rs 		!= null) rs.close();
		if(pstmt 	!= null) pstmt.close();
		if(conn 	!= null) conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
	public ArrayList<BoardDTO> boardSearch(String searchOption, String searchWord){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection 			conn  	= null;
		PreparedStatement 	pstmt	= null;
		ResultSet 			rs		= null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn 	=	DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
			"jspbook",
			"jspbook");
			String sql = "SELECT NUM, NAME, PASSWORD, SUBJECT, CONTENT, WRITE_DATE, WRITE_TIME, REF, STEP, LEV, READ_CNT, CHILD_CNT"
						+ " FROM BOARD";
			
			if(searchOption.equals("subject")){
				sql += " WHERE SUBJECT LIKE ?";
				sql += " ORDER BY REF DESC, STEP ASC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord + "%");
			}else if(searchOption.equals("content")){
				sql += " WHERE CONTENT LIKE ?";
				sql += " ORDER BY REF DESC, STEP ASC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord + "%");
			}else if(searchOption.equals("name")){
				sql += " WHERE NAME LIKE ?";
				sql += " ORDER BY REF DESC, STEP ASC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord + "%");
			}else {
				sql += " WHERE SUBJECT LIKE ? OR CONTENT LIKE ?";
				sql += " ORDER BY REF DESC, STEP ASC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord + "%");
				pstmt.setString(2, "%" + searchWord + "%");
			}
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int num 			= rs.getInt("num");
					String name 		= rs.getString("name");
					String password 	= rs.getString("password");
					String subject 		= rs.getString("subject");
					String content 		= rs.getString("content");
					Date writeDate 		= rs.getDate("write_date");
					Time writeTime 		= rs.getTime("write_time");
					int ref				= rs.getInt("REF");
					int step			= rs.getInt("step");
					int lev 			= rs.getInt("lev");
					int readCnt 		= rs.getInt("read_Cnt");
					int childCnt 		= rs.getInt("child_Cnt");
					
					BoardDTO whiting = new BoardDTO();
					whiting.setNum(num);
					whiting.setName(name);
					whiting.setPassword(password);
					whiting.setSubject(subject);
					whiting.setContent(content);
					whiting.setWriteDate(writeDate);
					whiting.setWriteTime(writeTime);
					whiting.setRef(ref);
					whiting.setStep(step);
					whiting.setLev(lev);
					whiting.setReadCnt(readCnt);
					whiting.setChildCnt(childCnt);
					list.add(whiting);
					
				}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			if(rs 		!= null) rs.close();
			if(pstmt 	!= null) pstmt.close();
			if(conn 	!= null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return list;
}
	public boolean LoginCheck(String id, String pass) {
		boolean LoginOk = false;
		int LoginCheck = 0;
		
		Connection 			conn 		=	null;
		PreparedStatement 	pstmt 		= 	null;
		ResultSet			rs			= 	null;
		
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn 	=	DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/jspbook?characterEncoding=UTF-8&serverTimezone=UTC",
		"jspbook",
		"jspbook");
		String sql = "SELECT COUNT(*) AS Login_CHECK FROM MEMBER WHERE id = ? AND PW = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pass);
		rs = pstmt.executeQuery();
		if(rs.next()){ LoginCheck = rs.getInt("Login_CHECK");}
		
		if(LoginCheck > 0) LoginOk = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs 		!= null) rs.close();
				if(pstmt 	!= null) pstmt.close();
				if(conn 	!= null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return LoginOk;
	}
}