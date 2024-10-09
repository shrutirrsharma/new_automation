package com.example.automation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.automation.Model.RealTimeModel;
@Repository
public interface RealTimeInterface extends JpaRepository<RealTimeModel, Long>{
	@Procedure("get_Tbl_RealTimeData2")
	public String fetchRealtimedata();
	
	@Procedure("get_Tbl_RealTimeBind")
	public List<RealTimeModel> getRealTime(int locationId, int DepartmentId, int BotId);

	//public Integer FunctionName(int LocationId, int DepartmentId, int BotId);
}
