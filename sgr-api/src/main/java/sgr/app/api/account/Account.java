package sgr.app.api.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

   public static final String PROPERTY_ID = "id";
   public static final String PROPERTY_USER_NAME = "userName";
   public static final String PROPERTY_PASSWORD = "password";

   @Id
   @Column(name = "account_id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "userName", length = 20, unique = true, nullable = false, updatable = false)
   private String userName;

   @Column(name = "password", length = 74, nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(name = "type", nullable = false)
   private AccountType type;

   public AccountType getType()
   {
      return type;
   }

   public void setType(AccountType type)
   {
      this.type = type;
   }

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
