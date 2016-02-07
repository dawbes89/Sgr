package sgr.app.api.comment;
/**
 * @author dawbes89
 */
public enum CommentType
{
   POSITIVE("comment_type_positive"),
   NEGATIVE("comment_type_negative"),
   REPREHENSIBLE("comment_type_reprehensible");

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
