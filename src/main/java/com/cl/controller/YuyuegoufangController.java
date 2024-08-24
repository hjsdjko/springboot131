package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.YuyuegoufangEntity;
import com.cl.entity.view.YuyuegoufangView;

import com.cl.service.YuyuegoufangService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 预约购房
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
@RestController
@RequestMapping("/yuyuegoufang")
public class YuyuegoufangController {
    @Autowired
    private YuyuegoufangService yuyuegoufangService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyuegoufangEntity yuyuegoufang,
                @RequestParam(required = false) Double xiaoshoujiagestart,
                @RequestParam(required = false) Double xiaoshoujiageend,
		HttpServletRequest request){
        EntityWrapper<YuyuegoufangEntity> ew = new EntityWrapper<YuyuegoufangEntity>();
                if(xiaoshoujiagestart!=null) ew.ge("xiaoshoujiage", xiaoshoujiagestart);
                if(xiaoshoujiageend!=null) ew.le("xiaoshoujiage", xiaoshoujiageend);

        String tableName = request.getSession().getAttribute("tableName").toString();
        ew.andNew();
        if(tableName.equals("yonghu")) {
            ew.eq("yonghuming", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("yonghu")) {
            ew.or();
            ew.eq("maijia", (String)request.getSession().getAttribute("username"));
        }
		PageUtils page = yuyuegoufangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuegoufang), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyuegoufangEntity yuyuegoufang, 
                @RequestParam(required = false) Double xiaoshoujiagestart,
                @RequestParam(required = false) Double xiaoshoujiageend,
		HttpServletRequest request){
        EntityWrapper<YuyuegoufangEntity> ew = new EntityWrapper<YuyuegoufangEntity>();
                if(xiaoshoujiagestart!=null) ew.ge("xiaoshoujiage", xiaoshoujiagestart);
                if(xiaoshoujiageend!=null) ew.le("xiaoshoujiage", xiaoshoujiageend);

		PageUtils page = yuyuegoufangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuegoufang), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyuegoufangEntity yuyuegoufang){
       	EntityWrapper<YuyuegoufangEntity> ew = new EntityWrapper<YuyuegoufangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuegoufang, "yuyuegoufang")); 
        return R.ok().put("data", yuyuegoufangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyuegoufangEntity yuyuegoufang){
        EntityWrapper< YuyuegoufangEntity> ew = new EntityWrapper< YuyuegoufangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuegoufang, "yuyuegoufang")); 
		YuyuegoufangView yuyuegoufangView =  yuyuegoufangService.selectView(ew);
		return R.ok("查询预约购房成功").put("data", yuyuegoufangView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyuegoufangEntity yuyuegoufang = yuyuegoufangService.selectById(id);
		yuyuegoufang = yuyuegoufangService.selectView(new EntityWrapper<YuyuegoufangEntity>().eq("id", id));
        return R.ok().put("data", yuyuegoufang);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyuegoufangEntity yuyuegoufang = yuyuegoufangService.selectById(id);
		yuyuegoufang = yuyuegoufangService.selectView(new EntityWrapper<YuyuegoufangEntity>().eq("id", id));
        return R.ok().put("data", yuyuegoufang);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YuyuegoufangEntity yuyuegoufang, HttpServletRequest request){
    	yuyuegoufang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuyuegoufang);
        yuyuegoufangService.insert(yuyuegoufang);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YuyuegoufangEntity yuyuegoufang, HttpServletRequest request){
    	yuyuegoufang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuyuegoufang);
        yuyuegoufangService.insert(yuyuegoufang);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YuyuegoufangEntity yuyuegoufang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuegoufang);
        yuyuegoufangService.updateById(yuyuegoufang);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyuegoufangEntity> list = new ArrayList<YuyuegoufangEntity>();
        for(Long id : ids) {
            YuyuegoufangEntity yuyuegoufang = yuyuegoufangService.selectById(id);
            yuyuegoufang.setSfsh(sfsh);
            yuyuegoufang.setShhf(shhf);
            list.add(yuyuegoufang);
        }
        yuyuegoufangService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yuyuegoufangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
