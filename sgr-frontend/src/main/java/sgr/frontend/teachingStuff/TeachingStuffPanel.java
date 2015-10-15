package sgr.frontend.teachingStuff;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.api.teachingStuff.TeachingStuff;
import sgr.api.teachingStuff.TeachingStuffService;

/**
 * @author dawbes
 */
@Controller
@ManagedBean(name = "teachingStuffPanel")
@ViewScoped
public class TeachingStuffPanel implements Serializable
{

   private static final long serialVersionUID = 4055047736483383800L;

   private TeachingStuff teachingStuff = new TeachingStuff();
   private List<TeachingStuff> teachingStuffs;
   private TeachingStuff selectedTeacher;

   @Autowired
   private TeachingStuffService teachingStuffService;

   @PostConstruct
   public void init()
   {
      teachingStuffs = teachingStuffService.search();
   }

   public TeachingStuff getTeachingStuff()
   {
      return teachingStuff;
   }

   public void setTeachingStuff(TeachingStuff teachingStuff)
   {
      this.teachingStuff = teachingStuff;
   }

   public List<TeachingStuff> getTeachingStuffs()
   {
      return teachingStuffs;
   }

   public void setTeachingStuffs(List<TeachingStuff> teachingStuffs)
   {
      this.teachingStuffs = teachingStuffs;
   }

   public TeachingStuffService getTeachingStuffService()
   {
      return teachingStuffService;
   }

   public void setTeachingStuffService(TeachingStuffService teachingStuffService)
   {
      this.teachingStuffService = teachingStuffService;
   }

   public TeachingStuff getSelectedTeacher()
   {
      return selectedTeacher;
   }

   public void setSelectedTeacher(TeachingStuff selectedTeacher)
   {
      this.selectedTeacher = selectedTeacher;
   }

}
