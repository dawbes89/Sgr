package sgr.app.api.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Service for handling session;
 *
 * @author leonzio
 */
public interface SessionService
{

   HttpSession getSession();

   HttpServletRequest getRequest();

   <T> T getAttributeValue(String attributeKey) throws NullPointerException;

   <T> void setAttributeValue(String attributeKey, T attributeValue);
}
