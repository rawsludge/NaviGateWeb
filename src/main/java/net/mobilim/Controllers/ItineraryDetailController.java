package net.mobilim.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItineraryDetailController {

    private String messageID="CCPROIT1";


    @RequestMapping(value = "/itinerary", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getItineraryInJson(
            @RequestParam(value = "sailingID", required = true) String sailingID,
            @RequestParam(value = "sailDate", required = true) String sailDate,
            @RequestParam(value = "durationDays", required = true) String durationDays,
            @RequestParam(value = "shipCode", required = true) String shipCode
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
                "  <ItineraryRequest SegmentId=\"" + messageID.substring(2) + "\">\n" +
                "    <SailingId>%s</SailingId>\n" +
                "    <SailDate>%s</SailDate>\n" +
                "    <DurationDays>%s</DurationDays>\n" +
                "    <Ship Code=\"%s\"/>\n" +
                "  </ItineraryRequest>\n" +
                "</CruiseLineRequest>";
/*        WebClient client = new WebClient(url);
        xmlData = String.format(xmlData, sailingID, sailDate, durationDays, shipCode);
        String response = client.Post(xmlData);
        String json = XML.toJSONObject(response.toString()).toString();*/
        return  "";
    }

}
