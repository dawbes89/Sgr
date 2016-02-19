package sgr.app.api.announcement;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dawbes
 */
@Entity
@Table(name = "announcement")
public class Announcement
{

   private final static SimpleDateFormat MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

   public final static String PROPERTY_DATE = "date";

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "title", length = 100)
   private String title;

   @Column(name = "date")
   private Date date;

   @Column(name = "content")
   private String content;

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

   public String getFormattedDate()
   {
      return MINUTE_FORMAT.format(date);
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getcontent()
   {
      return content;
   }

   public void setcontent(String content)
   {
      this.content = content;
   }

}
