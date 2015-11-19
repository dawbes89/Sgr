package sgr.app.api.classgroup;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author leonzio
 */
@Entity
@Table(name = "class_group")
public class ClassGroup implements Serializable
{

   private static final long serialVersionUID = -1359236624174757353L;

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "group_number", precision = 1)
   private Integer groupNumber;

   @Column(name = "group_name", length = 1, nullable = false)
   private String groupName;

   @Column(name = "year", nullable = false)
   private Date year;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Integer getGroupNumber()
   {
      return groupNumber;
   }

   public void setGroupNumber(Integer groupNumber)
   {
      this.groupNumber = groupNumber;
   }

   public String getGroupName()
   {
      return groupName;
   }

   public void setGroupName(String groupName)
   {
      this.groupName = groupName;
   }

   public Date getYear()
   {
      return year;
   }

   public void setYear(Date year)
   {
      this.year = year;
   }

   public String getFormattedYear()
   {
      return new SimpleDateFormat("yyyy").format(year);
   }
}
