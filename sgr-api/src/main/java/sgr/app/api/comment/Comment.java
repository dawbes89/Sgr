package sgr.app.api.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sgr.app.api.student.Student;

/**
 * Entity for student comments.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable
{

   private static final long serialVersionUID = -5937580296121716355L;

   public static final String PROPERTY_DATE = "date";
   public static final String PROPERTY_STUDENT = "student";

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false, updatable = false)
   private Date date;

   @Column(name = "content", nullable = false, updatable = false)
   private String content;

   @Enumerated(EnumType.STRING)
   @Column(name = "comment_type", length = 25, nullable = false, updatable = false)
   private CommentType commentType;

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "student_id", nullable = false, updatable = false,
         referencedColumnName = "id", foreignKey = @ForeignKey(name = "comment_student_id_fk") )
   private Student student;

   @Column(name = "issuer_name", length = 100, nullable = false, updatable = false)
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

   public Student getStudent()
   {
      return student;
   }

   public void setStudent(Student student)
   {
      this.student = student;
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
