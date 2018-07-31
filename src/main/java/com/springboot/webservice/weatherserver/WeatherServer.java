package com.springboot.webservice.weatherserver;

import com.springboot.webservice.impl.WeatherInterfaceImpl;

import javax.xml.ws.Endpoint;

public class WeatherServer {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1/weather", new WeatherInterfaceImpl());
    }
}
