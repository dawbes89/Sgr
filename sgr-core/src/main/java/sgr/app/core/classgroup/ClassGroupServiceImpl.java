package sgr.app.core.classgroup;

import java.util.List;

import org.hibernate.Criteria;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.commons.core.DaoSupport;

/**
 * @author leonzio
 */
public class ClassGroupServiceImpl extends DaoSupport implements ClassGroupService
{

   @Override
   public List<ClassGroup> search()
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      return search(criteria);
   }

   @Override
   public void create(ClassGroup classGroup)
   {
      createEntity(classGroup);
   }

   @Override
   public void remove(Long id)
   {
      removeEntity(getEntity(ClassGroup.class, id));
   }

}
