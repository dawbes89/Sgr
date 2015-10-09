package sgr.api.announcement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "announcement")
public class Announcement
{

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "title", length = 100)
   private String title;

   @Column(name = "date")
   private Date date;

   @Column(name = "message")
   private String message;

   public Announcement()
   {}

   public Announcement(Long id, String title, Date date, String message)
   {
      this.id = id;
      this.title = title;
      this.date = date;
      this.message = message;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

}
