package com.chenpu.backend.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.chenpu.backend.domain.Join;

import java.util.List;

@Mapper
public interface JoinMapper {

    @Select(" SELECT * FROM (SELECT device.deviceid,devicename,type,logtimestamp,info,lat,ing,devicevalue, device.state,\n" +
            " RANK() OVER (partition by device.deviceid ORDER BY logtimestamp DESC) as NUM\n" +
            " from (device left join log on device.deviceid = log.deviceid)) as tm WHERE NUM < 2")
    List<Join> findAll();

    @Select("SELECT * FROM (SELECT device.deviceid,devicename,type,logtimestamp,info,lat,ing,devicevalue, device.state,\n" +
            "            RANK() OVER (partition by device.deviceid ORDER BY logtimestamp DESC) as NUM\n" +
            "            from (device left join log on device.deviceid = log.deviceid)) as tm\n" +
            "            WHERE NUM < 2 AND (deviceid like #{keyword} OR\n" +
            "            devicename like #{keyword} OR type like #{keyword})")
    List<Join> findSearch(String keyword);
}
