package sgr.app.api.admin;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.person.Person;

/**
 * Entity representing administrators.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "admin")
public class Admin implements AccountEntity, Serializable
{

   private static final long serialVersionUID = -3977084256453665930L;

   public static final String PROPERTY_ID = "id";

   @Id
   @Column(name = "admin_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id", nullable = false, unique = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "admin_person_id") )
   private Person person = new Person();

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "account_id", nullable = false, unique = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "admin_account_id") )
   private Account account = new Account();

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

   @Override
   public Account getAccount()
   {
      return account;
   }

   public void setAccount(Account account)
   {
      this.account = account;
   }

}
