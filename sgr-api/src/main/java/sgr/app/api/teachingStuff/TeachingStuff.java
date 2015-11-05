package sgr.app.api.teachingStuff;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sgr.api.account.Account;

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

   @Column(name = "first_name", length = 20)
   private String firstName;

   @Column(name = "last_name", length = 30)
   private String lastName;

   @Column(name = "school_subject", length = 50)
   private String schoolSubject;

   @Column(name = "academic_title", length = 25)
   private String academicTitle;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "account_id")
   private Account account;

   public TeachingStuff()
   {}

   public TeachingStuff(Long id, String firstName, String lastName, String schoolSubject,
         String academicTitle)
   {
      super();
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.schoolSubject = schoolSubject;
      this.academicTitle = academicTitle;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
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

}
