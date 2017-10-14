package net.mobilim.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import net.mobilim.NaviGateData.Entities.Product;
import net.mobilim.NaviGateData.Repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class ProductAvailabilityController {
    private final Logger logger = LoggerFactory.getLogger(ProductAvailabilityController.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    private ProductRepository productRepository;

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
        List<Product> products = productRepository.findAvailableProducts(fromDate, toDate, minDuration, maxDuration,ship, destination);

        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setDateFormat(simpleDateFormat);
        Hibernate5Module module = new Hibernate5Module();
        module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        objectMapper.registerModule(module);
        try {
            String jsonInString = objectMapper.writeValueAsString(products);
            return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
