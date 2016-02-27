package sgr.app.api.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Entity representing personal data.
 *
 * @author leonzio
 */
@Entity
@Table(name = "person")
public class Person implements Serializable
{

   private static final long serialVersionUID = 1576074218152179038L;

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "first_name", nullable = false, length = 20)
   private String firstName;

   @Column(name = "last_name", nullable = false, length = 30)
   private String lastName;

   @Column(name = "birth_date", nullable = false)
   @Type(type = "date")
   private Date birthDate;

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

   public Date getBirthDate()
   {
      return birthDate;
   }

   public void setBirthDate(Date birthDate)
   {
      this.birthDate = birthDate;
   }
}
