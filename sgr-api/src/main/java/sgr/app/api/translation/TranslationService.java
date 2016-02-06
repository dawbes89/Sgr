package sgr.app.api.translation;

/**
 * Service used for translate messages from resource files in java files.
 *
 * @author leonzio
 */
public interface TranslationService
{

   /**
    * Translates value.
    * 
    * @param key
    *           of value to translate
    * @return translated value
    */
   String translate(String key);

   /**
    * Translates value.
    * 
    * @param resourceFileName
    *           resource file name from faces-config.xml file
    * @param key
    *           of value to translate
    * @return translated value
    */
   String translate(String resourceFileName, String key);
}
