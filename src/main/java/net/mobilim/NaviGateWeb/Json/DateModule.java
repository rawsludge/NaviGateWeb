package net.mobilim.NaviGateWeb.Json;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

public class DateModule extends SimpleModule {

    public DateModule() {
        super();
        addSerializer(Date.class, new DateSerializer());
    }
}
