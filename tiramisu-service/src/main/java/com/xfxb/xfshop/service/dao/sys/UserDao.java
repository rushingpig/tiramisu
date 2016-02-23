package com.xfxb.xfshop.service.dao.sys;

import java.util.List;

import com.xfxb.xfshop.service.dao.SimpleDao;
import com.xfxb.xfshop.service.domain.entity.sys.User;

/**
 * @author pigo.can
 * @email rushingpig@163.com
 * @homepage http://www.pigo.top
 * @date 2015年11月21日 下午6:29:27
 * @ver V1.0
 */
public interface UserDao extends SimpleDao<User> {
	
	public User getByLoginName(User user);

	public List<User> findByOfficeId(User user);

	public long findAllCount(User user);
	

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);

	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * 
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);

	/**
	 * 插入用户角色关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
}
