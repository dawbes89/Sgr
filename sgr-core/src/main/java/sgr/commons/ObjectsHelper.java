package sgr.commons;

/**
 * @author leonzio
 */
public final class ObjectsHelper
{
   @SuppressWarnings("unchecked")
   public static <T> T uncheckedCast(Object object)
   {
      return (T) object;
   }
}
