package com.chenpu.backend.service.servicelmpl;

import com.chenpu.backend.mapper.JoinMapper;
import com.chenpu.backend.service.JoinService;
import com.chenpu.backend.domain.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    private JoinMapper joinMapper;

    @Override
    public List<Join> findall() {
        return joinMapper.findAll();
    }

    @Override
    public List<Join> findSearch(String keyword) {
        return joinMapper.findSearch(keyword);
    }

}
