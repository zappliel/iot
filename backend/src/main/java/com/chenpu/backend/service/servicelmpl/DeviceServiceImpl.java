package com.chenpu.backend.service.servicelmpl;

import com.chenpu.backend.domain.Device;
import com.chenpu.backend.domain.PageObject;
import com.chenpu.backend.mapper.DeviceMapper;
import com.chenpu.backend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public int insertDevice(Device devices) {
        return deviceMapper.insert(devices);
    }

    @Override
    public Device getDeviceById(String deviceid) {
        return deviceMapper.findByID((deviceid));
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceMapper.findAllDevices();
    }

    @Override
    public List<Device> getDevicesByType(String type) {
        return deviceMapper.findByType(type);
    }

    @Override
    public int updateDevice(Device devices) {
        return deviceMapper.updateDevice(devices);
    }

    @Override
    public int getDevicesCount(){return deviceMapper.findDevicesCount();}

    @Override
    public PageObject getDeviceByType(int page_num,int page_size, String type) {
        PageHelper.startPage(page_num, page_size);
        List<Device> devices = deviceMapper.findByType(type);
        PageInfo<Device> appsPageInfo = new PageInfo<Device>(devices);
        long total = appsPageInfo.getTotal();
        PageObject pageObject = new PageObject(devices, page_num, page_size, total);
        return pageObject;
    }

    @Override
    public PageObject getAllDevices(int page_num,int page_size) {
        PageHelper.startPage(page_num, page_size);
        List<Device> devices = deviceMapper.findAllDevices();
        PageInfo<Device> appsPageInfo = new PageInfo<Device>(devices);
        long total = appsPageInfo.getTotal();
        PageObject pageObject = new PageObject(devices, page_num, page_size, total);
        return pageObject;
    }
}
