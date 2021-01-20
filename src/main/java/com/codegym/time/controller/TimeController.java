package com.codegym.time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/worldclock")
    public String getTimeByTimezone(ModelMap modelMap, @RequestParam(name="city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city){
        //Get current time in local
        Date date = new Date();
        //Get timezone by local
        TimeZone local = TimeZone.getDefault();
        //Get timezone by spicified city
        TimeZone locale = TimeZone.getTimeZone(city);

        // Calculate the current time in the specified city
        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());

        // Reset the date by locale_time
        date.setTime(locale_time);
        //Set the data sent to view
        modelMap.addAttribute("city",city);
        modelMap.addAttribute("date",date);


        return "index";
    }
}
