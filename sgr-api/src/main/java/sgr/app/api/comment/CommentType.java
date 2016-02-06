package sgr.app.api.comment;
/**
 * @author dawbes89
 */
public enum CommentType
{
   POSITIVE("POSitive"),
   NEGATIVE("NEGATIVE"),
   REPREHENSIBLE("REPREHENSIBLE");

   private String label;

   private CommentType(String label)
   {
      this.label = label;
   }

   public String getLabel()
   {
      return label;
   }



}
