package sgr.api.teachingStuff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TeachingStuff")
public class TeachingStuff
{

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "firstName")
   private String firstName;

   @Column(name = "lastName")
   private String lastName;

   @Column(name = "schoolSubject")
   private String schoolSubject;

   @Column(name = "academicTitle")
   private String academicTitle;

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

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public TeachingStuff(Long id, String firstName, String lastName, String schoolSubject, String academicTitle)
   {
      super();
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.schoolSubject = schoolSubject;
      this.academicTitle = academicTitle;
   }

   public TeachingStuff()
   {}

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

}
