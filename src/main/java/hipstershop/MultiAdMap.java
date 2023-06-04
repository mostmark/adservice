package hipstershop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import hipstershop.stubs.Ad;

public class MultiAdMap {

    private HashMap<String, List<Ad>> ads;

    public MultiAdMap(){
        ads = new HashMap<String, List<Ad>>();
    }

    public void put(String category, Ad ad){
        if(ads.containsKey(category)){
            ads.get(category).add(ad);
        }
        else {
            ArrayList<Ad> a = new ArrayList<Ad>();
            a.add(ad);
            ads.put(category, a);
        }
    }

    public List<Ad> get(String category){
        return ads.get(category);
    }

    public Collection<List<Ad>> values(){
        return ads.values();
    }

}
