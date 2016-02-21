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
 * @author dawbes
 */
public class StudentAssessmentPanel extends AbstractPanel<Assessment>
{

   private static final long serialVersionUID = 941998774046463482L;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private AssessmentService assessmentService;

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
      final Student currentLoggedUser = authenticationService.getCurrentUser();
      if (currentLoggedUser == null)
      {
         return;
      }
      AssessmentQuery query = AssessmentQuery.all().withStudentId(currentLoggedUser.getId())
            .build();
      if (schoolSubject != null)
      {
         query.setSchoolSubject(schoolSubject);
      }
      entities = assessmentService.search(query);
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

   public String getAverageAssessments()
   {
      float average = 0;
      for (Assessment assessment : entities)
      {
         average += assessment.getAssessment();
      }
      average = entities.isEmpty() ? average : average / entities.size();
      return String.format("%.1f", average);
   }

}
