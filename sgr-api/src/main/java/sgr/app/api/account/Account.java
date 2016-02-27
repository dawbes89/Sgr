package sgr.app.api.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Account entity.
 *
 * @author dawbes89
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
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "user_name", length = 20, unique = true, nullable = false, updatable = false)
   private String userName;

   @Column(name = "password", length = 74, nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(name = "type", length = 15, nullable = false, updatable = false)
   private AccountType type;

   @Column(name = "created", nullable = false, updatable = false)
   private Date created;

   @Column(name = "valid_to", nullable = true, updatable = true)
   private Date validTo;

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

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public Date getValidTo()
   {
      return validTo;
   }

   public void setValidTo(Date validTo)
   {
      this.validTo = validTo;
   }

}
