package com.chenpu.backend.mapper;

import com.chenpu.backend.domain.Coordinate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.chenpu.backend.domain.Device;
import com.chenpu.backend.domain.Log;
import org.apache.ibatis.annotations.Select;
import com.chenpu.backend.domain.OnlineCount;

import java.util.List;

@Mapper
public interface LogMapper {
    @Insert("INSERT INTO log" +
            "deviceid, logtimestamp,info,lat,ing,devicevalue,state" +
            "VALUES(#{deviceid},#{logtimestamp},#{info}," +
            "#{lat},#{ing},#{devicevalue},#{state})")
    int insert(Log historylog);

    @Select("SELECT deviceid, logtimestamp,info,lat,ing,devicevalue,state" +
            " FROM log WHERE deviceid = #{deviceid} ORDER BY logtimestamp")
    List<Log> findById(String deviceid);

    @Select("SELECT deviceid, logtimestamp,info,lat,ing,devicevalue,state" +
            " FROM log ORDER BY deviceid, logtimestamp desc")
    List<Log> findAll();

    @Select("SELECT lat, ing FROM log\n" +
            "WHERE deviceid = #{deviceid} ORDER BY logtimestamp DESC\n" +
            "LIMIT 10;")
    List<Coordinate> findPathByNum(String deviceid);

    //返回现在的在线数
    @Select(" SELECT COUNT(distinct deviceid) as online_count\n" +
            "            FROM log;")
    Integer findCountByTime();

    //返回所有按时间的信息量
    @Select(" SELECT COUNT(deviceid) as online_count," +
            " DATE_FORMAT(logtimestamp,'%M %d,%H:%i') " +
            "as times FROM log group by times  " +
            "order by times")
    List<OnlineCount> findAllCountByTime();

    @Select(" SELECT state\n" +
            " FROM device" +
            " WHERE deviceid = #{deviceid}")
    int findStateById(String deviceid);
}
