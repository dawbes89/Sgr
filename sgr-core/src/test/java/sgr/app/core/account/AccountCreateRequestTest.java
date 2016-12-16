package sgr.app.core.account;
import static org.easymock.EasyMock.eq;
import static org.unitils.easymock.EasyMockUnitils.replay;
import static org.unitils.easymock.EasyMockUnitils.verify;
import static org.easymock.EasyMock.expect;

import java.sql.Date;

import static org.unitils.easymock.EasyMockUnitils.createRegularMock;

import org.junit.Test;

import sgr.app.api.account.AccountType;
import static org.junit.Assert.assertEquals;

public class AccountCreateRequestTest
{

   @Test
   public void testCreate()
   {
      final String password = "password";
      final String login = "login";
      final AccountType type = AccountType.ADMIN;
      final Date validTo = Date.valueOf("2015-12-12");
      
      final PasswordEncoder encoder = createRegularMock(PasswordEncoder.class);
      expect(encoder.encode(eq(password))).andReturn(password);
      
      AccountCreateRequest request = new AccountCreateRequest();
      request.setLogin(login);
      request.setPassword(password);
      request.setType(type);
      request.setValidTo(validTo);
      
      replay();
      
      Account created = request.create(encoder);
      
      verify();
      
      assertEquals(password, created.getPassword());
      assertEquals(login, created.getUserName());
      assertEquals(type, created.getType());
      assertEquals(validTo, created.getValidTo());
      
      
      
      
      
   }

}
