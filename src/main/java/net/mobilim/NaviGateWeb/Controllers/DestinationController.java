package net.mobilim.NaviGateWeb.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mobilim.NaviGateData.Entities.Destination;
import net.mobilim.NaviGateData.Repositories.DestinationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DestinationController {
    private final Logger logger = LogManager.getLogger(ItineraryController.class);

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/destination/list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getDestinationList() {
        List<Destination> destinationList = destinationRepository.findAll();

        try {
            logger.info("writeValueAsString is being called");
            String jsonInString = objectMapper.writeValueAsString(destinationList);
            logger.info("writeValueAsString called");
            return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
