package passtoss.Business_status;

public class ActionForward {

	private String path=null;
	private boolean redirect = false;
	
	
	
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
	

	
	

}
