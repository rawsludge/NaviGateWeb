package net.mobilim.Controllers;

import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteSectorController {

    @Value("${website.url}")
    private String url;

    private String messageID="CCRTSAV1";

    @RequestMapping(value = "/route", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getRouteInJson(@RequestParam(value =  "sailingID", required = true) String sailingID) throws Exception {
/*        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
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
                "  <RouteSectorAvailabilityRequest SegmentId=\"" + messageID + "\">\n" +
                "    <SailingId>%s</SailingId>\n" +
                "    <City Code=\"\"/>\n" +
                "    <NumberOfGuests/>\n" +
                "    <NumberOfSectorsRequested>2</NumberOfSectorsRequested>\n" +
                "    <AdditionalDataRequested>Y</AdditionalDataRequested>\n" +
                "  </RouteSectorAvailabilityRequest>\n" +
                "</CruiseLineRequest>";
        WebClient client = new WebClient(url);
        xmlData = String.format(xmlData, sailingID);
        String response = client.Post(xmlData);
        String json = XML.toJSONObject(response.toString()).toString();*/
        return  "";
    }
}
