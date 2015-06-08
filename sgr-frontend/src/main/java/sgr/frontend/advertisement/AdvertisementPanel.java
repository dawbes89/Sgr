package sgr.frontend.advertisement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.api.advertisement.Advertisement;
import sgr.api.advertisement.AdvertisementService;

@Controller
@ManagedBean(name = "advertisementPanel")
@ViewScoped
public class AdvertisementPanel implements Serializable
{

   private static final long serialVersionUID = 7533733012660354175L;

   @Autowired
   private AdvertisementService advertisementService;

   private Advertisement advertisement = new Advertisement();

   private List<Advertisement> advertisementsList;

   @PostConstruct
   public void init()
   {
      setAdvertisementsList(advertisementService.search());
   }

   public AdvertisementService getAdvertisementService()
   {
      return advertisementService;
   }

   public void setAdvertisementService(AdvertisementService advertisementService)
   {
      this.advertisementService = advertisementService;
   }

   public Advertisement getAdvertisement()
   {
      return advertisement;
   }

   public void setAdvertisement(Advertisement advertisement)
   {
      this.advertisement = advertisement;
   }

   public List<Advertisement> getAdvertisementsList()
   {
      return advertisementsList;
   }

   public void setAdvertisementsList(List<Advertisement> advertisementsList)
   {
      this.advertisementsList = advertisementsList;
   }
}
