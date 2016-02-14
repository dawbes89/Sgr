package sgr.app.api.comment;

import java.util.List;

/**
 * @author dawbes89
 */
public interface CommentService
{

   void create(Comment comment);

   List<Comment> findByStudentId(Long studentId);

}
