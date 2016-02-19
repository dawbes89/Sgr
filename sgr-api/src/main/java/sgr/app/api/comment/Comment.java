package sgr.app.api.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dawbes
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable
{

   private static final long serialVersionUID = -5937580296121716355L;

   public static final String PROPERTY_DATE = "date";
   public static final String PROPERTY_STUDENT_ID = "studentId";

   @Id
   @Column(name = "comment_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date")
   private Date date;

   @Column(name = "content")
   private String content;

   @Enumerated(EnumType.STRING)
   @Column(name = "comment_type")
   private CommentType commentType;

   @Column(name = "student_id")
   private Long studentId;

   @Column(name = "issuer_name")
   private String issuerName;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getContent()
   {
      return content;
   }

   public void setContent(String content)
   {
      this.content = content;
   }

   public CommentType getCommentType()
   {
      return commentType;
   }

   public void setCommentType(CommentType commentType)
   {
      this.commentType = commentType;
   }

   public Long getStudentId()
   {
      return studentId;
   }

   public void setStudentId(Long studentId)
   {
      this.studentId = studentId;
   }

   public String getIssuerName()
   {
      return issuerName;
   }

   public void setIssuerName(String issuerName)
   {
      this.issuerName = issuerName;
   }

}
