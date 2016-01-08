package sgr.app.api.teachingStuff;

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
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.person.Person;

/**
 * @author dawbes
 */
@Entity
@Table(name = "teaching_stuff")
public class TeachingStuff implements Serializable
{

   private static final long serialVersionUID = 1452686849131351842L;

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "person_id", nullable = false, unique = true)
   private Person person;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "account_id", nullable = false, unique = true)
   private Account account;

   @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
   @JoinColumn(name = "preceptor_class_id")
   private ClassGroup preceptorClass;

   @Column(name = "school_subject", length = 50)
   private String schoolSubject;

   @Column(name = "academic_title", length = 25)
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

   public String getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(String schoolSubject)
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
}
