package com.chenpu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chenpu.backend.domain.ResultObject;
import com.chenpu.backend.domain.Join;
import com.chenpu.backend.service.JoinService;

import java.util.List;

@RestController
@RequestMapping("/api/join")
@CrossOrigin
public class JoinController {

    @Autowired
    private JoinService joinService;

    @GetMapping("/list")
    public ResultObject getAllJoin(){
        List<Join> joins = joinService.findall();
        ResultObject resultObject = new ResultObject(200,"success", joins);
        return resultObject;
    }

    @GetMapping("/search")
    public ResultObject getSearch(@RequestParam String keyword){
        List<Join> joins = joinService.findSearch(keyword);
        ResultObject resultObject = new ResultObject(200,"success", joins);
        return resultObject;
    }

}
