package passtoss.board.dept.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CommentdeptDAO {

	private DataSource ds;
	
	public CommentdeptDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return; 	
		}
	}

	public int commentsInsert(Commentdept co) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; 
		
		try {
			
			con = ds.getConnection();
			
			String sql = "insert into comment_dept "
					   + "values(dcom_seq.nextval, ?, ?, sysdate, ?, ?, ?, dcom_seq.nextval)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getId());
			pstmt.setString(2, co.getContent());
			pstmt.setInt(3, co.getComment_board_num());
			pstmt.setInt(4, co.getComment_re_lev());
			pstmt.setInt(5, co.getComment_re_seq());
			
			result = pstmt.executeUpdate();
			
			if(result ==1 ) {
				System.out.println("데이터 삽입 완료되었습니다.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("commentsInsert() 에러 : " + e);
		}
		finally
		{
			
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

	public int getListCount(int comment_board_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			
			con = ds.getConnection();
			
			String sql = "select count(*) from comment_dept "
					   + "where comment_board_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
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

	public JsonArray getCommentList(int comment_board_num, int state) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sort = "asc"; // 등록순 
		if(state == 2) {
			sort = "desc"; //최신순 
		}
		
		String sql = "select num, comment_dept.id, content, comment_date, comment_re_lev, "
				   + "comment_re_seq, comment_re_ref, member.profileImg "
				   + "from comment_dept join member "
				   + "on comment_dept.id = member.id "
				   + "where comment_board_num = ? "
				   + "order by comment_re_ref " + sort + ", "
				   + "		 comment_re_seq asc";
		
		JsonArray array = new JsonArray();
		
		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JsonObject object = new JsonObject();
				object.addProperty("num", rs.getInt("num"));
				object.addProperty("id", rs.getString("id"));
				object.addProperty("content", rs.getString("content"));
				object.addProperty("comment_date", rs.getString("comment_date"));
				object.addProperty("comment_re_lev", rs.getInt("comment_re_lev"));
				object.addProperty("comment_re_seq", rs.getInt("comment_re_seq"));
				object.addProperty("comment_re_ref", rs.getInt("comment_re_ref"));
				object.addProperty("profileImg", rs.getString("profileImg"));
				
				array.add(object);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getCommentList() 에러 : " + e);
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
		return array;
	}

	public int commentsUpdate(Commentdept co) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "update comment_dept "
					   + "set content = ? "
					   + "where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getContent());
			pstmt.setInt(2, co.getNum());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("댓글이 수정되었습니다.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("commentsUpdate() 에러 : " + e);
		}
		finally
		{
			
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

	public int commentsDelete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int resutl = 0;
		
		try {
			
			con = ds.getConnection();
			
			String sql ="delete comment_dept "
					   +"where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			resutl = pstmt.executeUpdate();
			
			if(resutl == 1) {
				System.out.println("데이터가 삭제 되었습니다.");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("commentsDelete() 에러 : " + e);
		}
		finally
		{
			
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
		return resutl;
	}

	public int commentsReply(Commentdept co) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			String sql ="update comment_dept "
					   +"set comment_re_seq = comment_re_seq +1 "
					   +"where comment_re_ref = ? "
					   +"and comment_re_seq > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, co.getComment_re_ref());
			pstmt.setInt(2, co.getComment_re_seq());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into comment_dept "
				+ "values(dcom_seq.nextval, ?, ?, sysdate, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getId());
			pstmt.setString(2, co.getContent());
			pstmt.setInt(3, co.getComment_board_num());
			pstmt.setInt(4, co.getComment_re_lev()+1);
			pstmt.setInt(5, co.getComment_re_seq()+1);
			pstmt.setInt(6, co.getComment_re_ref());
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("reply 삽입 완료되었습니다.");
				con.commit();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally
		{
			
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
		return result;
	}
}
