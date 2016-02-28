package sgr.app.frontend.panels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.person.PersonName;
import sgr.app.api.translation.TranslationService;

/**
 * Abstract base for <code>MainPanel</code>s used in application.
 *
 * @author leonzio
 */
public abstract class MainApplicationPanel implements Serializable
{

   private static final long serialVersionUID = -4946956951529094970L;

   @Autowired
   protected AuthenticationService authenticationService;

   @Autowired
   private TranslationService translationService;

   private String currentPanelKey = "panel_main";
   private Map<String, String> panels;

   protected MainApplicationPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

      panels = new HashMap<>();
      // common panels
      panels.put("changePasswordPanel", "panel_changePasswordPanel");
      panels.put("announcementPanel", "panel_announcementPanel");
      panels.put("teachingStuffPanel", "panel_teachingStuffPanel");

      // panels in admin
      panels.put("classGroupPanel", "panel_classGroupPanel");
      panels.put("studentPanel", "panel_studentPanel");
      panels.put("adminPanel", "panel_adminPanel");

      // panels in application
      panels.put("teacherLessonPanel", "panel_teacher_lessonPanel");
      panels.put("teacherAssessmentPanel", "panel_assessmentPanel");
      panels.put("teacherCommentPanel", "panel_commentPanel");
      panels.put("preceptorPresencePanel", "panel_preceptorPresencePanel");
      panels.put("studentPresencePanel", "panel_student_presencePanel");
      panels.put("studentAssessmentPanel", "panel_assessmentPanel");
      panels.put("studentCommentPanel", "panel_commentPanel");
      panels.put("studentNotificationPanel", "panel_student_notificationPanel");
      panels.put("siteSettings", "panel_siteSettings");
   }

   public final String getUserName()
   {
      final Object currentUser = authenticationService.getCurrentUser();
      return ((PersonName) currentUser).getFullName();
   }

   public final String displayCurrentPanelName()
   {
      final FacesContext context = FacesContext.getCurrentInstance();
      final String vievId = context.getViewRoot().getViewId();

      panels.forEach((key, value) -> {
         if (vievId.contains(key))
         {
            currentPanelKey = value;
         }
      });
      final String translated = translationService.translate(currentPanelKey);
      return translationService.translate("panel_default", new Object[] { translated });
   }

}
