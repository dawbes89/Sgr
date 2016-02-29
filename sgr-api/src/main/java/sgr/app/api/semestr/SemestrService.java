package sgr.app.api.semestr;

import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
public interface SemestrService
{

   List<Semestr> search();

   void create(Semestr semestr);

   Optional<Semestr> find();

}
