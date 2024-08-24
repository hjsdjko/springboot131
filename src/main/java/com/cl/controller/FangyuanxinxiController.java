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

import com.cl.entity.FangyuanxinxiEntity;
import com.cl.entity.view.FangyuanxinxiView;

import com.cl.service.FangyuanxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 房源信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
@RestController
@RequestMapping("/fangyuanxinxi")
public class FangyuanxinxiController {
    @Autowired
    private FangyuanxinxiService fangyuanxinxiService;

    @Autowired
    private StoreupService storeupService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FangyuanxinxiEntity fangyuanxinxi,
                @RequestParam(required = false) Double xiaoshoujiagestart,
                @RequestParam(required = false) Double xiaoshoujiageend,
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		fangyuanxinxi.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			fangyuanxinxi.setMaijia((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<FangyuanxinxiEntity> ew = new EntityWrapper<FangyuanxinxiEntity>();
                if(xiaoshoujiagestart!=null) ew.ge("xiaoshoujiage", xiaoshoujiagestart);
                if(xiaoshoujiageend!=null) ew.le("xiaoshoujiage", xiaoshoujiageend);

		PageUtils page = fangyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FangyuanxinxiEntity fangyuanxinxi, 
                @RequestParam(required = false) Double xiaoshoujiagestart,
                @RequestParam(required = false) Double xiaoshoujiageend,
		HttpServletRequest request){
        EntityWrapper<FangyuanxinxiEntity> ew = new EntityWrapper<FangyuanxinxiEntity>();
                if(xiaoshoujiagestart!=null) ew.ge("xiaoshoujiage", xiaoshoujiagestart);
                if(xiaoshoujiageend!=null) ew.le("xiaoshoujiage", xiaoshoujiageend);

		PageUtils page = fangyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FangyuanxinxiEntity fangyuanxinxi){
       	EntityWrapper<FangyuanxinxiEntity> ew = new EntityWrapper<FangyuanxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fangyuanxinxi, "fangyuanxinxi")); 
        return R.ok().put("data", fangyuanxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FangyuanxinxiEntity fangyuanxinxi){
        EntityWrapper< FangyuanxinxiEntity> ew = new EntityWrapper< FangyuanxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fangyuanxinxi, "fangyuanxinxi")); 
		FangyuanxinxiView fangyuanxinxiView =  fangyuanxinxiService.selectView(ew);
		return R.ok("查询房源信息成功").put("data", fangyuanxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FangyuanxinxiEntity fangyuanxinxi = fangyuanxinxiService.selectById(id);
		fangyuanxinxi.setClicknum(fangyuanxinxi.getClicknum()+1);
		fangyuanxinxi.setClicktime(new Date());
		fangyuanxinxiService.updateById(fangyuanxinxi);
		fangyuanxinxi = fangyuanxinxiService.selectView(new EntityWrapper<FangyuanxinxiEntity>().eq("id", id));
        return R.ok().put("data", fangyuanxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FangyuanxinxiEntity fangyuanxinxi = fangyuanxinxiService.selectById(id);
		fangyuanxinxi.setClicknum(fangyuanxinxi.getClicknum()+1);
		fangyuanxinxi.setClicktime(new Date());
		fangyuanxinxiService.updateById(fangyuanxinxi);
		fangyuanxinxi = fangyuanxinxiService.selectView(new EntityWrapper<FangyuanxinxiEntity>().eq("id", id));
        return R.ok().put("data", fangyuanxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FangyuanxinxiEntity fangyuanxinxi, HttpServletRequest request){
    	fangyuanxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fangyuanxinxi);
    	fangyuanxinxi.setUserid((Long)request.getSession().getAttribute("userId"));
        fangyuanxinxiService.insert(fangyuanxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FangyuanxinxiEntity fangyuanxinxi, HttpServletRequest request){
    	fangyuanxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fangyuanxinxi);
        fangyuanxinxiService.insert(fangyuanxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FangyuanxinxiEntity fangyuanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangyuanxinxi);
        fangyuanxinxiService.updateById(fangyuanxinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<FangyuanxinxiEntity> list = new ArrayList<FangyuanxinxiEntity>();
        for(Long id : ids) {
            FangyuanxinxiEntity fangyuanxinxi = fangyuanxinxiService.selectById(id);
            fangyuanxinxi.setSfsh(sfsh);
            fangyuanxinxi.setShhf(shhf);
            list.add(fangyuanxinxi);
        }
        fangyuanxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        fangyuanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,FangyuanxinxiEntity fangyuanxinxi, HttpServletRequest request,String pre){
        EntityWrapper<FangyuanxinxiEntity> ew = new EntityWrapper<FangyuanxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = fangyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 按收藏推荐
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,FangyuanxinxiEntity fangyuanxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "fangwuleixing";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "fangyuanxinxi").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<FangyuanxinxiEntity> fangyuanxinxiList = new ArrayList<FangyuanxinxiEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                fangyuanxinxiList.addAll(fangyuanxinxiService.selectList(new EntityWrapper<FangyuanxinxiEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<FangyuanxinxiEntity> ew = new EntityWrapper<FangyuanxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = fangyuanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
        List<FangyuanxinxiEntity> pageList = (List<FangyuanxinxiEntity>)page.getList();
        if(fangyuanxinxiList.size()<limit) {
            int toAddNum = (limit-fangyuanxinxiList.size())<=pageList.size()?(limit-fangyuanxinxiList.size()):pageList.size();
            for(FangyuanxinxiEntity o1 : pageList) {
                boolean addFlag = true;
                for(FangyuanxinxiEntity o2 : fangyuanxinxiList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    fangyuanxinxiList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(fangyuanxinxiList.size()>limit) {
            fangyuanxinxiList = fangyuanxinxiList.subList(0, limit);
        }
        page.setList(fangyuanxinxiList);
        return R.ok().put("data", page);
    }







}
