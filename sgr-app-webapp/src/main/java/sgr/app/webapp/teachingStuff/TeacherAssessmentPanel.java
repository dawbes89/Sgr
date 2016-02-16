package sgr.app.webapp.teachingStuff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.assessment.Assessment;
import sgr.app.api.assessment.AssessmentQuery;
import sgr.app.api.assessment.AssessmentService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
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
public class TeacherAssessmentPanel extends AbstractPanel<Student>
{
   private static final long serialVersionUID = 8302392304292102639L;

   @Autowired
   private TranslationService translationService;

   @Autowired
   private AssessmentService assessmentService;

   @Autowired
   private StudentService studentService;

   @Autowired
   private ClassGroupService classGroupService;

   @Autowired
   private AuthenticationService authenticationService;

   private TeachingStuff currentLoggedTeacher;

   private Assessment assessment;

   private ClassGroup classGroup;

   private List<ClassGroup> classes;

   private List<Assessment> assessments;

   @Override
   public void init()
   {
      classGroup = new ClassGroup();
      assessment = new Assessment();
      entity = new Student();
      entities = new ArrayList<>();
      assessments = new ArrayList<>();
      classes = classGroupService.search(ClassGroupQuery.EMPTY);

   }

   @Override
   public void onLoad()
   {
      init();

      currentLoggedTeacher = authenticationService.getCurrentUser();
      if (currentLoggedTeacher == null)
      {
         return;
      }
      final ClassGroup preceptorClass = currentLoggedTeacher.getPreceptorClass();

      if (preceptorClass == null)
      {
         return;
      }
      classGroup = preceptorClass;
      searchStudents(preceptorClass.getId());
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
      assessment.setStudentId(entity.getId());
      assessment.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
      assessment.setDate(new Date());
      assessmentService.create(assessment);
      assessment = new Assessment();

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

   public TeachingStuff getCurrentLoggedTeacher()
   {
      return currentLoggedTeacher;
   }

   public void setCurrentLoggedTeacher(TeachingStuff currentLoggedTeacher)
   {
      this.currentLoggedTeacher = currentLoggedTeacher;
   }

   public Assessment getAssessment()
   {
      return assessment;
   }

   public void setAssessment(Assessment assessment)
   {
      this.assessment = assessment;
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

   public List<Assessment> getAssessments()
   {
      if (currentLoggedTeacher == null || entity.getId() == null)
      {
         return new ArrayList<>();
      }
      assessments = assessmentService.search(AssessmentQuery.all()
            .withSchoolSubject(currentLoggedTeacher.getSchoolSubject())
            .withStudentId(entity.getId()).build());
      return assessments;
   }

   public String getAverageAssessments()
   {
      float average = 0f;
      if(assessments.isEmpty())
      {
         return String.format("%.1f", average);
      }
      for (Assessment assessment : assessments)
      {
         average = average + assessment.getAssessment();
      }
      average = average / assessments.size();
      return String.format("%.1f", average);
   }

}
