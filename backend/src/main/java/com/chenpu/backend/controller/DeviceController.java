package com.chenpu.backend.controller;

import com.chenpu.backend.domain.OnlineCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chenpu.backend.mapper.DeviceMapper;
import com.chenpu.backend.service.DeviceService;
import com.chenpu.backend.domain.ResultObject;
import com.chenpu.backend.domain.Device;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/api/device")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/update")
    public ResultObject updateDevices(@RequestBody Device de){
        Device devices = new Device();
        devices.setDeviceid(de.getDeviceid());
        devices.setDevicename(de.getDevicename());
        devices.setType(de.getType());
        System.out.println(de.getDeviceid()+de.getDevicename()+de.getType());
        int modifyid = deviceService.updateDevice(devices);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("modifyId", modifyid);
        ResultObject resultObject = new ResultObject(200, "success", map);
        return resultObject;
    }

    @GetMapping("/type")
    public ResultObject getDevicesByType(@RequestParam String type){
        List<Device> devices = deviceService.getDevicesByType(type);
        ResultObject resultObject = new ResultObject(200, "success", devices);
        return resultObject;
    }

    @GetMapping("/get")
    public ResultObject getDevicesById(@RequestParam String deviceid){
        Device devices = deviceService.getDeviceById(deviceid);
        System.out.println(deviceid);
        ResultObject resultObject = new ResultObject(200, "success", devices);
        System.out.println(devices);
        return resultObject;
    }

    @GetMapping("/list")
    public ResultObject getAllDevices(){
        List<Device> devices = deviceService.getAllDevices();
        ResultObject resultObject = new ResultObject(200, "success", devices);
        return resultObject;
    }

    @GetMapping("/count")
    public ResultObject getDevicesCount(){
        int count = deviceService.getDevicesCount();
        ResultObject resultObject = new ResultObject(count,"success", null);
        return  resultObject;
    }

    @PostMapping("/add")
    public ResultObject insertDevice(@RequestParam String deviceid,String devicename,String type,int state){
        Device devices=new Device();
        devices.setDeviceid(deviceid);
        devices.setDevicename(devicename);
        devices.setType(type);
        devices.setState(state);
        int modifyid = deviceService.insertDevice(devices);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("modifyId", modifyid);
        ResultObject resultObject = new ResultObject(200, "success", map);
        return resultObject;
    }
}
