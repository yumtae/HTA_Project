package passtoss.board.free.action;

public class ActionForward {

	private boolean redirect = false;
	private String path = null;
	
	public ActionForward() {
		
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect(boolean b) {
		redirect=b;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String string) {
		path = string;
	}
}
