package sgr.app.api.account;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import static org.unitils.easymock.EasyMockUnitils.replay;

public class AccountDTOTest
{
   

   @Test
   public void test()
   {
      AccountDTO account = new AccountDTO();
      account.setId(1L);
      replay();
      assertTrue(account !=null);
      
      
   }

}
