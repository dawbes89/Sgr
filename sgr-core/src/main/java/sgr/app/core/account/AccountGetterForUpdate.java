package sgr.app.core.account;

/**
 * Created by leonzio on 2016-12-09.
 */
@FunctionalInterface
public interface AccountGetterForUpdate
{
	Account get(Long id);
}
