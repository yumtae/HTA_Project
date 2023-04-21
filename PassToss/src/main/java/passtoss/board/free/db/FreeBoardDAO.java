package passtoss.board.free.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FreeBoardDAO {
	
	private DataSource ds;
	
	public FreeBoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return; 	
		}
	}

	public int getListCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from board_free where board_notice = ?");
			pstmt.setInt(1, 1); // 게시글만 count함 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getListCount() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return x;
	}

	//일반 게시물 
	public List<FreeBoard> getBoardList(int page, int limit) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
				+ "from(select rownum rnum, j.* "
				+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
				+ "     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
				+ "				 	           		       FROM COMMENT_FREE "
				+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
				+ "	      ON BOARD_NUM = COMMENT_BOARD_NUM "
				+ " 	      ORDER BY BOARD_RE_REF DESC, "
				+ "	      BOARD_RE_SEQ ASC) j "
				+ "		where board_notice = 1"
				+ "     and rownum <= ?) "
				+ " where rnum >= ? and rnum <= ?";
		
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return list;
		
	}
	
	//공지사항 
	public List<FreeBoard> getBoardList() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * "
				+ "from(select rownum rnum, j.* "
				+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
				+ "     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
				+ "				 	           		       FROM COMMENT_FREE "
				+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
				+ "	      ON BOARD_NUM = COMMENT_BOARD_NUM "
				+ " 	      ORDER BY BOARD_RE_REF DESC, "
				+ "	      BOARD_RE_SEQ ASC) j "
				+ "		where board_notice = 0"
				+ "     and rownum <= 3) "
				+ " where rnum >= 1 and rnum <= 3";
		
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		try {
			
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return list;
	}

	public int getListCount(String field, String value) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int x = 0;
		
		try{
			
			con = ds.getConnection();
			
			if(field.equals("all")) {
				sql = " select count(*) "
					+ " from board_free "
					+ " where(board_subject like ? or board_name like ?) "
					+ " and board_notice = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%"); 
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, 1); // 게시글만 count함 
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				sql = "select count(*) from board_free "
					+ "where " + field + " like ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%"); 
				rs = pstmt.executeQuery();
			}
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception e)
		{
			System.out.println("getListCount() 에러: " + e);
		}
		finally
		{
			try
			{
				if(rs != null)
				rs.close();
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				if(pstmt != null)
				pstmt.close();
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				if(con != null)
				con.close();		
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return x;
	}
	
	//공지사항 
	public List<FreeBoard> getBoardList(String field, String value) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ "		and " + field + " like ?"
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return list;
	}

	//일반게시물 
	public List<FreeBoard> getBoardList(String field, String value, int page, int limit) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, endrow);
				pstmt.setInt(4, startrow);
				pstmt.setInt(5, endrow);
				rs = pstmt.executeQuery();
				
			} else if(!field.equals("all")){
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_FREE "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ "		and " + field + " like ?"
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setInt(2, endrow);
				pstmt.setInt(3, startrow);
				pstmt.setInt(4, endrow);
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				FreeBoard board = new FreeBoard();
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getString("BOARD_DATE"));
				board.setBoard_notice(rs.getInt("BOARD_NOTICE"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getBoardList() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return list;
	}

	public int boardInsert(FreeBoard board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			con = ds.getConnection();
			
			String max_sql = "(select nvl(max(board_num),0)+1 from board_free)";
			
			String sql = "insert into board_free "
					   + "(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, "
					   + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, "
					   + " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
					   + " BOARD_DATE, BOARD_NOTICE) "
					   + " values(" + max_sql +", ?, ?, "
					   + " 		  ?, ?," + max_sql + ", " 
					   + "		  ?, ?, ?, sysdate, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, board.getBoard_notice());
			
			result = pstmt.executeUpdate();
			System.out.println("name : " + board.getBoard_name());
			if(result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
			
		}catch (Exception ex) {
			System.out.println("boardInsert() 에러: " + ex);
		}finally{
			
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return result;
	}

	public FreeBoard getDetail(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoard board = null; 
		
		try {
			
			con = ds.getConnection();
			
			String sql = "select * from board_free where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new FreeBoard();
				
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_notice(rs.getInt("board_notice"));
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getDetail() 에러: " + ex);
		}finally{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return board;
	}

	public int boardModify(FreeBoard fboard) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; 
		
		try {
			
			con= ds.getConnection();
			
			String sql = "update board_free "
					   + "set board_subject=?, board_content=?, board_file=?, board_notice =? "
					   + "where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fboard.getBoard_subject());
			pstmt.setString(2, fboard.getBoard_content());
			pstmt.setString(3, fboard.getBoard_file());
			pstmt.setInt(4, fboard.getBoard_notice());
			pstmt.setInt(5, fboard.getBoard_num());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("boardModify() 에러: " + ex);
		}finally{
			
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return result;
	}

	public int boardReply(FreeBoard board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		
		String board_max_sql = "select max(board_num)+1 from board_free";
		
		int re_ref = board.getBoard_re_ref();
		int re_lev = board.getBoard_re_lev();
		int re_seq = board.getBoard_re_seq();
		
		try {
			
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1);
			}
			pstmt.close();
			
			String sql = "update board_free "
					   + "set board_re_seq = board_re_seq + 1 "
					   + "where board_re_ref = ? "
					   + "and board_re_seq > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			re_seq = re_seq + 1;		
			re_lev = re_lev + 1;
			
			sql = "insert into board_free "
				+ "(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, "
				+ " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, "
				+ " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
				+ " BOARD_DATE, BOARD_NOTICE) "
				+ "values(" + num + ", ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?) "; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, "");
			pstmt.setInt(5, re_ref);
			pstmt.setInt(6, re_lev);
			pstmt.setInt(7, re_seq);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 1); // 
			
			if(pstmt.executeUpdate() == 1) {
				con.commit();
			} else {
				con.rollback();
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("boardReply() 에러: " + ex);
			if(con != null) {
				try {
					con.rollback(); // rollback합니다.
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}finally{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.setAutoCommit(true);
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		
		return num;
	}

	public int boardDelete(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			
			con = ds.getConnection();
			
			String select_sql ="select BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ "
							  +"from board_free "
							  +"where BOARD_NUM =?";
			
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			String board_delete_sql = "delete from board_free "
									+ "where board_re_ref = ? "
									+ "and board_re_lev >=? "
									+ "and board_re_seq>=? "
									+ "and board_re_seq <=(  "
									+ " 					nvl((select min(Board_re_seq) -1 "
									+ "					    from board_free "
									+ "					    where board_re_ref = ? "
									+ "					    and board_re_lev = ? "
									+ "					    and board_re_seq >?),"
									+ "					    (select max(Board_re_seq)"
									+ "			  		    from board_free"
									+ "			 		    where board_re_ref = ?)))";
			if(rs.next()) {
				pstmt2 = con.prepareStatement(board_delete_sql);
				pstmt2.setInt(1, rs.getInt("BOARD_RE_REF"));
				pstmt2.setInt(2, rs.getInt("BOARD_RE_LEV"));
				pstmt2.setInt(3, rs.getInt("BOARD_RE_SEQ"));
				pstmt2.setInt(4, rs.getInt("BOARD_RE_REF"));
				pstmt2.setInt(5, rs.getInt("BOARD_RE_LEV"));
				pstmt2.setInt(6, rs.getInt("BOARD_RE_SEQ"));
				pstmt2.setInt(7, rs.getInt("BOARD_RE_REF"));
				
				result = pstmt2.executeUpdate();
			}
		}catch (Exception ex) {
			System.out.println("boardDelete() 에러: " + ex);
		}finally{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(pstmt2 != null) {
				try
				{
					pstmt2.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return result;
	}

	public void setReadCountUpdate(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update board_free "
				   + "set BOARD_READCOUNT=BOARD_READCOUNT+1 "
				   + "where BOARD_NUM = ?";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch (SQLException ex) {
			System.out.println("setReadCountUpdate() 에러: " + ex);
		}finally{
			
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
  
	public int getnoticecount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from board_free where board_notice = ?");
			pstmt.setInt(1, 0); // 게시글만 count함 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1); 
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getnoticecount() 에러 : " + e);
		}
		finally
		{
			
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
			if(pstmt != null) {
				try
				{
					pstmt.close();
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(con != null) {
				try
				{
					con.close();		
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		return x;
  }
  
	public ArrayList<FreeBoard> getNextPrevNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//select만 필요
		ResultSet rs = null;
			
		 String sql = "SELECT b.* FROM( "
					+ "SELECT "
					+ "    Board_num, "
					+ "    LAG(Board_num,1,-1) OVER(ORDER BY Board_num ASC) AS board_prev_num,"
					+ "    LEAD(Board_num,1,-1) OVER(ORDER BY Board_num ASC) AS board_next_num "
					+ "FROM BOARD_free  where BOARD_NOTICE = 1 and BOARD_RE_LEV = 0"
					+ ") b "
					+ "WHERE b.Board_num = ?" ;
                   


		ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();

			
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num); 
	
			rs= pstmt.executeQuery();
			
				
			while (rs.next()) {
				FreeBoard b = new FreeBoard();
				b.setBoard_prev_num(rs.getInt("board_prev_num"));
				b.setBoard_next_num(rs.getInt("board_next_num"));
				list.add(b);
			}
			
			
		}catch(Exception se){
			System.out.println("getNextPrevNum error "+ se.getMessage());
			
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}

		return list;
	}
}
