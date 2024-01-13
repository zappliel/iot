package com.chenpu.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Join {
    private String deviceid;
    private String devicename;
    private String type;
    private String logtimestamp;
    private String info;
    private double lat;
    private double ing;
    private int devicevalue;
    private int state;
}
