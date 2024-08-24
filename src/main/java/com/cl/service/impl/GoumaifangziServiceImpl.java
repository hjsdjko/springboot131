package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.GoumaifangziDao;
import com.cl.entity.GoumaifangziEntity;
import com.cl.service.GoumaifangziService;
import com.cl.entity.view.GoumaifangziView;

@Service("goumaifangziService")
public class GoumaifangziServiceImpl extends ServiceImpl<GoumaifangziDao, GoumaifangziEntity> implements GoumaifangziService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoumaifangziEntity> page = this.selectPage(
                new Query<GoumaifangziEntity>(params).getPage(),
                new EntityWrapper<GoumaifangziEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GoumaifangziEntity> wrapper) {
		  Page<GoumaifangziView> page =new Query<GoumaifangziView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<GoumaifangziView> selectListView(Wrapper<GoumaifangziEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GoumaifangziView selectView(Wrapper<GoumaifangziEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
