package sgr.app.frontend.validators;

import java.util.Date;

public enum DateValidationMode
{

   /**
    * Validated date must be strictly before reference date
    */
   BEFORE("validation_date_before")
   {
      @Override
      boolean isValid(Date referenceDate, Date value)
      {
         return value.before(referenceDate);
      }
   },

   /**
    * Validated date must be strictly after reference date
    */
   AFTER("validation_date_after")
   {
      @Override
      boolean isValid(Date referenceDate, Date value)
      {
         return value.after(referenceDate);
      }
   },

   /**
    * Validated date must be after or the same as reference date
    */
   NOT_BEFORE("validation_date_notBefore")
   {
      @Override
      boolean isValid(Date referenceDate, Date value)
      {
         return !value.before(referenceDate);
      }
   },

   /**
    * Validated date must be before or the same as reference date
    */
   NOT_AFTER("validation_date_notAfter")
   {
      @Override
      boolean isValid(Date referenceDate, Date value)
      {
         return !value.after(referenceDate);
      }
   };

   private String label;
   private DateValidationMode(String label)
   {
      this.label = label;
   }

   public String getLabel()
   {
      return label;
   }

   abstract boolean isValid(Date referenceDate, Date value);
}
