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

import com.cl.entity.GoumaifangziEntity;
import com.cl.entity.view.GoumaifangziView;

import com.cl.service.GoumaifangziService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 购买房子
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
@RestController
@RequestMapping("/goumaifangzi")
public class GoumaifangziController {
    @Autowired
    private GoumaifangziService goumaifangziService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GoumaifangziEntity goumaifangzi,
                @RequestParam(required = false) Double xiaoshoujiagestart,
                @RequestParam(required = false) Double xiaoshoujiageend,
		HttpServletRequest request){
        EntityWrapper<GoumaifangziEntity> ew = new EntityWrapper<GoumaifangziEntity>();
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
		PageUtils page = goumaifangziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, goumaifangzi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,GoumaifangziEntity goumaifangzi, 
                @RequestParam(required = false) Double xiaoshoujiagestart,
                @RequestParam(required = false) Double xiaoshoujiageend,
		HttpServletRequest request){
        EntityWrapper<GoumaifangziEntity> ew = new EntityWrapper<GoumaifangziEntity>();
                if(xiaoshoujiagestart!=null) ew.ge("xiaoshoujiage", xiaoshoujiagestart);
                if(xiaoshoujiageend!=null) ew.le("xiaoshoujiage", xiaoshoujiageend);

		PageUtils page = goumaifangziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, goumaifangzi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GoumaifangziEntity goumaifangzi){
       	EntityWrapper<GoumaifangziEntity> ew = new EntityWrapper<GoumaifangziEntity>();
      	ew.allEq(MPUtil.allEQMapPre( goumaifangzi, "goumaifangzi")); 
        return R.ok().put("data", goumaifangziService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GoumaifangziEntity goumaifangzi){
        EntityWrapper< GoumaifangziEntity> ew = new EntityWrapper< GoumaifangziEntity>();
 		ew.allEq(MPUtil.allEQMapPre( goumaifangzi, "goumaifangzi")); 
		GoumaifangziView goumaifangziView =  goumaifangziService.selectView(ew);
		return R.ok("查询购买房子成功").put("data", goumaifangziView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GoumaifangziEntity goumaifangzi = goumaifangziService.selectById(id);
		goumaifangzi = goumaifangziService.selectView(new EntityWrapper<GoumaifangziEntity>().eq("id", id));
        return R.ok().put("data", goumaifangzi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GoumaifangziEntity goumaifangzi = goumaifangziService.selectById(id);
		goumaifangzi = goumaifangziService.selectView(new EntityWrapper<GoumaifangziEntity>().eq("id", id));
        return R.ok().put("data", goumaifangzi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GoumaifangziEntity goumaifangzi, HttpServletRequest request){
    	goumaifangzi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(goumaifangzi);
        goumaifangziService.insert(goumaifangzi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody GoumaifangziEntity goumaifangzi, HttpServletRequest request){
    	goumaifangzi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(goumaifangzi);
        goumaifangziService.insert(goumaifangzi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody GoumaifangziEntity goumaifangzi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(goumaifangzi);
        goumaifangziService.updateById(goumaifangzi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        goumaifangziService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
