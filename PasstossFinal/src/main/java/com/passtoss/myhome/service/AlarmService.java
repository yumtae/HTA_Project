package com.passtoss.myhome.service;

import java.util.List;
import java.util.Map;

import com.passtoss.myhome.domain.Alarm;
import com.passtoss.myhome.domain.Board;

public interface AlarmService {


	public int insertAlarmLog(Map<String, Object> alarmInsertMap );
	
	public int readAlarmLog(String ID );

	public List<Map<String, Object>> getAlarmLog(String id);

	
	
	
}
