package com.chenpu.backend.controller;

import com.chenpu.backend.domain.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chenpu.backend.service.LogService;
import com.chenpu.backend.domain.Log;
import com.chenpu.backend.domain.ResultObject;
import com.chenpu.backend.domain.OnlineCount;

import java.util.List;

@RestController
@RequestMapping("/api/log")
@CrossOrigin
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/list")
    public ResultObject getAllLogs(){
        List<Log> logs = logService.getAllHistoryLog();
        ResultObject resultObject = new ResultObject(200,"success", logs);
        return resultObject;
    }

    @GetMapping("/get")
    public ResultObject getHistoryLogById(@RequestParam String deviceid){
        List<Log> logs = logService.getHistoryLogById(deviceid);
        ResultObject resultObject = new ResultObject(200,"success", logs);
        return resultObject;
    }

    @GetMapping("/path")
    public ResultObject getPathById(@RequestParam String deviceid){
        System.out.println(deviceid);
        List<Coordinate> logs = logService.getPathByNum(deviceid);
        ResultObject resultObject = new ResultObject(200,"success", logs);
        System.out.println(logs);
        return resultObject;
    }

    @GetMapping("/online")
    public ResultObject getCountByTime(){
        Integer logs = logService.getCountByTime();
        ResultObject resultObject = new ResultObject(200,"success",logs);
        return  resultObject;
    }

    @GetMapping("/countall")
    public ResultObject getValueByTimeClient(){
        List<OnlineCount> logs = logService.getAllCountByTime();
        ResultObject resultObject = new ResultObject(200,"success",logs);
        return  resultObject;
    }

    @GetMapping("/state")
    public ResultObject getDeviceStateById(@RequestParam String deviceid){
        int state = logService.getStateById(deviceid);
        ResultObject resultObject = new ResultObject(200,"success", state);
        return resultObject;
    }

}
