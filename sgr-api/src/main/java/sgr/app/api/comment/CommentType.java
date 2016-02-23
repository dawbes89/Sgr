package sgr.app.api.comment;

/**
 * @author dawbes89
 */
public enum CommentType
{
   POSITIVE("commentType_positive"),
   NEGATIVE("commentType_negative"),
   REPREHENSIBLE("commentType_reprehensible");

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
