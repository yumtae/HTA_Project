package passtoss.member.db;

public class Member {
	private String id;
	private String password;
	private String name;
	private String jumin;
	private int deptno;
	private int post;
	private String email;
	private String phone;
	private String address;
	private int authority;
	private String profileImg;
	private String joindate;
	private String PROFILEIMG;

	
	public String getPROFILEIMG() {
		return PROFILEIMG;
	}

	public void setPROFILEIMG(String pROFILEIMG) {
		PROFILEIMG = pROFILEIMG;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getJoindate() {
		return joindate.substring(0, 10);
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

}
