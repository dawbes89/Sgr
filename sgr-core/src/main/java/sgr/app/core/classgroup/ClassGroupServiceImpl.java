package sgr.app.core.classgroup;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.exceptions.CreateException;
import sgr.app.api.exceptions.RemoveException;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class ClassGroupServiceImpl extends DaoSupport implements ClassGroupService
{

   @Override
   public List<ClassGroup> search(ClassGroupQuery query)
   {
      Criteria criteria = createCriteria(query);
      return search(criteria);
   }

   @Override
   public void create(ClassGroup classGroup) throws CreateException
   {
      final Optional<ClassGroup> optionalClass = find(
            ClassGroupQuery.all().withGroupName(classGroup.getGroupName())
                  .withGroupNumber(classGroup.getGroupNumber()).build());
      if (optionalClass.isPresent())
      {
         throw new CreateException("classGroupException_classExists", FacesMessage.SEVERITY_ERROR);
      }
      classGroup.setYear(new Date());
      createEntity(classGroup);
   }

   @Override
   public void remove(Long id) throws RemoveException
   {
      final Criteria criteria = findIndelibleClasses(id);
      final List<Object> indelibleClasses = search(criteria);
      if (!indelibleClasses.isEmpty())
      {
         throw new RemoveException("removeException_canNotDelete", FacesMessage.SEVERITY_ERROR);
      }
      removeEntity(ClassGroup.class, id);
   }

   @Override
   public Optional<ClassGroup> find(ClassGroupQuery query)
   {
      final Criteria criteria = createCriteria(query);
      return find(criteria);
   }

   private Criteria findIndelibleClasses(Long classGroupId)
   {
      final Criteria criteria = createCriteria(ClassGroup.class);
      criteria.add(Restrictions.eq(ClassGroup.PROPERTY_ID, classGroupId));
      criteria.add(Restrictions
            .sqlRestriction("this_.id IN (SELECT class_group_id FROM lesson WHERE class_group_id = "
                  + classGroupId + ") OR this_.id IN "
                  + "(SELECT class_group_id FROM student WHERE class_group_id = " + classGroupId
                  + ")"
                  + " OR this_.id IN (select preceptor_class_id FROM teaching_staff WHERE preceptor_class_id = "
                  + classGroupId + ")"));
      return criteria;
   }

   private Criteria createCriteria(ClassGroupQuery query)
   {
      final Criteria criteria = createCriteria(ClassGroup.class);
      criteria.addOrder(Order.asc("groupNumber")).addOrder(Order.asc("groupName"))
            .addOrder(Order.asc("year"));
      if (query.hasClassId())
      {
         criteria.add(Restrictions.sqlRestriction(
               "this_.id NOT IN (SELECT preceptor_class_id FROM teaching_staff WHERE preceptor_class_id IS NOT NULL AND preceptor_class_id != "
                     + query.getClassId() + ")"));
      }
      if (query.isAvailableForTeachers())
      {
         criteria.add(Restrictions.sqlRestriction(
               "this_.id NOT IN (SELECT preceptor_class_id FROM teaching_staff WHERE preceptor_class_id IS NOT NULL)"));
      }
      if (query.hasGroupNumber())
      {
         criteria.add(Restrictions.eq(ClassGroup.PROPERTY_GROUP_NUMBER, query.getGroupNumber()));
      }
      if (query.hasGroupName())
      {
         criteria.add(Restrictions.eq(ClassGroup.PROPERTY_GROUP_NAME, query.getGroupName()));
      }
      return criteria;
   }

}
