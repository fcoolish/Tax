package cn.tax.nsfw.info.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tax.core.action.service.impl.BaseServiceImpl;
import cn.tax.core.util.QueryHelper;
import cn.tax.nsfw.info.dao.InfoDao;
import cn.tax.nsfw.info.entity.Info;
import cn.tax.nsfw.info.service.InfoService;

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {

	
	private InfoDao infoDao;

	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}


	
	

	

}
