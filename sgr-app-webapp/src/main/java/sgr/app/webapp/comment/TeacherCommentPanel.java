package sgr.app.webapp.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
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

   private List<Comment> comments;

   private Comment comment;

   @Override
   public void init()
   {
      comment = new Comment();
      entity = new Student();
      comments = new ArrayList<>();
   }

   @Override
   public void onLoad()
   {
      entities = studentService.search();
   }

   public void create()
   {
      comment.setStudentId(entity.getId());
      comment.setDate(new Date());
      commentService.create(comment);
      init();
   }

   public void findComments()
   {
      comments = commentService.findByStudentId(entity.getId());
   }

   public Comment getComment()
   {
      return comment;
   }

   public void setComment(Comment comment)
   {
      this.comment = comment;
   }

   public List<Comment> getComments()
   {
      return comments;
   }

   public void setComments(List<Comment> comments)
   {
      this.comments = comments;
   }





}
