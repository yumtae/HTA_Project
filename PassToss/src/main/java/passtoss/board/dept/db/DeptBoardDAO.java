package passtoss.board.dept.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DeptBoardDAO {
	
	private DataSource ds;
	
	public DeptBoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return; 	
		}
	}

	public int getListCount(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			
			con = ds.getConnection();
			if(deptno != 0) {
			 pstmt = con.prepareStatement("select count(*) from board_dept "
									   + "where board_notice = ? and board_deptno = ?");
			 pstmt.setInt(1, 1); // 게시글만 count함 
			 pstmt.setInt(2, deptno);
			 rs = pstmt.executeQuery();
			 
			}else if(deptno == 0) {
				pstmt = con.prepareStatement("select count(*) from board_dept "
						 						+ "where board_notice = ?");
				pstmt.setInt(1, 1); // 게시글만 count함 
				rs = pstmt.executeQuery();
			}
			
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

	public List<DeptBoard> getBoardList(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		
		try {
			
			con = ds.getConnection(); 
			if(deptno != 0) {
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	  FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		       FROM COMMENT_DEPT "
						+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	  ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	      ORDER BY BOARD_RE_REF DESC, "
						+ "	      BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ "     and board_deptno = ? "
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
			 pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, deptno);
			 rs = pstmt.executeQuery();
			} else if(deptno == 0) {
				
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	  FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		       FROM COMMENT_DEPT "
						+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	  ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	      ORDER BY BOARD_RE_REF DESC, "
						+ "	      BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ "     and board_deptno = 10 or board_deptno = 20 "
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
				
			 pstmt = con.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
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
				board.setBoard_deptno(rs.getInt("board_deptno"));
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

	public List<DeptBoard> getBoardList(int page, int limit, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		
		try {
			
			con = ds.getConnection(); 
			
			if(deptno != 0) {
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	  FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		       FROM COMMENT_DEPT "
						+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
						+ "	          ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	      ORDER BY BOARD_RE_REF DESC, "
						+ "	      BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1 "
						+ "     and board_deptno = ? "
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
			
			 pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, deptno);
			 pstmt.setInt(2, endrow);
			 pstmt.setInt(3, startrow);
			 pstmt.setInt(4, endrow);
			 rs = pstmt.executeQuery();
			 
			} else if(deptno == 0) {
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	  FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		       FROM COMMENT_DEPT "
						+ "				 	          		       GROUP BY COMMENT_BOARD_NUM) "
						+ "	          ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	      ORDER BY BOARD_RE_REF DESC, "
						+ "	      BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1 "
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
			
			 pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, endrow);
			 pstmt.setInt(2, startrow);
			 pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
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
				board.setBoard_deptno(rs.getInt("board_deptno"));
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

	public int getListCount(String field, String value, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int x = 0;
		
		try{
			
			con = ds.getConnection();
			
			if(field.equals("all")) {
				if(deptno != 0) { 
				sql = " select count(*) "
					+ " from board_dept "
					+ " where(board_subject like ? or board_name like ?) "
					+ " and board_notice = ? "
					+ " and board_deptno = ?";
				
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, "%"+value+"%"); 
				 pstmt.setString(2, "%"+value+"%");
				 pstmt.setInt(3, 1); // 게시글만 count함
				 pstmt.setInt(4, deptno);
				 rs = pstmt.executeQuery();
				 
				} else if(deptno == 0) {
					
					sql = " select count(*) "
							+ " from board_dept "
							+ " where(board_subject like ? or board_name like ?) "
							+ " and board_notice = ? ";
						
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+value+"%"); 
					pstmt.setString(2, "%"+value+"%");
					pstmt.setInt(3, 1); // 게시글만 count함
					rs = pstmt.executeQuery();
				}
				
			} else if(!field.equals("all")){
				if(deptno != 0) {
				sql = "select count(*) from board_dept "
					+ "where " + field + " like ? "
					+ "and board_deptno = ?";
				
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, "%"+value+"%"); 
				 pstmt.setInt(2, deptno);
				 rs = pstmt.executeQuery();
				 
				} else if(deptno == 0) {
					sql = "select count(*) from board_dept "
							+ "where " + field + " like ? ";
						
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+value+"%"); 
					rs = pstmt.executeQuery();
				}
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

	public List<DeptBoard> getBoardList(String field, String value, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
				if(deptno != 0) {
				  sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 0"
						+ "     and board_deptno = ? "
						+ " 	and(BOARD_SUBJECT like ? or BOARD_NAME like ?) "
						+ "     and rownum <= 3) "
						+ " where rnum >= 1 and rnum <= 3";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, deptno);
					pstmt.setString(2, "%"+value+"%");
					pstmt.setString(3, "%"+value+"%");
					rs = pstmt.executeQuery();
					
				} else if(deptno == 0) {
					sql = "select * "
							+ "from(select rownum rnum, j.* "
							+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
							+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
							+ "				 	           		      	   FROM COMMENT_DEPT "
							+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
							+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
							+ " 	     ORDER BY BOARD_RE_REF DESC, "
							+ "	         BOARD_RE_SEQ ASC) j "
							+ "		where board_notice = 0"
							+ "     and board_deptno = 10 or board_deptno = 20 "
							+ " 	and(BOARD_SUBJECT like ? or BOARD_NAME like ?) "
							+ "     and rownum <= 3) "
							+ " where rnum >= 1 and rnum <= 3";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+value+"%");
						pstmt.setString(2, "%"+value+"%");
						rs = pstmt.executeQuery();
				}
			    
			} else if(!field.equals("all")){
				if(deptno != 0) {
				   sql = "select * "
					   + "from(select rownum rnum, j.* "
					   + "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
					   + "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
					   + "				 	           		      	   FROM COMMENT_DEPT "
				       + "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
					   + "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
					   + " 	     ORDER BY BOARD_RE_REF DESC, "
					   + "	         BOARD_RE_SEQ ASC) j "
					   + "		where board_notice = 0"
					   + "      and board_deptno = ? "
					   + "		and " + field + " like ? "
					   + "     and rownum <= 3) "
					   + " where rnum >= 1 and rnum <= 3";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, deptno);
					pstmt.setString(2, "%"+value+"%");
					rs = pstmt.executeQuery();
					
				}else if(deptno == 0) {
					 sql = "select * "
							   + "from(select rownum rnum, j.* "
							   + "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
							   + "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
							   + "				 	           		      	   FROM COMMENT_DEPT "
						       + "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
							   + "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
							   + " 	     ORDER BY BOARD_RE_REF DESC, "
							   + "	         BOARD_RE_SEQ ASC) j "
							   + "		where board_notice = 0"
							   + "      and board_deptno = 10 or board_deptno = 20 "
							   + "		and " + field + " like ? "
							   + "     and rownum <= 3) "
							   + " where rnum >= 1 and rnum <= 3";
							
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+value+"%");
							rs = pstmt.executeQuery();
				}
			   
			}
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
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
				board.setBoard_deptno(rs.getInt("board_deptno"));
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

	public List<DeptBoard> getBoardList(String field, String value, int page, int limit, int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		int startrow = (page - 1) *limit + 1; 
		int endrow = startrow + limit -1;
		
		try {
			
			con = ds.getConnection(); 
			
			if(field.equals("all")) {
			  if(deptno != 0) {
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ " 	and(board_subject like ? or board_name like ?)"
						+ "	    and board_deptno = ? "
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setString(2, "%"+value+"%");
				pstmt.setInt(3, deptno);
				pstmt.setInt(4, endrow);
				pstmt.setInt(5, startrow);
				pstmt.setInt(6, endrow);
				rs = pstmt.executeQuery();
				
				
			  } else if(deptno == 0 ) {
				  sql = "select * "
							+ "from(select rownum rnum, j.* "
							+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
							+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
							+ "				 	           		      	   FROM COMMENT_DEPT "
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
			  }
				
			} else if(!field.equals("all")){
			   if(deptno != 0) {
				sql = "select * "
						+ "from(select rownum rnum, j.* "
						+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
						+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
						+ "				 	           		      	   FROM COMMENT_DEPT "
						+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
						+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
						+ " 	     ORDER BY BOARD_RE_REF DESC, "
						+ "	         BOARD_RE_SEQ ASC) j "
						+ "		where board_notice = 1"
						+ "		and " + field + " like ? "
						+ "		and board_deptno = ? "
						+ "     and rownum <= ?) "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+value+"%");
				pstmt.setInt(2, deptno);
				pstmt.setInt(3, endrow);
				pstmt.setInt(4, startrow);
				pstmt.setInt(5, endrow);
				rs = pstmt.executeQuery();
				
			   } else if(deptno == 0) {
				   sql = "select * "
							+ "from(select rownum rnum, j.* "
							+ "     from(SELECT BOARD_DEPT.*, NVL(CNT,0)CNT "
							+ "     	 FROM BOARD_DEPT LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT "
							+ "				 	           		      	   FROM COMMENT_DEPT "
							+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM) "
							+ "	      	 ON BOARD_NUM = COMMENT_BOARD_NUM "
							+ " 	     ORDER BY BOARD_RE_REF DESC, "
							+ "	         BOARD_RE_SEQ ASC) j "
							+ "		where board_notice = 1"
							+ "		and " + field + " like ? "
							+ "     and rownum <= ?) "
							+ " where rnum >= ? and rnum <= ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+value+"%");
					pstmt.setInt(2, endrow);
					pstmt.setInt(3, startrow);
					pstmt.setInt(4, endrow);
					rs = pstmt.executeQuery();
			   }
			}
			
			while (rs.next()) {
				
				DeptBoard board = new DeptBoard();
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
				board.setBoard_deptno(rs.getInt("board_deptno"));
				list.add(board); 
				
				System.out.println("list board = " + list.size());
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

	public int boardInsert(DeptBoard board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			con = ds.getConnection();
			
			String max_sql = "(select nvl(max(board_num),0)+1 from board_dept)";
			
			String sql = "insert into board_dept "
					   + "(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, "
					   + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, "
					   + " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
					   + " BOARD_DATE, BOARD_NOTICE, BOARD_DEPTNO) "
					   + " values(" + max_sql +", ?, ?, "
					   + " 		  ?, ?, " + max_sql + ", " 
					   + "		  ?, ?, ?, sysdate, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, board.getBoard_notice());
			pstmt.setInt(9, board.getBoard_deptno());
			
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

	public String getdname(int deptno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dname = null;
		
		try {
			con = ds.getConnection();
			
			String sql = "select dname from dept where deptno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dname = rs.getString(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getdname() 에러 : " + e);
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
		return dname;
	}

	public void setReadCountUpdate(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "update board_dept "
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

	public DeptBoard getDetail(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DeptBoard board = null; 
		
		try {
			
			con = ds.getConnection();
			
			String sql = "select * from board_dept where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new DeptBoard();
				
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
				board.setBoard_notice(rs.getInt("board_deptno"));
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

	public int boardModify(DeptBoard board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; 
		
		try {
			
			con= ds.getConnection();
			
			String sql = "update board_dept "
					   + "set board_subject=?, board_content=?, board_file=?, board_notice =? "
					   + "where board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_subject());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setString(3, board.getBoard_file());
			pstmt.setInt(4, board.getBoard_notice());
			pstmt.setInt(5, board.getBoard_num());
			
			result = pstmt.executeUpdate();
			System.out.println("dao 수정 result : " + result);
			
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

	public ArrayList<DeptBoard> getNextPrevNum(int num, int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
			String sql = "SELECT b.* FROM( "
					+ "SELECT "
					+ "    Board_num, board_deptno,"
					+ "    LAG(Board_num,1,-1) OVER(ORDER BY Board_num ASC) AS board_prev_num,"
					+ "    LEAD(Board_num,1,-1) OVER(ORDER BY Board_num ASC) AS board_next_num "
					+ "FROM BOARD_dept where BOARD_NOTICE = 1 and BOARD_RE_LEV = 0"
					+ ") b "
					+ "WHERE b.Board_num = ? "
					+ "and b.board_deptno = ?" ;


		ArrayList<DeptBoard> list = new ArrayList<DeptBoard>();

			
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num); 
			pstmt.setInt(2, deptno);
	
			rs= pstmt.executeQuery();
			
				
			while (rs.next()) {
				DeptBoard b = new DeptBoard();
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

	public int boardReply(DeptBoard board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		
		String board_max_sql = "select max(board_num)+1 from board_dept";
		
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
			
			String sql = "update board_dept "
					   + "set board_re_seq = board_re_seq + 1 "
					   + "where board_deptno = ? "
					   + "and board_re_ref = ? "
					   + "and board_re_seq > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_deptno());
			pstmt.setInt(2, re_ref);
			pstmt.setInt(3, re_seq);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			re_seq = re_seq + 1;		
			re_lev = re_lev + 1;
			
			sql = "insert into board_dept "
				+ "(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, "
				+ " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, "
				+ " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, "
				+ " BOARD_DATE, BOARD_NOTICE, BOARD_DEPTNO) "
				+ "values(" + num + ", ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?) "; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, "");
			pstmt.setInt(5, re_ref);
			pstmt.setInt(6, re_lev);
			pstmt.setInt(7, re_seq);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 1); 
			pstmt.setInt(10, board.getBoard_deptno());
			
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
							  +"from board_dept "
							  +"where BOARD_NUM =?";
			
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			String board_delete_sql = "delete from board_dept "
									+ "where board_re_ref = ? "
									+ "and board_re_lev >=? "
									+ "and board_re_seq>=? "
									+ "and board_re_seq <=(  "
									+ " 					nvl((select min(Board_re_seq) -1 "
									+ "					    from board_dept "
									+ "					    where board_re_ref = ? "
									+ "					    and board_re_lev = ? "
									+ "					    and board_re_seq >?),"
									+ "					    (select max(Board_re_seq)"
									+ "			  		    from board_dept"
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
}
