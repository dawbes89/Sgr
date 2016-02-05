package sgr.app.api.translation;

/**
 * @author leonzio
 */
public interface TranslationService
{

   String translate(String key);

   String translate(String resourceFileName, String key);
}
