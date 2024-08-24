package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuyuegoufangEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuegoufangView;


/**
 * 预约购房
 *
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
public interface YuyuegoufangService extends IService<YuyuegoufangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuegoufangView> selectListView(Wrapper<YuyuegoufangEntity> wrapper);
   	
   	YuyuegoufangView selectView(@Param("ew") Wrapper<YuyuegoufangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuegoufangEntity> wrapper);
   	

}

