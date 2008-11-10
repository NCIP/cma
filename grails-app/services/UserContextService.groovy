import org.springframework.web.context.request.RequestContextHolder
import javax.servlet.http.HttpSession

class UserContextService {

    boolean transactional = true

    def serviceMethod() {

    }
    
    def isLoggedIn()	{
    	def webRequest= RequestContextHolder.currentRequestAttributes()  
    	def session = webRequest.session
    	
    	if(session.userId != null)	{
    		return true;
    	}
    	else	{
    		return false;
    	}
    }
    private HttpSession getSession() {
    	def webRequest= RequestContextHolder.currentRequestAttributes()  
    	def session = webRequest.session
    	return session
    }
}
