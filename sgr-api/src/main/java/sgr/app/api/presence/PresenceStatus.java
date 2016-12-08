package sgr.app.api.presence;

/**
 * @author dawbes
 */
public enum PresenceStatus
{
	PRESENT("presenceStatus_present"),
	ABSENT_EXCUSED("presenceStatus_absentExcused"),
	ABSENT("presenceStatus_absent"),
	LATENESS("presenceStatus_latencess");

	private final String label;

	PresenceStatus(String label)
	{
		this.label = label;
	}

	public String getLabel()
	{
		return label;
	}


}
