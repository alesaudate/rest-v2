package app.car.cap06.interfaces.outcoming;


import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Setter;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        JSONArray rawResults = JsonPath.parse(jsonResult).read("$..legs[*].duration.value");

        List<Integer> results = rawResults.stream().map(it -> ((Integer) it)).collect(Collectors.toList());

        return results.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }
}
