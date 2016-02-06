package sgr.app.core.comment;

import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.comment.CommentService;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentService;
import sgr.app.core.DaoSupport;
/**
 * @author dawbes89
 */
public class CommentServiceImpl extends DaoSupport implements CommentService
{

   private StudentService studentService;

   @Override
   public void createCommentForStudent(Student student)
   {
      studentService.update(student);

   }

   @Required
   public void setStudentService(StudentService studentService)
   {
      this.studentService = studentService;
   }

}
