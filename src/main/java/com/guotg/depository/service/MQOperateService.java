package com.guotg.depository.service;

public interface MQOperateService {
    void sendMessage(String topic, String tag, String message) ;
}
