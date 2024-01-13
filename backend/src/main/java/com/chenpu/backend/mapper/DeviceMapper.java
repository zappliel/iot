package com.chenpu.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.chenpu.backend.domain.Device;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeviceMapper {
    @Insert("INSERT INTO device" +
            "deviceid, devicename,type " +
            "VALUES" +
            "(#{deviceid},#{devicename},#{type})")
    int insert(Device devices);

    @Update("UPDATE device SET devicename = #{devicename}, type =#{type} " +
            "WHERE " +
            "deviceid = #{deviceid}")
    int updateDevice(Device devices);

    @Select("SELECT deviceid,devicename,type,state" +
            " FROM device " +
            "WHERE type = #{type}")
    List<Device> findByType(String type);

    @Select("SELECT deviceid,devicename,type,state" +
            " FROM device " +
            "WHERE deviceid = #{deviceid}")
    Device findByID(String deviceid);

    @Select("SELECT COUNT(distinct deviceid) FROM device")
    int findDevicesCount();

    @Select("SELECT deviceid,devicename,type,state FROM device")
    List<Device> findAllDevices();
}
