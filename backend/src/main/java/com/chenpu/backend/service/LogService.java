package com.chenpu.backend.service;

import com.chenpu.backend.domain.Coordinate;
import com.chenpu.backend.domain.Log;
import com.chenpu.backend.domain.OnlineCount;

import java.util.List;

public interface LogService {

    public int insert(Log historylog);

    public List<Log> getAllHistoryLog();

    public List<Log> getHistoryLogById(String deviceid);

    public List<Coordinate> getPathByNum(String deviceid);

    public Integer getCountByTime();

    public List<OnlineCount> getAllCountByTime();

    public int getStateById(String deviceid);
}
