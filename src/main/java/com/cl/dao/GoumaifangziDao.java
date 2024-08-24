package com.cl.dao;

import com.cl.entity.GoumaifangziEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.GoumaifangziView;


/**
 * 购买房子
 * 
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
public interface GoumaifangziDao extends BaseMapper<GoumaifangziEntity> {
	
	List<GoumaifangziView> selectListView(@Param("ew") Wrapper<GoumaifangziEntity> wrapper);

	List<GoumaifangziView> selectListView(Pagination page,@Param("ew") Wrapper<GoumaifangziEntity> wrapper);
	
	GoumaifangziView selectView(@Param("ew") Wrapper<GoumaifangziEntity> wrapper);
	

}
