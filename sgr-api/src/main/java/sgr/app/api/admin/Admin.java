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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.person.Person;
import sgr.app.api.person.PersonName;

/**
 * Entity representing administrators.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "admin",
      uniqueConstraints = {
            @UniqueConstraint(columnNames = { "person_id" }, name = "admin_person_id_uk"),
            @UniqueConstraint(columnNames = { "account_id" }, name = "admin_account_id_uk") })
public class Admin implements PersonName, AccountEntity, Serializable
{

   private static final long serialVersionUID = -3977084256453665930L;

   public static final String PROPERTY_ID = "id";

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id", nullable = false, referencedColumnName = "id",
         foreignKey = @ForeignKey(name = "admin_person_id_fk") )
   private Person person = new Person();

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id",
         foreignKey = @ForeignKey(name = "admin_account_id_fk") )
   private Account account = new Account();

   @Column(name = "is_superuser", nullable = false)
   private boolean superuser = false;

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

   public boolean isSuperuser()
   {
      return superuser;
   }

   public void setSuperuser(boolean superuser)
   {
      this.superuser = superuser;
   }

   @Transient
   @Override
   public String getFullName()
   {
      if (person == null)
      {
         return null;
      }
      return String.format("%s %s", person.getFirstName(), person.getLastName());
   }

}
