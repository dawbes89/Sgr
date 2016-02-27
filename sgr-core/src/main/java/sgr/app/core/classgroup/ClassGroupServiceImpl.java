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
import sgr.app.api.exceptions.ClassGroupException;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class ClassGroupServiceImpl extends DaoSupport implements ClassGroupService
{

   @Override
   public List<ClassGroup> search(ClassGroupQuery query)
   {
      Criteria criteria = createCriteriaFromQuery(query);
      return search(criteria);
   }

   @Override
   public void create(ClassGroup classGroup) throws ClassGroupException
   {
      Optional<ClassGroup> optionalClass = find(
            ClassGroupQuery.all().withGroupName(classGroup.getGroupName())
                  .withGroupNumber(classGroup.getGroupNumber()).build());
      if (optionalClass.isPresent())
      {
         throw new ClassGroupException("classGroupException_classExists",
               FacesMessage.SEVERITY_ERROR);
      }
      classGroup.setYear(new Date());
      createEntity(classGroup);
   }

   @Override
   public void remove(Long id) throws ClassGroupException
   {
      Criteria criteria = findIndelibleClasses(id);
      List<Object> indelibleClasses = search(criteria);
      if (!indelibleClasses.isEmpty())
      {
         throw new ClassGroupException("classGroupException_canNotDelete",
               FacesMessage.SEVERITY_ERROR);
      }
      removeEntity(getEntity(ClassGroup.class, id));
   }

   @Override
   public Optional<ClassGroup> find(ClassGroupQuery query)
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      if (query.hasGroupNumber())
      {
         criteria.add(Restrictions.eq(ClassGroup.PROPERTY_GROUP_NUMBER, query.getGroupNumber()));
      }
      if (query.hasGroupName())
      {
         criteria.add(Restrictions.eq(ClassGroup.PROPERTY_GROUP_NAME, query.getGroupName()));
      }
      List<ClassGroup> result = search(criteria);
      if (result.size() > 0)
      {
         return Optional.of(result.get(0));
      }
      return Optional.empty();

   }

   private Criteria findIndelibleClasses(Long classGroupId)
   {

      Criteria criteria = createCriteria(ClassGroup.class);
      criteria.add(Restrictions.eq(ClassGroup.PROPERTY_ID, classGroupId));
      criteria.add(Restrictions
            .sqlRestriction("this_.id IN (SELECT class_group_id FROM lesson WHERE class_group_id = "
                  + classGroupId + ") OR this_.id IN "
                  + "(SELECT class_group_id FROM student WHERE class_group_id = " + classGroupId
                  + ")"
                  + " OR this_.id IN (select preceptor_class_id FROM teaching_stuff WHERE preceptor_class_id = "
                  + classGroupId + ")"));

      return criteria;
   }

   private Criteria createCriteriaFromQuery(ClassGroupQuery query)
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      criteria.addOrder(Order.asc("groupNumber")).addOrder(Order.asc("groupName"))
            .addOrder(Order.asc("year"));
      if (query.hasClassId())
      {
         criteria.add(Restrictions.sqlRestriction(
               "this_.id NOT IN (SELECT preceptor_class_id FROM teaching_stuff WHERE preceptor_class_id IS NOT NULL AND preceptor_class_id != "
                     + query.getClassId() + ")"));
      }
      if (query.isAvailableForTeachers())
      {
         criteria.add(Restrictions.sqlRestriction(
               "this_.id NOT IN (SELECT preceptor_class_id FROM teaching_stuff WHERE preceptor_class_id IS NOT NULL)"));
      }
      return criteria;
   }

}
