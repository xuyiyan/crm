package com.xdl.system.service;

import java.util.List;

import com.xdl.patter.entity.Pattertype;
import com.xdl.publics.util.PageBean;
import com.xdl.system.entity.Course;
import com.xdl.system.entity.Subject;

/**
 * 课程管理接口
 */
public interface ICourseService {

	/**
	 * 查询所有课程
	 * @param pageBean
	 */
	public List<Course> queryCourse(PageBean pageBean);
	
	/**
	 * 查询课程的场景
	 * @param pageBean
	 * @param courseid
	 */
	public  List<Pattertype> queryPattertype(PageBean pageBean, String courseid);
	
	/**
	 * 增加或者修改课程
	 * @param course
	 */
	public void saveOrUpdate(Course course);
	
	/**
	 * 增加或者修改课程场景
	 * @param pattertype
	 */
	public void saveOrUpdate(Pattertype pattertype);
	
	/**
	 * 删除课程
	 * @param courseids
	 */
	public void deleteCourse(String courseids);
	
	/**
	 * 根据课程ID或者场景ID，删除课程场景
	 * @param courseids
	 * @param patterTypeIds
	 */
	public void deletePattertype(String courseids, String patterTypeIds);
	
	/**
	 * 保存/修改科目
	 * @param subject
	 */
	public void saveOrUpdate(Subject subject);
	
	/**
	 * 删除科目
	 * @param subject
	 */
	public void deleteSubject(String subjectids);
	
	/**
	 * 查询科目
	 * @param courseid
	 * @param pageBean
	 * @return
	 */
	public List<Subject> querySubject(Long courseid, PageBean pageBean);
	
	/**
	 * 根据课程ID，查询科目，用逗号隔开
	 * @param courseid
	 * @return
	 */
	public Subject querySubjectByCourseId(Integer courseid);
	
	/**
	 * 根据课程ID，查询课程
	 * @param courseid
	 * @return
	 */
	public Course queryCourseById(Integer courseid);
	
	
}
