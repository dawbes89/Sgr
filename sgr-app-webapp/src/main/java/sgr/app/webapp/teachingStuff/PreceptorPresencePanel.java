package sgr.app.webapp.teachingStuff;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceQuery;
import sgr.app.api.presence.PresenceService;
import sgr.app.api.presence.PresenceStatus;
import sgr.app.api.teachingStuff.SchoolSubject;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes
 */
@Controller
public class PreceptorPresencePanel extends AbstractPanel<Presence>
{

   private static final long serialVersionUID = -5877371993225024163L;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private PresenceService presenceService;

   private TeachingStuff currentLoggedTeacher;

   private List<Presence> presences;

   private Presence presence;

   private ClassGroup classGroup;

   private SchoolSubject schoolSubject;

   private String studentFullName;

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
      currentLoggedTeacher = authenticationService.getCurrentUser();
      classGroup = currentLoggedTeacher.getPreceptorClass();
      searchPresences();
   }

   public void update(Presence presence)
   {
      presenceService.update(presence);
      searchPresences();
   }

   public boolean checkStatus(Presence presence)
   {
      return !PresenceStatus.PRESENT.equals(presence.getStatus());
   }

   public void searchPresences()
   {
      final PresenceQuery query = new PresenceQuery();
      if (classGroup != null)
      {
         query.setClassGroupId(classGroup.getId());
      }
      if (schoolSubject != null)
      {
         query.setSchoolSubject(schoolSubject);
      }
      if (studentFullName != null && !"".equals(studentFullName))
      {
         query.setStudentFullName(studentFullName);
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

   public SchoolSubject[] getSchoolSubjects()
   {
      return SchoolSubject.values();
   }

   public PresenceStatus[] getStatuses()
   {
      return PresenceStatus.values();
   }

   public List<Presence> getPresences()
   {
      return presences;
   }

   public void setPresences(List<Presence> presences)
   {
      this.presences = presences;
   }

   public Presence getPresence()
   {
      return presence;
   }

   public void setPresence(Presence presence)
   {
      this.presence = presence;
   }

   public ClassGroup getClassGroup()
   {
      return classGroup;
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

   public String getStudentFullName()
   {
      return studentFullName;
   }

   public void setStudentFullName(String studentFullName)
   {
      this.studentFullName = studentFullName;
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
