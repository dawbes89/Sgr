package sgr.app.api.comment;

import java.util.List;

/**
 * @author dawbes89
 */
public interface CommentService
{

   public void create(Comment comment);

   public List<Comment> findByStudentId(Long studentId);

}
