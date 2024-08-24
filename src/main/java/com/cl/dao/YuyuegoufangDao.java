package com.cl.dao;

import com.cl.entity.YuyuegoufangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuegoufangView;


/**
 * 预约购房
 * 
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
public interface YuyuegoufangDao extends BaseMapper<YuyuegoufangEntity> {
	
	List<YuyuegoufangView> selectListView(@Param("ew") Wrapper<YuyuegoufangEntity> wrapper);

	List<YuyuegoufangView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuegoufangEntity> wrapper);
	
	YuyuegoufangView selectView(@Param("ew") Wrapper<YuyuegoufangEntity> wrapper);
	

}
