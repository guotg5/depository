package com.guotg.depository.service.impl;

import com.guotg.depository.service.MQOperateService;
import com.guotg.depository.util.Logger;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQOperateServiceImpl implements MQOperateService {

    /**
     * 生产者的组名
     */
    @Value("${depository.rocketmq.producerGroup}")
    private static String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${depository.rocketmq.namesrvaddr}")
    private String namesrvAddr;

    private DefaultMQProducer producer;

    private void initProcucer() throws MQClientException {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
    }

    @Override
    public void sendMessage(String topic, String tag, String json) {

        try {
            if(producer == null){
                initProcucer();
            }

            Message message = new Message(topic, tag, json.getBytes(RemotingHelper.DEFAULT_CHARSET));

            producer.sendOneway(message);

        } catch (Exception e) {
            Logger.error(e);
            //失败后应该持久化到数据库  定时任务轮询处理  或者提供两种方案
        } finally {
            producer.shutdown();
        }
    }

}
