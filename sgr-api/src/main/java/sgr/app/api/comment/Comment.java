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
@Table(name = "account")
public class Comment  implements Serializable
{

   private static final long serialVersionUID = -5937580296121716355L;

   @Id
   @Column(name = "comment_id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "date")
   private Date date;

   @Column(name = "content")
   private String content;

   @Enumerated(EnumType.STRING)
   @Column(name = "commentType")
   private CommentType commentType;

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



}
