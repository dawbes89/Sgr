package sgr.app.core.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes89
 */
class CommentServiceImpl extends DaoSupport implements CommentService
{

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
