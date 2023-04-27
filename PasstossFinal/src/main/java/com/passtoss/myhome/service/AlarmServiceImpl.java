package com.passtoss.myhome.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passtoss.myhome.domain.Alarm;
import com.passtoss.myhome.domain.Board;
import com.passtoss.myhome.mybatis.mapper.AlarmMapper;

@Service
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	private AlarmMapper dao;


	@Override
	public int readAlarmLog(String ID ) {
		return dao.readAlarmLog(ID);
		
	}
	@Override
	public List<Map<String, Object>> getAlarmLog(String id) {
		return dao.getAlarmLog(id);
	}
	@Override
	public int insertAlarmLog(Map<String, Object> alarmInsertMap) {
		return dao.insertAlarmLog(alarmInsertMap);
	}
	
	
	
}
