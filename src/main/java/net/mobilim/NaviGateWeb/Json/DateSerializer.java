package net.mobilim.NaviGateWeb.Json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if( calendar.get(Calendar.YEAR) > 1970) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("date", simpleDateFormat.format(date).toString());
            jsonGenerator.writeNumberField("year", calendar.get(Calendar.YEAR));
            jsonGenerator.writeNumberField("month", calendar.get(Calendar.MONTH) + 1);
            jsonGenerator.writeNumberField("day", calendar.get(Calendar.DAY_OF_MONTH));
            jsonGenerator.writeEndObject();
        }
        else {
            jsonGenerator.writeString(new SimpleDateFormat("HH:mm:ss").format(date));
        }
    }
}
