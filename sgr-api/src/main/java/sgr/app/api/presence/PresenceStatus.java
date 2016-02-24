package sgr.app.api.presence;
/**
 * @author dawbes
 */
public enum PresenceStatus
{
   PRESENT("form_presence_status_present"),
   ABSENT_EXCUSED("form_presence_status_absentExcused"),
   ABSENT("form_presence_status_absent"),
   LATENESS("form_presence_status_latencess");

   private String label;

   private PresenceStatus(String label)
   {
      this.label = label;
   }

   public String getLabel()
   {
      return label;
   }


}
