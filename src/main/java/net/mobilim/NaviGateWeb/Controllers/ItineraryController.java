package net.mobilim.NaviGateWeb.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mobilim.NaviGateData.Entities.Itinerary;
import net.mobilim.NaviGateData.Entities.Product;
import net.mobilim.NaviGateData.Repositories.ItineraryRepository;
import net.mobilim.NaviGateData.Repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class ItineraryController {
    private final Logger logger = LogManager.getLogger(ItineraryController.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @RequestMapping(value = "/itinerary", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getItinerariesInJson(
            @RequestParam(value = "dateFrom", required = true) String dateFrom,
            @RequestParam(value = "dateTo", required = true) String dateTo,
            @RequestParam(value = "durationMin", required = true) String durationMin,
            @RequestParam(value = "durationMax", required = true) String durationMax,
            @RequestParam(value = "shipCode", required = false) String shipCode,
            @RequestParam(value = "destCode", required = false) String destCode,
            Pageable pageable) throws Exception {
        Date fromDate = simpleDateFormat.parse(dateFrom);
        Date toDate = simpleDateFormat.parse(dateTo);
        Integer minDuration = Integer.parseInt(durationMin);
        Integer maxDuration = Integer.parseInt(durationMax);
        String ship = shipCode == null ? "%" : shipCode.concat("%");
        String destination = destCode == null ? "%" : destCode.concat("%");
        logger.info("findByProducts is being called. fromData:{}, toDate:{}, minDuration:{}, maxDuration:{}, ship:{}, destination:{}",
                fromDate, toDate, minDuration, maxDuration, ship, destination);
        Page<Itinerary> itineraries = itineraryRepository.findByProducts(fromDate, toDate, minDuration, maxDuration, ship, destination, pageable);
        logger.info("findAvailableProducts called. {} itinerary(s) found", itineraries.getSize());


        try {
            logger.info("writeValueAsString is being called");
            String jsonInString = objectMapper.writeValueAsString(itineraries);
            logger.info("writeValueAsString called");
            return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
