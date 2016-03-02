package sgr.app.api.semestr;

import java.util.List;
import java.util.Optional;

import sgr.app.api.exceptions.CreateException;

/**
 * @author leonzio
 */
public interface SemestrService
{

   List<Semestr> search(SemestrQuery query);

   void create(Semestr semestr) throws CreateException;

   /**
    * Finds currenlty being semestr.
    *
    * @return
    */
   Optional<Semestr> find();

}
