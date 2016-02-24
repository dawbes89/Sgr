package sgr.app.webapp.teachingStuff;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

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
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes89
 */
@Controller
public class TeacherAssessmentPanel extends AbstractPanel<Student>
{
   private static final long serialVersionUID = 8302392304292102639L;

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
      assessment = new Assessment();
      entity = new Student();
      entities = new ArrayList<>();
      assessments = new ArrayList<>();
      classes = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void onLoad()
   {
      currentLoggedTeacher = authenticationService.getCurrentUser();
      classGroup = currentLoggedTeacher.getPreceptorClass();
      handleClassChange();
   }

   public void handleClassChange()
   {
      if (classGroup == null || classGroup.getId() == null)
      {
         entities = new ArrayList<>();
      }
      else
      {
         final StudentQuery query = StudentQuery.withClassGroupId(classGroup.getId());
         entities = studentService.search(query);
      }
   }

   public void create()
   {
      assessment.setStudentId(entity.getId());
      assessment.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
      assessmentService.create(assessment);
      assessment = new Assessment();

      showValidationMessage("add", "form_comment_savedMessage", FacesMessage.SEVERITY_INFO);
   }

   public List<Assessment> getAssessments()
   {
      if (currentLoggedTeacher == null || entity.getId() == null)
      {
         return new ArrayList<>();
      }
      assessments = assessmentService.search(createQuery());
      return assessments;
   }

   public String getAverageAssessments()
   {
      double average = 0;
      if (currentLoggedTeacher != null && entity != null)
      {
         average = assessmentService.getAverageAssesment(createQuery());
      }
      return String.format("%1$,.2f", average);
   }

   private AssessmentQuery createQuery()
   {
      final AssessmentQuery query = new AssessmentQuery();
      if (currentLoggedTeacher != null)
      {
         query.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
      }
      if (entity != null)
      {
         query.setStudentId(entity.getId());
      }
      return query;
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

}
