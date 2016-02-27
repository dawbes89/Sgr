package sgr.app.api.classgroup;

import java.io.Serializable;
import java.util.Optional;

import sgr.app.api.QueryBuilder;

/**
 * Query for classes.
 *
 * @author leonzio
 */
public class ClassGroupQuery implements Serializable
{

   private static final long serialVersionUID = -2359187238841851888L;

   /**
    * For new instances use this.
    */
   public static final ClassGroupQuery EMPTY = new ClassGroupQuery();

   private boolean availableForTeachers;
   private Optional<Long> classId = Optional.empty();
   private Optional<Integer> groupNumber = Optional.empty();
   private Optional<String> groupName = Optional.empty();

   public boolean isAvailableForTeachers()
   {
      return availableForTeachers;
   }

   public boolean hasClassId()
   {
      return classId.isPresent();
   }

   public Long getClassId()
   {
      return classId.get();
   }

   public void setClassId(Long classId)
   {
      this.classId = Optional.of(classId);
   }

   public boolean hasGroupNumber()
   {
      return groupNumber.isPresent();
   }

   public Integer getGroupNumber()
   {
      return groupNumber.get();
   }

   public void setGroupNumber(Integer groupNumber)
   {
      this.groupNumber = Optional.of(groupNumber);
   }

   public boolean hasGroupName()
   {
      return groupName.isPresent();
   }

   public String getGroupName()
   {
      return groupName.get();
   }

   public void setGroupName(String groupName)
   {
      this.groupName = Optional.of(groupName);
   }

   public void setAvailableForTeachers(boolean availableForTeachers)
   {
      this.availableForTeachers = availableForTeachers;
   }



   public static Builder all()
   {
      return new Builder();
   }

   /**
    * @author dawbes
    */
   public static class Builder extends QueryBuilder<ClassGroupQuery>
   {

      public Builder()
      {
         super(new ClassGroupQuery());
      }

      public Builder withAvailableForTeachers(boolean availableForTeachers)
      {
         query.setAvailableForTeachers(availableForTeachers);
         return this;
      }

      public Builder withClassGroupId(Long classId)
      {
         query.setClassId(classId);
         return this;
      }

      public Builder withGroupName(String groupName)
      {
         query.setGroupName(groupName);
         return this;
      }

      public Builder withGroupNumber(Integer groupNumber)
      {
         query.setGroupNumber(groupNumber);
         return this;
      }

   }

}
