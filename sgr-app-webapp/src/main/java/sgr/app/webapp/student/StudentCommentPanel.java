package sgr.app.webapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.api.student.Student;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * Panel for displaying comments for student.
 *
 * @author leonzio
 */
public class StudentCommentPanel extends AbstractPanel<Comment>
{

	private static final long serialVersionUID = 4138667330295752265L;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CommentService commentService;

	@Override
	public void init()
	{
		entity = new Comment();
	}

	@Override
	public void onLoad()
	{
		final Student currentLoggedUser = authenticationService.getCurrentUser();
		entities = commentService.findByStudentId(currentLoggedUser.getId());
	}

}
