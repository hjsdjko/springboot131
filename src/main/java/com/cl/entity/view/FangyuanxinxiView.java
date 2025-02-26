package com.cl.entity.view;

import com.cl.entity.FangyuanxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 房源信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-06 22:11:36
 */
@TableName("fangyuanxinxi")
public class FangyuanxinxiView  extends FangyuanxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public FangyuanxinxiView(){
	}
 
 	public FangyuanxinxiView(FangyuanxinxiEntity fangyuanxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, fangyuanxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
