package passtoss.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonObject;

import passtoss.admin.board.Board;

public class MemberDAO {

	private DataSource ds;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}

	public int getListCount(int authority) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listcount = 0;

		try {
			conn = ds.getConnection();

			String sql = "select count(*) from member "
						+ "where authority = ? "
						+ "order by joindate desc";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authority);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listcount = rs.getInt(1);
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
		return listcount;
	}

	public List<Member> getMemberList(int page, int limit, int authority) {// 회원가입 정보 조회
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();

			String sql = "select * "
						+ "from (select m.*, rownum r "
						+ "		 from (select * from member "
						+ "		 	   where authority = ? "
						+ "			   order by joindate desc) m "	//가장 최근에 가입한 순으로 정렬
						+ "		 where rownum <= ? "
						+ "		) "
						+ "where r between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(1, authority);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));
				m.setName(rs.getString("name"));
				m.setJumin("jumin");
				m.setDeptno(rs.getInt("deptno"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setAuthority(rs.getInt("authority"));
				m.setProfileImg(rs.getString("profileImg"));
				m.setJoindate(rs.getString("joindate"));
				list.add(m);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getMemberList() 에러: " + se);
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

	public int getListCount(String field, String value, int authority) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from member "
					   + "where authority = ? "
					   + "and " + field + " like ? "
					   + "order by joindate desc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authority);
			pstmt.setString(2, "%" + value + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listcount = rs.getInt(1);
			}
		} catch (Exception se) {
			se.printStackTrace();
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
		return listcount;
	}

	public List<Member> getMemberList(String field, String value, int page, int limit, int authority) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();

			String sql = "select * "
						+ "from (select m.*, rownum r "
						+ "		 from (select * from member "
						+ "		 	   where authority = ?"
						+ "			   and " + field + " like ? "
						+ "			   order by joindate desc) m "
						+ "		 where rownum <= ? "
						+ "		) "
						+ "where r between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, authority);
			pstmt.setString(2, "%" + value + "%");
			
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(3, endrow);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));
				m.setName(rs.getString("name"));
				m.setJumin("jumin");
				m.setDeptno(rs.getInt("deptno"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setAuthority(rs.getInt("authority"));
				m.setProfileImg(rs.getString("profileImg"));
				m.setJoindate(rs.getString("joindate"));
				list.add(m);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getMemberList() 에러: " + se);
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

	public int authorize(String[] id, int authority) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int count = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			System.out.println("id.length="+id.length);
			for (int i = 0; i < id.length; i++) {
				String sql = "update member " 
							+ "set authority = ? "
							+ "where id = ?";
				pstmt = conn.prepareStatement(sql);				
				pstmt.setInt(1, authority);
				pstmt.setString(2, id[i]);
				result = pstmt.executeUpdate();
				pstmt.close();
				
				if (result == 1)
					count++;
				if (i == id.length-1) {
					if (count != id.length) {
						conn.rollback();
						System.out.println("가입승인 중 문제가 발생했습니다.");
					} else {
						conn.commit();
						System.out.println("commit 됨");
					}
				}
				
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("authorize() 에러: " + se);
		} finally {
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
		return count;
	}

	public JsonObject member_info(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JsonObject obj=null;
		try {
			conn = ds.getConnection();

			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				obj=new JsonObject();
				obj.addProperty("id", id);
				obj.addProperty("password", rs.getString("password"));
				obj.addProperty("name", rs.getString("name"));
				obj.addProperty("jumin", rs.getString("jumin"));
				obj.addProperty("deptno", rs.getString("deptno"));
				obj.addProperty("email", rs.getString("email"));
				obj.addProperty("phone", rs.getString("phone"));
				obj.addProperty("address", rs.getString("address"));
				obj.addProperty("authority", rs.getString("authority"));
				obj.addProperty("profileImg", rs.getString("profileImg"));
				obj.addProperty("joindate", rs.getString("joindate").substring(0, 10));
			}

		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("member_info() 에러: " + se);
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
		return obj;
	}

	public int isId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//select만 필요
		ResultSet rs = null;

		int result = -1; // DB에 해당 id가 없습니다
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
				
			while (rs.next()) {
				result = 0 ; // DB에 해당 id가 있습니다
			}
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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

		return result;
	}

	public int insert(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {
			
			conn = ds.getConnection();
			
			String sql = "insert into member (id,password,name,jumin,deptno,email,phone,address,AUTHORITY,post,PROFILEIMG)"
					+ " values ( ? , ?, ?, ? ,? ,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getJumin());
			pstmt.setInt(5, m.getDeptno());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setInt(9, 0);
			pstmt.setInt(10, m.getPost());
			pstmt.setString(11, m.getProfileImg());
	
			result = pstmt.executeUpdate();
			
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("아이디 중복 에러입니다" + e);
		
		}catch(Exception se){
			System.out.println(se.getMessage());
			
		}finally {

			
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
		return result;
	}

	public int isId(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = -1; 
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				if(rs.getString(2).equals(pass)) {
					result = 1 ; 
				}else {
					result = 0 ; 
				}

				
			}
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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

		return result;

	}


	public int delete(String[] id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int count = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			System.out.println("id.length="+id.length);
			for (int i = 0; i < id.length; i++) {
				String sql = "delete member "
							+ "where id = ?";
				pstmt = conn.prepareStatement(sql);	
				pstmt.setString(1, id[i]);
				result = pstmt.executeUpdate();
				pstmt.close();
				
				if (result == 1)
					count++;
				if (i == id.length-1) {
					if (count != id.length) {
						conn.rollback();
						System.out.println("회원삭제 중 문제가 발생했습니다.");
					} else {
						conn.commit();
						System.out.println("commit 됨");
					}
				}
				
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("delete() 에러: " + se);
		} finally {
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
		return count;
  }
	public String getprofileimg(String id) {
		String img_src = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			conn = ds.getConnection();
			
			String sql = "select PROFILEIMG from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				img_src = rs.getString(1);
			}
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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

		return img_src;
	}

	public Member memberinfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		Member m =  null;
 		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,id);
			rs= pstmt.executeQuery();
			
				
			if (rs.next()) {
				m = new Member();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setDeptno(rs.getInt("DEPTNO"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setPost(rs.getInt("post"));
				m.setProfileImg(rs.getString("PROFILEIMG"));
				System.out.println(rs.getString("PROFILEIMG"));
				
			}
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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

		return m;
	}

	public int updateMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {
			
			conn = ds.getConnection();
			
			String sql = "update member set name=? , phone=?, email=?,post=?,address=?,PROFILEIMG=? where id=? ";
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setInt(4, m.getPost());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getProfileImg());
			pstmt.setString(7, m.getId());
		
	
			result = pstmt.executeUpdate();
			
			
		}catch(Exception se){
			System.out.println(se.getMessage());
			
		}finally {

			
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
		return result;
	}
  
	public int getBoardListCount(String table, String id) { //마이페이지
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			
			String sql = "select count(*) from " + table 
					+ " where board_name = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			for (int i = 1; rs.next(); i++) {
				x += rs.getInt(i);
				System.out.println("x = " + x);
			}
		} catch (Exception se) {
			se.printStackTrace();
			System.out.println("getBoardListCount() 에러: " + se);
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

	public int getBoardListCount(String field, String word, String table, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			
			
			String sql = "select count(*) from " + table 
						+ " where " + field + " like ? "
						+ "and board_name = ?";						 

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");
			pstmt.setString(2, id);
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

	public List<Board> getfreeBoardList(int page, int limit, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 
		String sql = "select *"
				+ "	from(select rownum rnum, j.*"
				+ "      from(SELECT board_free.*, NVL(CNT,0)CNT"
				+ "     	  FROM board_free LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       	   FROM comment_free"
				+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM)"
				+ "	      	  ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      ORDER BY BOARD_RE_REF DESC,"
				+ "	      	  BOARD_RE_SEQ ASC) j"
				+ "		 where board_name = ?"
				+ "      and rownum <= ?)"
				+ " where rnum between ? and ?";	

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_notice(rs.getInt("board_notice"));
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

	public List<Board> getfreeBoardList(String field, String word, int page, int limit, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select *"
				+ "	from(select rownum rnum, j.*"
				+ "      from(SELECT board_free.*, NVL(CNT,0)CNT"
				+ "     	  FROM board_free LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       	   FROM comment_free"
				+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM)"
				+ "	      	  ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      ORDER BY BOARD_RE_REF DESC,"
				+ "	      	  BOARD_RE_SEQ ASC) j"
				+ "		 where board_name = ?"
				+ "		 and "+ field + " like ?"
				+ "      and rownum <= ?)"
				+ " where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%" + word + "%");
			pstmt.setInt(3, endrow);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_notice(rs.getInt("board_notice"));
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

	public List<Board> getdeptBoardList(int page, int limit, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "select *"
				+ "	from(select rownum rnum, j.*"
				+ "      from(SELECT board_dept.*, NVL(CNT,0)CNT"
				+ "     	  FROM board_dept LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT"
				+ "				 	           		       	   FROM comment_dept"
				+ "				 	          		       	   GROUP BY COMMENT_BOARD_NUM)"
				+ "	      	  ON BOARD_NUM = COMMENT_BOARD_NUM"
				+ " 	      ORDER BY BOARD_RE_REF DESC,"
				+ "	      	  BOARD_RE_SEQ ASC) j"
				+ "		 where board_name = ?"
				+ "      and rownum <= ?)"
				+ " where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_notice(rs.getInt("board_notice"));
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

	public List<Board> getdeptBoardList(String field, String word, int page, int limit, String id) {
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
				+ "	 				 where board_name = ?"
				+ "					 and "+ field + " like ?"
				+ "     			 and rownum <= ?)"
				+ " 			 where rnum between ? and ?";

		List<Board> list = new ArrayList<Board>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%" + word + "%");
			pstmt.setInt(3, endrow);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_notice(rs.getInt("board_notice"));
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
  
	public int getdeptno(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int deptno = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select deptno from member where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				deptno = rs.getInt(1);
			}
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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
		return deptno;
	}

	public boolean boardDelete(String[] select, String board_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int count=0;
		int result=0;
		System.out.println("select = "+select[0]+"게시판 이름="+board_name);
		String select_sql = "select  board_re_ref, board_re_lev, board_re_seq "
						  + "from " + board_name
						  + " where board_num = ?";
		
		String delete_sql = "delete from " + board_name
				+ " where board_re_ref = ? "
				+ "and board_re_lev >=? "
				+ "and board_re_seq>=? "
				+ "and board_re_seq <=(  "
				+ " 					nvl((select min(Board_re_seq) -1 "
				+ "					    from " + board_name
				+ "					    where board_re_ref = ? "
				+ "					    and board_re_lev = ? "
				+ "					    and board_re_seq >?),"
				+ "					    (select max(Board_re_seq)"
				+ "			  		    from " + board_name
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

	public int getauthority(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int authority = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select authority from member where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				authority = rs.getInt(1);
			}
		}catch(Exception se){
			System.out.println(se.getMessage());
			
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
		return authority;
	}
}
