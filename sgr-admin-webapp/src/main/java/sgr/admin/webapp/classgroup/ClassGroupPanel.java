package sgr.admin.webapp.classgroup;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.exceptions.ClassGroupException;
import sgr.app.api.exceptions.CustomException;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * @author leonzio
 */
@Controller
public class ClassGroupPanel extends AbstractPanel<ClassGroup> implements EditablePanel<ClassGroup>
{

   private static final long serialVersionUID = 1665393811406612606L;

   @Autowired
   private ClassGroupService classGroupService;

   @Override
   public void init()
   {
      entity = new ClassGroup();
      entities = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void onLoad()
   {
      init();
   }

   @Override
   public void create()
   {
      try
      {
         classGroupService.create(entity);
         RequestContext context = RequestContext.getCurrentInstance();
         context.execute("PF('addDialog').hide();");
      }
      catch (ClassGroupException e)
      {
         handleException("add", e);
      }
      init();
   }

   @Override
   public void update(ClassGroup object)
   {
      // nothing
   }

   @Override
   public void remove(Long id)
   {
      try
      {
         classGroupService.remove(id);
      }
      catch (ClassGroupException e)
      {
         handleException("root", e);
      }
      init();
   }

   public List<String> getYears()
   {
      return classGroupService.getYears();
   }

   private void handleException(String formId, CustomException throwable)
   {
      final String validationMessage = translationService.translate(throwable.getMessage());
      final FacesMessage message = new FacesMessage(validationMessage);
      message.setSeverity(throwable.getSeverity());
      FacesContext.getCurrentInstance().addMessage(formId, message);
   }
}
