package sgr.app.webapp.student;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceQuery;
import sgr.app.api.presence.PresenceService;
import sgr.app.api.presence.PresenceStatus;
import sgr.app.api.student.Student;
import sgr.app.api.teachingstaff.SchoolSubject;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author leonzio
 */
@Controller
public class StudentPresencePanel extends AbstractPanel<Presence>
{

   private static final long serialVersionUID = -5877371993225024163L;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private PresenceService presenceService;

   private Student currentLoggedStudent;

   private SchoolSubject schoolSubject;

   private Date date;

   private PresenceStatus status;

   @Override
   public void init()
   {
      entity = new Presence();
   }

   @Override
   public void onLoad()
   {
      currentLoggedStudent = authenticationService.getCurrentUser();
   }

   public void searchPresences()
   {
      final PresenceQuery query = new PresenceQuery();
      if (currentLoggedStudent != null)
      {
         query.setStudentId(currentLoggedStudent.getId());
      }
      if (schoolSubject != null)
      {
         query.setSchoolSubject(schoolSubject);
      }
      if (date != null)
      {
         query.setDate(date);
      }
      if (status != null)
      {
         query.setStatus(status);
      }
      entities = presenceService.search(query);
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public PresenceStatus getStatus()
   {
      return status;
   }

   public void setStatus(PresenceStatus status)
   {
      this.status = status;
   }

}
