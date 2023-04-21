package passtoss.Business_status.db;

public class Business_status_Bean {

	private int memo_seq;
	private String memo_id;
	private String memo_content;
	private String board_date;
	private String limit_date;
	private int status;
	private int priority;
	private int count = 0;
	private long diffMin = 0;

	

	
	public long getDiffMin() {
		return diffMin;
	}
	public void setDiffMin(long diffMin) {
		this.diffMin = diffMin;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getMemo_seq() {
		return memo_seq;
	}
	public void setMemo_seq(int memo_seq) {
		this.memo_seq = memo_seq;
	}
	public String getMemo_id() {
		return memo_id;
	}
	public void setMemo_id(String memo_id) {
		this.memo_id = memo_id;
	}
	public String getMemo_content() {
		return memo_content;
	}
	public void setMemo_content(String memo_content) {
		this.memo_content = memo_content;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(String limit_date) {
		this.limit_date = limit_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

	
}
