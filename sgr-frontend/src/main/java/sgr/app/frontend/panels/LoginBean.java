package sgr.app.frontend.panels;

import java.io.Serializable;

/**
 * Bean used for login.
 *
 * @author leonzio
 */
public class LoginBean implements Serializable
{

   private static final long serialVersionUID = 1863958145104759923L;

   private String userName;
   private String password;

   public String getUserName()
   {
      return userName;
   }

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

}
