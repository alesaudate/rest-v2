package app.car.cap09.interfaces.outcoming;


import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GMapsService {


    @Value("${app.car.domain.googlemaps.apikey}")
    private String appKey;

    @Value("${interfaces.outcoming.gmaps.host:https://maps.googleapis.com}")
    private String gMapsHost;

    private static final String GMAPS_TEMPLATE = "/maps/api/directions/json?origin={origin}&destination={destination}&key={key}";

    public Integer getDistanceBetweenAddresses(String addressOne, String addressTwo) {

        RestTemplate template = new RestTemplate();
        String jsonResult = template.getForObject(gMapsHost + GMAPS_TEMPLATE, String.class, addressOne, addressTwo, appKey);

        List<Integer> results = JsonPath.parse(jsonResult).read("$..legs[*].duration.value");
        return results.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }

}
