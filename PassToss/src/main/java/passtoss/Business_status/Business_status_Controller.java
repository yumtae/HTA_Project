package passtoss.Business_status;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.bs")
public class Business_status_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Business_status_Controller() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	String RequestURI = request.getRequestURI();
    	//System.out.println("RequestURI = " + RequestURI);
    	

    	String contextPath = request.getContextPath();
    	//System.out.println("contextPath = " + contextPath);

    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);
    	
    	//초기화
    	ActionForward forward = null;
    	Action action = null;
    	
    	
    	switch(command) {
	    	case "/Business_status.bs":
	    		action = new Business_Status_list();
	    		break;
	    		
	    	case "/BusinessAddAction.bs":
	    		action = new BusinessAddAction();
	    		break;
	    		
	    	case "/BusinessDeleteAction.bs":
	    		action = new BusinessDeleteAction();
	    		break;
	    	case "/BusinessUpdateAction.bs":
	    		action = new BusinessUpdateAction();
	    		break;
	    		
    		
    		
    	}
    	
    	
    	forward = action.execute(request,response);
   	 
    	if(forward !=null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		
    		
    	    }else {
    		   RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    		    dispatcher.forward(request, response);
    	    }
    	}
    	
    	
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
