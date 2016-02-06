package sgr.app.webapp.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

   @Override
   public void init()
   {
      entity = new Student();
   }

   @Override
   public void onLoad()
   {
      entities = studentService.search();
   }

}
