package hipstershop;

import hipstershop.stubs.Ad;
import hipstershop.stubs.AdRequest;
import hipstershop.stubs.AdResponse;
import hipstershop.stubs.AdServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@GrpcService
public class AdService extends AdServiceGrpc.AdServiceImplBase {

    private static final Logger LOG = Logger.getLogger(AdService.class.getName());

    private static int MAX_ADS_TO_SERVE = 2;
    private static final MultiAdMap adsMap = createAdsMap();

    @Override
    public void getAds(AdRequest request, StreamObserver<AdResponse> responseObserver) {
        List<Ad> allAds = new ArrayList<>();
        if (request.getContextKeysCount() > 0) {
            String keys = "";
            for (int i = 0; i < request.getContextKeysCount(); i++) {
                keys = keys + " " + request.getContextKeys(i);
                Collection<Ad> ads = getAdsByCategory(request.getContextKeys(i));
                if(ads ==  null){
                    LOG.info("Didn't find any ads for context key " + request.getContextKeys(i));
                }
                else {
                    allAds.addAll(ads);
                }
            }
            LOG.info("Received request, context keys: " + keys);
        } else {
            allAds = getRandomAds();
            LOG.info("Received request, no context keys provided");
        }
        if (allAds.isEmpty()) {
            // Serve random ads.
            allAds = getRandomAds();
        }
        AdResponse reply = AdResponse.newBuilder().addAllAds(allAds).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private List<Ad> getRandomAds() {
        Random random = new Random();
        List<Ad> ads = new ArrayList<>(MAX_ADS_TO_SERVE);
        Collection<List<Ad>> allAds = adsMap.values();
        for (int i = 0; i < MAX_ADS_TO_SERVE; i++) {

            Object[] ads1 = allAds.toArray();
            List<Ad> adList = (List<Ad>)ads1[random.nextInt(allAds.size())];
            for (Ad a: adList) {
                ads.add(a);
            }

        }
        return ads;
    }

    private Collection<Ad> getAdsByCategory(String category) {
        return adsMap.get(category);
    }

    private static MultiAdMap createAdsMap() {
        Ad hairdryer =
                Ad.newBuilder()
                        .setRedirectUrl("/product/2ZYFJ3GM2N")
                        .setText("Hairdryer for sale. 50% off.")
                        .build();
        Ad tankTop =
                Ad.newBuilder()
                        .setRedirectUrl("/product/66VCHSJNUP")
                        .setText("Tank top for sale. 20% off.")
                        .build();
        Ad candleHolder =
                Ad.newBuilder()
                        .setRedirectUrl("/product/0PUK6V6EV0")
                        .setText("Candle holder for sale. 30% off.")
                        .build();
        Ad bambooGlassJar =
                Ad.newBuilder()
                        .setRedirectUrl("/product/9SIQT8TOJO")
                        .setText("Bamboo glass jar for sale. 10% off.")
                        .build();
        Ad watch =
                Ad.newBuilder()
                        .setRedirectUrl("/product/1YMWWN1N4O")
                        .setText("Watch for sale. Buy one, get second kit for free")
                        .build();
        Ad mug =
                Ad.newBuilder()
                        .setRedirectUrl("/product/6E92ZMYYFZ")
                        .setText("Mug for sale. Buy two, get third one for free")
                        .build();
        Ad loafers =
                Ad.newBuilder()
                        .setRedirectUrl("/product/L9ECAV7KIM")
                        .setText("Loafers for sale. Buy one, get second one for free")
                        .build();


        MultiAdMap ads = new MultiAdMap();

        ads.put("clothing", tankTop);
        ads.put("accessories", watch);
        ads.put("footwear", loafers);
        ads.put("hair", hairdryer);
        ads.put("decor", candleHolder);
        ads.put("kitchen", bambooGlassJar);
        ads.put("kitchen", mug);

        return ads;
    }
}
