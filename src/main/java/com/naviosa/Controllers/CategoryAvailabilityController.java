package com.naviosa.Controllers;

import com.naviosa.NaviGate.WebClient;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryAvailabilityController {
    @Value("${website.url}")
    private String url;

    private String messageID="CCCATAV1";

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getCategoryInJson(
            @RequestParam(value = "sailingID", required = true) String sailingID,
            @RequestParam(value = "sailDate", required = true) String sailDate,
            @RequestParam(value = "durationDays", required = true) String durationDays,
            @RequestParam(value = "shipCode", required = true) String shipCode,
            @RequestParam(value = "cityCode", required = true) String cityCode,
            @RequestParam(value = "rateCode", required = true) String rateCode
    ) throws Exception {
        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<CruiseLineRequest>\n" +
                "  <MessageHeader SegmentId=\"MSGHDR\">\n" +
                "    <MessageId>" + messageID + "</MessageId>\n" +
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
                "  <CategoryAvailabilityRequest SegmentId=\"" + messageID + "\">\n" +
                "    <SailingId>%s</SailingId>\n" +
                "    <SailDate>%s</SailDate>\n" +
                "    <Ship Code=\"%s\" />\n" +
                "    <City Code=\"%s\" />\n" +
                "    <Currency Code=\"EUR\" />\n" +
                "    <Transportation Type=\"O\" />\n" +
                "    <Rate Code=\"%s\" />\n" +
                "    <NumberOfGuests>2</NumberOfGuests>\n" +
                "    <VoyageLimits>Y</VoyageLimits>\n" +
                "    <Guest SeqNumber=\"1\" AgeCode=\"A\"/>\n" +
                "    <Guest SeqNumber=\"2\" AgeCode=\"A\"/>\n" +
                "    <IncludeGroupsInd>Y</IncludeGroupsInd>\n" +
                "  </CategoryAvailabilityRequest>\n" +
                "</CruiseLineRequest>";
        WebClient client = new WebClient(url);
        xmlData = String.format(xmlData, sailingID, sailDate, shipCode, cityCode, rateCode);
        String response = client.Post(xmlData);
        String json = XML.toJSONObject(response.toString()).toString();
        return  json;
    }
}
