package sgr.app.webapp.comment;

import java.util.Date;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.api.comment.CommentType;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentService;
import sgr.app.frontend.AbstractPanel;

/**
 * @author dawbes89
 */
@Controller
public class TeacherCommentPanel extends AbstractPanel<Student>
{

   private static final long serialVersionUID = -6502541271869290855L;

   @Autowired
   private CommentService commentService;

   @Autowired
   private StudentService studentService;

   private Comment comment;

   @Override
   public void init()
   {
      comment = new Comment();
      entity = new Student();
   }

   @Override
   public void onLoad()
   {
      entities = studentService.search();
   }

   public void create()
   {
      comment.setId(entity.getId());
      comment.setDate(new Date());
      commentService.create(comment);
      init();
   }

   public CommentType[] getCommentType()
   {
      return CommentType.values();
   }

   public SelectItem[] getCommentTypeValues() {
      SelectItem[] items = new SelectItem[CommentType.values().length];
      int i = 0;
      for(CommentType g: CommentType.values()) {
         items[i++] = new SelectItem(g, g.getLabel());
      }
      return items;
   }

   public Comment getComment()
   {
      return comment;
   }

   public void setComment(Comment comment)
   {
      this.comment = comment;
   }





}
