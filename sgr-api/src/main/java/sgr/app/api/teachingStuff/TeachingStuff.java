package sgr.app.api.teachingStuff;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.person.Person;
import sgr.app.api.person.PersonName;

/**
 * @author dawbes
 */
@Entity
@Table(name = "teaching_stuff")
public class TeachingStuff implements PersonName, AccountEntity, Serializable
{

   private static final long serialVersionUID = 1452686849131351842L;

   public final static String PROPERTY_ID = "id";
   public final static String PROPERTY_PRECEPTOR_CLASS = "preceptorClass";

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id", nullable = false, unique = true)
   private Person person;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "account_id", nullable = false, unique = true)
   private Account account;

   @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
         CascadeType.REFRESH }, fetch = FetchType.EAGER)
   @JoinColumn(name = "preceptor_class_id", nullable = true, unique = true)
   private ClassGroup preceptorClass;

   @Enumerated(EnumType.STRING)
   @Column(name = "school_subject", nullable = false)
   private SchoolSubject schoolSubject;

   @Column(name = "academic_title", length = 25, nullable = false)
   private String academicTitle;

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

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

   public String getAcademicTitle()
   {
      return academicTitle;
   }

   public void setAcademicTitle(String academicTitle)
   {
      this.academicTitle = academicTitle;
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

   public ClassGroup getPreceptorClass()
   {
      return preceptorClass;
   }

   public void setPreceptorClass(ClassGroup preceptorClass)
   {
      this.preceptorClass = preceptorClass;
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

   public String getTeacherFullName()
   {
      return String.format("%s %s %s", academicTitle, person.getFirstName(), person.getLastName());
   }
}
