package com.chenpu.backend.service.servicelmpl;

import com.chenpu.backend.domain.Coordinate;
import com.chenpu.backend.domain.Log;
import com.chenpu.backend.domain.OnlineCount;
import com.chenpu.backend.mapper.LogMapper;
import com.chenpu.backend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int insert(Log historylog){return logMapper.insert(historylog);}

    @Override
    public List<Log> getAllHistoryLog(){return logMapper.findAll();}

    @Override
    public List<Log> getHistoryLogById(String deviceid){return logMapper.findById(deviceid);}

    @Override
    public List<Coordinate> getPathByNum(String deviceid){return logMapper.findPathByNum(deviceid);}

    @Override
    public Integer getCountByTime(){return logMapper.findCountByTime();}

    @Override
    public List<OnlineCount> getAllCountByTime(){return logMapper.findAllCountByTime();}

    @Override
    public int getStateById(String deviceid){return logMapper.findStateById(deviceid);}
}
