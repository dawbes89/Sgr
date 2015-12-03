package sgr.app.api.account;

import java.util.List;
import java.util.Optional;

/**
 * @author dawbes
 */
// REVIEW serwis pod taką nazwą obecnie potrzebny nie jest chyba, że z
// metodą fingAccountByLogin(String login)
// jeśli znajdzie taki account to go zwroci jesli nie to wyrzuci bład
// A do logowania i wylogowywania zrób inny serwis - też jeszcze do obgadania
// sprawa encji użytkowników
// mam pewnien pomysł aby nie zmieniać tego co jest obecnie
public interface AccountService
{
   // REVIEW nie wiem czy listowanie kont nam się przyda, ale to jeszcze do
   // obgadania
   public List<Account> search(AccountQuery query);

   // REVIEW do zmiany tak jak mówiłem - przeniesienie do innego serwisu
   public <T> boolean checkLogin(String userName, String password);

   public <T> Optional<T> findByLogin(String login);

}
