package app.car.car09.interfaces.outcoming

import com.jayway.jsonpath.JsonPath
import net.minidev.json.JSONArray
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.Comparator

@Service
class GMapsService(
        @Value("\${app.car.domain.googlemaps.apikey}")
        val appKey: String,

        @Value("\${interfaces.outcoming.gmaps.host:https://maps.googleapis.com}")
        val gMapsHost: String
) {


    fun getDistanceBetweenAddresses(addressOne: String, addressTwo: String): Int? {
        val template = RestTemplate()
        val jsonResult = template.getForObject(gMapsHost + GMAPS_TEMPLATE, String::class.java, addressOne, addressTwo, appKey)
        val rawResults = JsonPath.parse(jsonResult).read<JSONArray>("$..legs[*].duration.value")
        val results = rawResults.map { it: Any -> it as Int }
        return results.minByOrNull { it }
    }

    companion object {
        private const val GMAPS_TEMPLATE = "/maps/api/directions/json?origin={origin}&destination={destination}&key={key}"
    }
}
