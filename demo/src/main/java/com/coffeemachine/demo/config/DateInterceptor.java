package com.coffeemachine.demo.config;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateInterceptor implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JsonNode jsonNode;
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/dates.json")){

            LocalDateTime localDate = LocalDateTime.now();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            DateTimeFormatter day = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

            DateTimeFormatter hour = DateTimeFormatter.ofPattern("H");

            String dtf = localDate.format(dateTimeFormatter);

            String dtf_day = localDate.format(day);

            String dtf_hour = localDate.format(hour);

            jsonNode = objectMapper.readTree(inputStream);

            if(jsonNode.isArray()){
                for(JsonNode jN : jsonNode){
                    String date = jN.get("date").asText();
                    if(dtf.equals(date) || dtf_day.equals("Saturday") || dtf_day.equals("Sunday")  || Integer.parseInt(dtf_hour) < 7 || Integer.parseInt(dtf_hour) > 18 ){
                        response.setStatus(500);
                        response.getWriter().write("Coffee machine is available Monday to Friday,from 8 A.M till 6 P.M, and is closed on weekends and public holidays!Sorry, for the inconvenience!");
                        return false;
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}