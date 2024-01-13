package com.chenpu.backend.service;

import com.chenpu.backend.domain.Join;

import java.util.List;

public interface JoinService {

    public List<Join> findall();

    public List<Join> findSearch(String keyword);
}
