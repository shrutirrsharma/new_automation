package com.example.automation.Repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.automation.Model.BotNameModel;

public interface iBotNameRepository extends JpaRepository<BotNameModel,Long>{	
	@Procedure("get_Tbl_BotName")
	public String fetchBotNameFromStoredProcedure();
}
