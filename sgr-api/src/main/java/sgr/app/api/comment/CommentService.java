package sgr.app.api.comment;

import java.util.List;

import sgr.app.api.student.Student;

/**
 * @author dawbes89
 */
public interface CommentService
{

   public void createCommentForStudent(Student student);

   public void create(Comment comment);

   public List<Comment> findByStudentId(Long studentId);

}
