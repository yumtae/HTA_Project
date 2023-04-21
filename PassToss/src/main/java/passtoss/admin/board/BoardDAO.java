package passtoss.admin.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource ds;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
//수정 확인
	public int getListCount(String table) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;// DB에 해당 id가 없습니다.
		try {
			conn = ds.getConnection();

			String sql = "select count(*) from " + table + " where board_notice = 1";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			for (int i = 1; rs.next(); i++) {
				x += rs.getInt(i);
				System.out.println("x = " + x);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getListCount() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return x;
	}
	
	public int getListCount(String field, String word, String table) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;// DB에 해당 id가 없습니다.
		try {
			conn = ds.getConnection();

			String sql = "select count(*) from " + table 
						+ " where " + field + " like ?"
						+ " and board_notice = 1";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");
			rs = pstmt.executeQuery();
			for (int i = 1; rs.next(); i++) {
				x += rs.getInt(i);
				System.out.println("x = " + x);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getListCount() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return x;
	}

	public List<Board> getfreeBoardList(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String board_list_sql = "select *"
				+ "				 from(select rownum rnum, j.*"
				+ "     			  from(SELECT board_free.*, NVL(CNT,0)CNT"
				+ "     	  			   FROM board_free LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       					FROM comment_free"
				+ "				 	          		       					GROUP BY COMMENT_BOARD_NUM)"
				+ "	      				   ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      			   ORDER BY BOARD_RE_REF DESC,"
				+ "	      				   BOARD_RE_SEQ ASC) j"
				+ "	 				 where board_notice = 1"
				+ "     			 and rownum <= ?)"
				+ " 			 where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getfreeBoardlist() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public List<Board> getfreeBoardList(String field, String word, int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String board_list_sql = "select *"
				+ "				 from(select rownum rnum, j.*"
				+ "     			  from(SELECT board_free.*, NVL(CNT,0)CNT"
				+ "     	  			   FROM board_free LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       					FROM comment_free"
				+ "				 	          		       					GROUP BY COMMENT_BOARD_NUM)"
				+ "	      				   ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      			   ORDER BY BOARD_RE_REF DESC,"
				+ "	      				   BOARD_RE_SEQ ASC) j"
				+ "	 				 where board_notice = 1"
				+ "					 and "+ field + " like ?"
				+ "     			 and rownum <= ?)"
				+ " 			 where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setString(1, "%" + word + "%");
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getfreeBoardlist() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public boolean boardDelete(String[] select, String string) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int count=0;
		int result=0;
		String select_sql = "select  board_re_ref, board_re_lev, board_re_seq "
						  + "from " + string
						  + " where board_num = ?";
		
		String delete_sql = "delete from " + string
				+ " where board_re_ref = ? "
				+ "and board_re_lev >=? "
				+ "and board_re_seq>=? "
				+ "and board_re_seq <=(  "
				+ " 					nvl((select min(Board_re_seq) -1 "
				+ "					    from " + string
				+ "					    where board_re_ref = ? "
				+ "					    and board_re_lev = ? "
				+ "					    and board_re_seq >?),"
				+ "					    (select max(Board_re_seq)"
				+ "			  		    from " + string
				+ "			 		    where board_re_ref = ?)))";
		boolean result_check = false;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			System.out.println("select.length = " + select.length);
			for (int i = 0; i < select.length; i++) {
				pstmt = conn.prepareStatement(select_sql);
				pstmt.setInt(1, Integer.parseInt(select[i]));
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					pstmt2 = conn.prepareStatement(delete_sql);
					pstmt2.setInt(1, rs.getInt("board_re_ref"));
					pstmt2.setInt(2, rs.getInt("board_re_lev"));
					pstmt2.setInt(3, rs.getInt("board_re_seq"));
					pstmt2.setInt(4, rs.getInt("board_re_ref"));
					pstmt2.setInt(5, rs.getInt("board_re_lev"));
					pstmt2.setInt(6, rs.getInt("board_re_seq"));
					pstmt2.setInt(7, rs.getInt("board_re_ref"));
					result = pstmt2.executeUpdate();
				}
				if (result != 0)
					count++;

				if (i == select.length - 1) {
					if (count != select.length) {
						conn.rollback();
						result_check = false;
						System.out.println("게시물 삭제중 오류발생");
					} else {
						conn.commit();
						System.out.println("commit 됨");
						result_check = true;
					}
				}
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("boardDelete() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {

					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt2 != null)
				try {

					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {

					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return result_check;
	}

	public List<Board> getdeptBoardList(int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String board_list_sql = "select *"
				+ "				 from(select rownum rnum, j.*"
				+ "     			  from(SELECT board_dept.*, NVL(CNT,0)CNT"
				+ "     	  			   FROM board_dept LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       					FROM comment_dept"
				+ "				 	          		       					GROUP BY COMMENT_BOARD_NUM)"
				+ "	      				   ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      			   ORDER BY BOARD_RE_REF DESC,"
				+ "	      				   BOARD_RE_SEQ ASC) j"
				+ "	 				 where board_notice = 1"
				+ "     			 and rownum <= ?)"
				+ " 			 where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_deptno(rs.getInt("board_deptno"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getdeptBoardlist() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public List<Board> getdeptBoardList(String field, String word, int page, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String board_list_sql = "select *"
				+ "				 from(select rownum rnum, j.*"
				+ "     			  from(SELECT board_dept.*, NVL(CNT,0)CNT"
				+ "     	  			   FROM board_dept LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       					FROM comment_dept"
				+ "				 	          		       					GROUP BY COMMENT_BOARD_NUM)"
				+ "	      				   ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      			   ORDER BY BOARD_RE_REF DESC,"
				+ "	      				   BOARD_RE_SEQ ASC) j"
				+ "	 				 where board_notice = 1"
				+ "					 and "+ field + " like ?"
				+ "     			 and rownum <= ?)"
				+ " 			 where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setString(1, "%" + word + "%");
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getString("board_date"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getfreeBoardlist() 에러: " + se);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}
}


