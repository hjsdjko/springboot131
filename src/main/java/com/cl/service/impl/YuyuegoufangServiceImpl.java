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


import com.cl.dao.YuyuegoufangDao;
import com.cl.entity.YuyuegoufangEntity;
import com.cl.service.YuyuegoufangService;
import com.cl.entity.view.YuyuegoufangView;

@Service("yuyuegoufangService")
public class YuyuegoufangServiceImpl extends ServiceImpl<YuyuegoufangDao, YuyuegoufangEntity> implements YuyuegoufangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuegoufangEntity> page = this.selectPage(
                new Query<YuyuegoufangEntity>(params).getPage(),
                new EntityWrapper<YuyuegoufangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuegoufangEntity> wrapper) {
		  Page<YuyuegoufangView> page =new Query<YuyuegoufangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YuyuegoufangView> selectListView(Wrapper<YuyuegoufangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuegoufangView selectView(Wrapper<YuyuegoufangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
