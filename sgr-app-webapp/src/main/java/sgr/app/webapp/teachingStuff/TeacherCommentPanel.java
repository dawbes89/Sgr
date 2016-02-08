package sgr.app.webapp.teachingStuff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.frontend.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.webapp.loginPanel.LoginPanel;

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

   @Autowired
   private ClassGroupService classGroupService;

   private List<Comment> comments;

   private Comment comment;

   private ClassGroup classGroup;

   private List<ClassGroup> classes;

   @Override
   public void init()
   {
      classGroup = new ClassGroup();
      comment = new Comment();
      entity = new Student();
      comments = new ArrayList<>();
      classes = new ArrayList<>();
   }

   @Override
   public void onLoad()
   {
      init();
//      entities = studentService.search(StudentQuery.EMPTY);
      classes = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   public void create()
   {
      LoginPanel loginPanel  = BeanHelper.findBean("#{loginPanel}", LoginPanel.class);
      comment.setStudentId(entity.getId());
      comment.setIssuerName(loginPanel.getCurrentTeacher().getTeacherFullName());
      comment.setDate(new Date());
      commentService.create(comment);
      init();
   }

   public void findComments()
   {
      comments = commentService.findByStudentId(entity.getId());
   }

   public void handleChange()
   {
      StudentQuery query = new StudentQuery();
      if(classGroup != null)
      {
         query.setClassGroupId(classGroup.getId());
      }

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
      return comments;
   }

   public void setComments(List<Comment> comments)
   {
      this.comments = comments;
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

   public void setClasses(List<ClassGroup> classes)
   {
      this.classes = classes;
   }

}
