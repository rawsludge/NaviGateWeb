package com.naviosa.Controllers;

import com.naviosa.NaviGate.WebClient;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateAvailabilityController {
    private final Logger logger = LoggerFactory.getLogger(ProductAvailabilityController.class);

    @Value("${website.url}")
    private String url;

    @RequestMapping(value = "/rate", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getRateInJson() throws Exception{
        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<CruiseLineRequest>\n" +
                "  <MessageHeader SegmentId=\"MSGHDR\">\n" +
                "    <MessageId>CCRATAV1</MessageId>\n" +
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
                "  <RateAvailabilityRequest SegmentId=\"CCRATAV1\">\n" +
                "    <SailingId>K902</SailingId>\n" +
                "    <SailDate>01052019</SailDate>\n" +
                "    <DurationDays>15</DurationDays>\n" +
                "    <Ship Code=\"PA\"/>\n" +
                "    <City Code=\"IST\" />" +
                "    <Currency Code=\"EUR\"/>\n" +
                "    <Transportation Type=\"O\" />" +
                "    <BestFareInd>Y</BestFareInd>\n" +
                "    <IncludeGroupsInd>Y</IncludeGroupsInd>\n" +
                //"    <Guest SeqNumber=\"1\" AgeCode=\"A\"/>\n" +
                //"    <Guest SeqNumber=\"2\" AgeCode=\"A\"/>" +
                "  </RateAvailabilityRequest>\n" +
                "</CruiseLineRequest>";
        WebClient client = new WebClient(url);
        String response = client.Post(xmlData);
        String json = XML.toJSONObject(response.toString()).toString();
        return  json;
    }
}
