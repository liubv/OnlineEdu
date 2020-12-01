package com.lbvguli.msmservice.service;

import java.util.Map;

public interface MsmService {
    boolean send(String phone, Map<String, Object> param);
}
