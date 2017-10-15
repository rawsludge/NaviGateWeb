package net.mobilim.NaviGateWeb.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import net.mobilim.NaviGateData.Entities.Product;
import net.mobilim.NaviGateData.Repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class ProductAvailabilityController {
    private final Logger logger = LogManager.getLogger(ProductAvailabilityController.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> getProductInJson(
            @RequestParam(value = "dateFrom", required = true) String dateFrom,
            @RequestParam(value = "dateTo", required = true) String dateTo,
            @RequestParam(value = "durationMin", required = true) String durationMin,
            @RequestParam(value = "durationMax", required = true) String durationMax,
            @RequestParam(value = "shipCode", required = false) String shipCode,
            @RequestParam(value = "destCode", required = false) String destCode ) throws Exception {
        Date fromDate = simpleDateFormat.parse(dateFrom);
        Date toDate = simpleDateFormat.parse(dateTo);
        Integer minDuration = Integer.parseInt(durationMin);
        Integer maxDuration = Integer.parseInt(durationMax);
        String ship = shipCode == null ? "%" : shipCode.concat("%");
        String destination = destCode == null ? "%" : destCode.concat("%");
        logger.info("findAvailableProducts is being called. fromData:{}, toDate:{}, minDuration:{}, maxDuration:{}, ship:{}, destination:{}",
                fromDate, toDate, minDuration, maxDuration, ship, destination);
        List<Product> products = productRepository.findAvailableProducts(fromDate, toDate, minDuration, maxDuration, ship, destination);
        logger.info("findAvailableProducts called. {} product(s) found", products.size());


        try {
            logger.info("writeValueAsString is being called");
            String jsonInString = objectMapper.writeValueAsString(products);
            logger.info("writeValueAsString called");
            return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
