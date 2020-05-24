package com.xdl.patter.service;

import java.util.List;

import com.xdl.patter.entity.Patter;
import com.xdl.patter.entity.Pattertype;
import com.xdl.publics.util.PageBean;
import com.xdl.system.entity.Course;

/**
 * 话术接口
 */
public interface IPatterService {
	
	/**
	 * 保存或者修改话术内容
	 * @param patter
	 */
	public void saveOrUpdate(Patter patter);
	
	/**
	 * 删除话术内容
	 * @param patterids
	 */
	public void deletePatter(String patterids);
	
	/**
	 * 是否共享 
	 * @param patterid
	 * @param isshare 是否共享 0不共享  1共享
	 */
	public void updatePatterIsShare(Long patterid, Integer isshare);
	
	/**
	 * 查询课程和课程下的场景
	 * @param roleid
	 * @param userid
	 * @param teacherid
	 * @param courseName
	 */
	public List<Pattertype> queryPattertype(Long roleid, Long userid, Long teacherid, String courseid, PageBean pageBean);
	
	/**
	 * 查询话术内容
	 * @param roleid
	 * @return
	 */
	public List<Patter> queryPatter(Long roleid, Long userid, Long patterTypeId, Long courseid, PageBean pageBean);
	
	
	
	
}
