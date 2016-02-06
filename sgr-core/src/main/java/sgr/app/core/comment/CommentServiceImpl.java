package sgr.app.core.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.comment.Comment;
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

   @Override
   public void create(Comment comment)
   {
      createEntity(comment);

   }

   @Override
   public List<Comment> findByStudentId(Long studentId)
   {
      Criteria criteria = createCriteria(Comment.class);
      criteria.add(Restrictions.eq(Comment.PROPERTY_STUDENT_ID, studentId));
      return search(criteria);
   }

}
