package sgr.app.api.student;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.person.Person;
import sgr.app.api.person.PersonName;

/**
 * @author leonzio
 */
@Entity
@Table(name = "student")
public class Student implements PersonName, AccountEntity, Serializable
{

   private static final long serialVersionUID = -2795415011971757723L;

   public static final String PROPERTY_ID = "id";
   public static final String PROPERTY_CLASS_GROUP = "classGroup";
   public static final String PROPERTY_PERSON = "person";

   @Id
   @Column(name = "student_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id", nullable = false, unique = true)
   private Person person;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "account_id", nullable = false, unique = true)
   private Account account;

   @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
         CascadeType.REFRESH }, fetch = FetchType.EAGER)
   @JoinColumn(name = "class_group_id", nullable = false)
   private ClassGroup classGroup;

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

   public ClassGroup getClassGroup()
   {
      return classGroup;
   }

   public void setClassGroup(ClassGroup classGroup)
   {
      this.classGroup = classGroup;
   }

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
