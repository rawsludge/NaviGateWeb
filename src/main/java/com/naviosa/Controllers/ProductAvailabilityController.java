package com.naviosa.Controllers;

import com.naviosa.Models.ProductAvailabilityResponse;
import com.naviosa.NaviGate.WebClient;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;


@RestController
public class ProductAvailabilityController {
    private final Logger logger = LoggerFactory.getLogger(ProductAvailabilityController.class);

    @Value("${website.url}")
    private String url;

    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getProductInJson(
            @RequestParam(value =  "dateFrom", required = true) String dateFrom,
            @RequestParam(value = "dateTo", required = true) String dateTo,
            @RequestParam(value = "durationMin", required = true) String durationMin,
            @RequestParam(value = "durationMax", required = true) String durationMax,
            @RequestParam(value = "shipCode", required = false) String shipCode,
            @RequestParam(value = "destCode", required = false) String destCode ) throws Exception {

        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<CruiseLineRequest>\n" +
                "  <MessageHeader SegmentId=\"MSGHDR\">\n" +
                "    <MessageId>CCPROAV1</MessageId>\n" +
                "    <SessionId>1234</SessionId>\n" +
                "    <CruiseLineCode>PCL</CruiseLineCode>\n" +
                "    <UserId>optional</UserId>\n" +
                "    <UserName>username</UserName>\n" +
                "    <SystemId>E4</SystemId>\n" +
                "    <AgencyId>1319</AgencyId>\n" +
                "    <UICode>3</UICode>\n" +
                "    <VersionNum>3.0</VersionNum>\n" +
                "    <AttemptCnt>1</AttemptCnt>\n" +
                "    <SendDescriptionInd>Y</SendDescriptionInd>\n" +
                "    <Copyright>Copyright (C) 2000 Carnival Corporation.  All rights reserved.</Copyright>\n" +
                "  </MessageHeader>\n" +
                "  <ProductAvailabilityRequest SegmentId=\"CCPROAV1\">\n" +
                "    <DepDate From=\"%s\" To=\"%s\" />\n" +
                "    <DurationDays Minimum=\"%s\" Maximum=\"%s\" />\n" +
                "    <Ship Code=\"%s\"/>" +
                "    <Destination Code=\"%s\" />\n" +
                "  </ProductAvailabilityRequest>\n" +
                "</CruiseLineRequest>";
        xmlData = String.format(xmlData, dateFrom, dateTo, durationMin, durationMax, shipCode, destCode);

        WebClient client = new WebClient(url);
        String response = client.Post(xmlData);
        String json = XML.toJSONObject(response.toString()).toString();
        return  json;
    }
}
