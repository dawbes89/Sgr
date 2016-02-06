package sgr.app.api.comment;
/**
 * @author dawbes89
 */
public enum CommentType
{
   POSITIVE("#{sgr_translation.commetn_type_positive}"),
   NEGATIVE("sgr_app_translation.sgr.app.api.comment.CommentType.NEGATIVE"),
   REPREHENSIBLE("sgr_app_translation.sgr.app.api.comment.CommentType.REPREHENSIBLE");

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
