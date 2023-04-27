package com.passtoss.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.passtoss.myhome.domain.Alarm;
import com.passtoss.myhome.domain.Board;

@Mapper
public interface AlarmMapper {

	public int insertAlarmLog(Map<String, Object> alarmInsertMap );
	
	public int readAlarmLog(String iD );

	public List<Map<String, Object>> getAlarmLog(String id);
	
	
	
}
