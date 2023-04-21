package passtoss.board.dept.db;

public class DeptBoard {

	private int board_num;
	private String board_name;
	private String board_subject;
	private String board_content;
	private String board_file;
	private int board_re_ref;
	private int board_re_lev;
	private int board_re_seq;
	private int board_readcount;
	private String board_date;
	private int board_notice;
	private int board_deptno;
	private int cnt;
	
	private int board_next_num;
	private int board_prev_num;
	
	public int getBoard_next_num() {
		return board_next_num;
	}
	public void setBoard_next_num(int board_next_num) {
		this.board_next_num = board_next_num;
	}
	public int getBoard_prev_num() {
		return board_prev_num;
	}
	public void setBoard_prev_num(int board_prev_num) {
		this.board_prev_num = board_prev_num;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	public int getBoard_re_ref() {
		return board_re_ref;
	}
	public void setBoard_re_ref(int board_re_ref) {
		this.board_re_ref = board_re_ref;
	}
	public int getBoard_re_lev() {
		return board_re_lev;
	}
	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}
	public int getBoard_re_seq() {
		return board_re_seq;
	}
	public void setBoard_re_seq(int board_re_seq) {
		this.board_re_seq = board_re_seq;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date.substring(0,10);
	}
	public int getBoard_notice() {
		return board_notice;
	}
	public void setBoard_notice(int board_notice) {
		this.board_notice = board_notice;
	}
	public int getBoard_deptno() {
		return board_deptno;
	}
	public void setBoard_deptno(int board_deptno) {
		this.board_deptno = board_deptno;
	}
	
	
}
