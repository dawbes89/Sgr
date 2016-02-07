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
import sgr.app.frontend.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.webapp.loginPanel.LoginPanel;

/**
 * @author dawbes89
 */
// REVIEW tak si� zastanawiam, czy tabelki z uczniami lepiej nie zrobi� bez
// klasy a klas� wybiera� nad tabelk� z comboboxa, i po wybraniu robi� update na
// tabeli wy�wietlaj�c list� uczni�w z tej klasy, ale o tym pasuje jeszcze
// pogada�.
// A i wsumie ten panel mo�na przenie�c do paczki nauczyciela, bo to dla niego
// panel, a dla ucznia zrobi� podobnie w jego paczce
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
      LoginPanel loginPanel  = BeanHelper.findBean("#{loginPanel}", LoginPanel.class);
      loginPanel.getExistTeacher();
      comment.setStudentId(entity.getId());
      comment.setIssuerName(loginPanel.getExistTeacher().getTeacherFullName());
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
