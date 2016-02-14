package sgr.app.frontend.converters;

/**
 * @author dawbes
 */
public class AssessmentConverter extends AbstractConverter<Float>
{

   private final static Float ONE_POINT_SEVENTYFIVE =  1.75f;
   private final static Float TWO_POINT_FIVE =  2.5f;
   private final static Float TWO_POINT_SEVENTYFIVE =  2.75f;
   private final static Float THREE_POINT_FIVE =  3.5f;
   private final static Float THREE_POINT_SEVENTYFIVE =  3.75f;
   private final static Float FOUR_POINT_FIVE =  4.5f;
   private final static Float FOUR_POINT_SEVENTYFIVE =  4.75f;
   private final static Float FIVE_POINT_FIVE =  5.5f;
   @Override
   protected Float convertToObject(String value)
   {
      return Float.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {

      if(object.equals(ONE_POINT_SEVENTYFIVE))
      {
         return "-2";
      }
      if(object.equals(TWO_POINT_FIVE))
      {
         return "+2";
      }
      if(object.equals(TWO_POINT_SEVENTYFIVE))
      {
         return "-3";
      }
      if(object.equals(THREE_POINT_FIVE))
      {
         return "+3";
      }
      if(object.equals(THREE_POINT_SEVENTYFIVE))
      {
         return "-4";
      }
      if(object.equals(FOUR_POINT_FIVE))
      {
         return "+4";
      }
      if(object.equals(FOUR_POINT_SEVENTYFIVE))
      {
         return "-5";
      }
      if(object.equals(FIVE_POINT_FIVE))
      {
         return "+5";
      }
      return object.toString().substring(0, 1);
   }

}
