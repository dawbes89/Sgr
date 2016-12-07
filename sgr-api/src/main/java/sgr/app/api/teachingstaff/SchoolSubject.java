package sgr.app.api.teachingstaff;

/**
 * @author dawbes89
 */
public enum SchoolSubject
{
	POLISH_LANGUAGE("schoolSubject_language_polish"),
	MATHS("schoolSubject_maths"),
	HISTORY("schoolSubject_history"),
	CIVIC_EDUCATION("schoolSubject_civicEducation"),
	BIOLOGY("schoolSubject_biology"),
	CHEMISTRY("schoolSubject_chemistry"),
	GEOGRAPHY("schoolSubject_geography"),
	INFORMATICS("schoolSubject_informatics"),
	TECHNIQUE("schoolSubject_technique"),
	ART_MUSIC("schoolSubject_artMusic"),
	RELIGION("schoolSubject_religion"),
	EUROPEAN_EDUCATION("schoolSubject_europeanEducation"),
	ENGLISH("schoolSubject_language_english"),
	PHYSICS("schoolSubject_physics"),
	GERMAN("schoolSubject_language_german");

	private String label;

	private SchoolSubject(String label)
	{
		this.label = label;
	}

	public String getLabel()
	{
		return label;
	}

}
