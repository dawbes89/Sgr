package sgr.app.webapp.teachingStuff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;
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
   private ClassGroupService classGroupService;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private PresenceService presenceService;

   private TeachingStuff currentLoggedTeacher;

   private List<Presence> presences;

   private Presence presence;

   private ClassGroup classGroup;

   private SchoolSubject schoolSubject;

   @Override
   public void init()
   {
      classGroup = new ClassGroup();
      entity = new Presence();
      entities = new ArrayList<>();
   }

   @Override
   public void onLoad()
   {
      init();
      searchPresences();
   }

   public void update(Presence presence)
   {
      presenceService.update(presence);
      searchPresences();
   }

   public boolean checkStatus(Presence presence)
   {
      PresenceStatus status = presence.getStatus();
      if (status.equals(PresenceStatus.PRESENT))
      {
         return false;
      }
      return true;
   }

   public void searchPresences()
   {
      PresenceQuery query = new PresenceQuery();
      TeachingStuff currentLoggedUser = authenticationService.getCurrentUser();
      if (currentLoggedUser == null)
      {
         return;
      }
      classGroup = currentLoggedUser.getPreceptorClass();
      if (classGroup != null)
      {
         query.setClassGroupId(classGroup.getId());
      }
      if (schoolSubject != null)
      {
         query.setSchoolSubject(schoolSubject);
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

   public TeachingStuff getCurrentLoggedTeacher()
   {
      return currentLoggedTeacher;
   }

   public void setCurrentLoggedTeacher(TeachingStuff currentLoggedTeacher)
   {
      this.currentLoggedTeacher = currentLoggedTeacher;
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

   public void setClassGroup(ClassGroup classGroup)
   {
      this.classGroup = classGroup;
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

}
