package sgr.app.api.admin;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sgr.app.api.account.Account;
import sgr.app.api.person.Person;

/**
 * @author dawbes89
 */
@Entity
@Table(name = "admin")
public class Admin implements Serializable
{

   private static final long serialVersionUID = -3977084256453665930L;

   public static final String PROPERTY_ID = "id";

   @Id
   @Column(name = "admin_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id", nullable = false, unique = true)
   private Person person;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "account_id", nullable = false, unique = true)
   private Account account;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Person getPerson()
   {
      return person;
   }

   public void setPerson(Person person)
   {
      this.person = person;
   }

   public Account getAccount()
   {
      return account;
   }

   public void setAccount(Account account)
   {
      this.account = account;
   }

}
