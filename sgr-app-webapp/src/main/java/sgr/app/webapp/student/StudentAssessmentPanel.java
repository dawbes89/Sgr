package sgr.app.webapp.student;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.assessment.Assessment;
import sgr.app.api.assessment.AssessmentQuery;
import sgr.app.api.assessment.AssessmentService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.student.Student;
import sgr.app.api.teachingStuff.SchoolSubject;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes89
 */
public class StudentAssessmentPanel extends AbstractPanel<Assessment>
{

   private static final long serialVersionUID = 941998774046463482L;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private AssessmentService assessmentService;

   private Student currentLoggedUser;

   private SchoolSubject schoolSubject;

   @Override
   public void init()
   {
      entities = new ArrayList<>();
      entity = new Assessment();
   }

   @Override
   public void onLoad()
   {
      searchAssessments();
   }

   public void searchAssessments()
   {
      currentLoggedUser = authenticationService.getCurrentUser();
      entities = assessmentService.search(createQuery());
   }

   public String getAverageAssessments()
   {
      double average = 0;
      if (currentLoggedUser != null && entity != null)
      {
         average = assessmentService.getAverageAssesment(createQuery());
      }
      return String.format("%1$,.2f", average);
   }

   private AssessmentQuery createQuery()
   {
      final AssessmentQuery query = new AssessmentQuery();
      if (currentLoggedUser != null)
      {
         query.setStudentId(currentLoggedUser.getId());
      }
      if (schoolSubject != null)
      {
         query.setSchoolSubject(schoolSubject);
      }
      return query;
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

   public SchoolSubject[] getSchoolSubjects()
   {
      return SchoolSubject.values();
   }

}
