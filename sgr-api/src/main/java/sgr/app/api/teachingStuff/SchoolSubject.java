package sgr.app.api.teachingStuff;

public enum SchoolSubject
{
   POLISH_LANGUAGE("school_subject_polish_language"),
   MATHS("school_subject_maths"),
   HISTORY("school_subject_history"),
   CIVIC_EDUCATION("school_subject_civic_education"),
   BIOLOGY("school_subject_biology"),
   CHEMISTRY("school_subject_chemistry"),
   GEOGRAPHY("school_subject_geography"),
   INFORMATICS("school_subject_informatics"),
   TECHNIQUE("school_subject_technique"),
   ART_MUSIC("school_subject_art_music"),
   RELIGION("school_subject_religion"),
   EUROPEAN_EDUCATION("school_subject_european_education"),
   ENGLISH("school_subject_english_language"),
   PHYSICS("school_subject_physics"),
   GERMAN("school_subject_german_language");

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
