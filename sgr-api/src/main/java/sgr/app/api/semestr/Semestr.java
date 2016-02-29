package sgr.app.api.semestr;

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
 * @author leonzio
 */
@Entity
@Table(name = "semestr")
public class Semestr implements Serializable
{

   private static final long serialVersionUID = -988347663214142873L;

   public static final String PROPERTY_SCHOOL_YEAR = "schoolYear";
   public static final String PROPERTY_SEMESTR_NUMBER = "semestrNumber";

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "semestr_number", nullable = false)
   private Integer semestrNumber;

   @Column(name = "school_year", nullable = false, updatable = false, length = 9)
   private String schoolYear;

   @Column(name = "date_from", nullable = false)
   @Type(type = "date")
   private Date from;

   @Column(name = "date_to", nullable = false)
   @Type(type = "date")
   private Date to;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Integer getSemestrNumber()
   {
      return semestrNumber;
   }

   public void setSemestrNumber(Integer semestrNumber)
   {
      this.semestrNumber = semestrNumber;
   }

   public String getSchoolYear()
   {
      return schoolYear;
   }

   public void setSchoolYear(String schoolYear)
   {
      this.schoolYear = schoolYear;
   }

   public Date getFrom()
   {
      return from;
   }

   public void setFrom(Date from)
   {
      this.from = from;
   }

   public Date getTo()
   {
      return to;
   }

   public void setTo(Date to)
   {
      this.to = to;
   }

}
