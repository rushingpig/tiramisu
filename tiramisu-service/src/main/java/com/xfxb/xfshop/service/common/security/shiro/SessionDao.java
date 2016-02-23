package com.xfxb.xfshop.service.common.security.shiro;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;

/**
 * @author pigo.can
 * @email rushingpig@163.com
 * @homepage http://www.pigo.top
 * @date 2015年11月17日 下午11:08:44
 * @ver V1.0
 */
public interface SessionDao extends SessionDAO {
	/**
	 * 获取活动会话
	 * 
	 * @param includeLeave
	 *            是否包括离线（最后访问时间大于3分钟为离线会话）
	 * @return
	 */
	public Collection<Session> getActiveSessions(boolean includeLeave);

	/**
	 * 获取活动会话
	 * 
	 * @param includeLeave
	 *            是否包括离线（最后访问时间大于3分钟为离线会话）
	 * @param principal
	 *            根据登录者对象获取活动会话
	 * @param filterSession
	 *            不为空，则过滤掉（不包含）这个会话。
	 * @return
	 */
	public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession);
}
