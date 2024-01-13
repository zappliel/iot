package com.chenpu.backend.service;

import com.chenpu.backend.domain.Device;
import com.chenpu.backend.domain.OnlineCount;
import com.chenpu.backend.mapper.DeviceMapper;
import com.chenpu.backend.domain.PageObject;

import java.util.List;

public interface DeviceService {
    //插入device
    public int insertDevice(Device devices);
    //
    public Device getDeviceById(String deviceid);

    public List<Device> getAllDevices();

    public List<Device> getDevicesByType(String type);

    public int updateDevice(Device devices);

    public int getDevicesCount();

    public PageObject getDeviceByType(int page_num,int page_size,String type);

    public PageObject getAllDevices(int page_num,int page_size);
}
