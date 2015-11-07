package sgr.app.api.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dawbes
 */
@Entity
@Table(name = "account")
public class Account implements Serializable
{
   private static final long serialVersionUID = 5425777781237535276L;

   @Id
   @Column(name = "account_id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "userName", length = 20)
   private String userName;

   @Column(name = "password", length = 74)
   private String password;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

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
