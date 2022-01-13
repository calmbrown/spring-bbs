package com.app.app.Dao;

import java.util.ArrayList;
import java.util.Map;

import com.app.app.Dto.BbsDto;

public interface BDao {
	public void Write(Map map);

	public ArrayList<BbsDto> list();
	public BbsDto detail(String bbs_id);
	public void Edit(Map map);
	public void Delete(String bbs_id);
}
