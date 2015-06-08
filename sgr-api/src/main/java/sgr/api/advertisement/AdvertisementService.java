package sgr.api.advertisement;

import java.util.List;

public interface AdvertisementService
{
   public List<Advertisement> search();

   public void add(Advertisement advertisement);

   public void delete(Advertisement advertisement);

   public void update(Advertisement advertisement);
}
