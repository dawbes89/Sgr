package sgr.app.webapp.teachingStuff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.translation.TranslationService;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes89
 */
@Controller
public class TeacherCommentPanel extends AbstractPanel<Student>
{

   private static final long serialVersionUID = -6502541271869290855L;

   @Autowired
   private TranslationService translationService;

   @Autowired
   private CommentService commentService;

   @Autowired
   private StudentService studentService;

   @Autowired
   private ClassGroupService classGroupService;

   @Autowired
   private AuthenticationService authenticationService;

   private TeachingStuff currentLoggedTeacher;

   private Comment comment;

   private ClassGroup classGroup;

   private List<ClassGroup> classes;

   @Override
   public void init()
   {
      classGroup = new ClassGroup();
      comment = new Comment();
      entity = new Student();
      entities = new ArrayList<>();
      classes = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void onLoad()
   {
      init();

      currentLoggedTeacher = authenticationService.getCurrentUser();
      if (currentLoggedTeacher != null)
      {
         final ClassGroup preceptorClass = currentLoggedTeacher.getPreceptorClass();
         if (preceptorClass != null)
         {
            classGroup = preceptorClass;
            searchStudents(preceptorClass.getId());
         }
      }
   }

   public void handleClassChange()
   {
      if (classGroup.getId() != null)
      {
         searchStudents(classGroup.getId());
      }
      else
      {
         entities = new ArrayList<>();
      }
   }

   public void create()
   {
      comment.setStudentId(entity.getId());
      comment.setIssuerName(currentLoggedTeacher.getTeacherFullName());
      comment.setDate(new Date());
      commentService.create(comment);
      comment = new Comment();

      final String messageContent = translationService.translate("form_comment_savedMessage");
      final FacesMessage message = new FacesMessage(messageContent);
      message.setSeverity(FacesMessage.SEVERITY_INFO);
      FacesContext.getCurrentInstance().addMessage("add", message);
   }

   private void searchStudents(Long classId)
   {
      final StudentQuery query = StudentQuery.withClassGroupId(classId);
      entities = studentService.search(query);
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
      return commentService.findByStudentId(entity.getId());
   }

   public ClassGroup getClassGroup()
   {
      return classGroup;
   }

   public void setClassGroup(ClassGroup classGroup)
   {
      this.classGroup = classGroup;
   }

   public List<ClassGroup> getClasses()
   {
      return classes;
   }

}
