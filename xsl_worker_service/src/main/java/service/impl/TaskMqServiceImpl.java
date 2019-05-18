package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pojo.UpdateTaskVo;
import service.TaskMqService;
import util.GsonSingle;


import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class TaskMqServiceImpl implements TaskMqService {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination updateTaskInfo;

	public void sendMq(Destination destination, Object object){
		String s = GsonSingle.getGson().toJson(object);
		jmsTemplate.send(destination, (session -> session.createTextMessage(s)));
	}


	@Override
	public void updateEsTask(UpdateTaskVo updateTaskVo) {
		sendMq(updateTaskInfo,updateTaskVo);
	}

}
