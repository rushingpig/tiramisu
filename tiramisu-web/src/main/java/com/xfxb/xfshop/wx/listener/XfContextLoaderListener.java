package com.xfxb.xfshop.wx.listener;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

/**
 * @des	   Spring的监听入口，里面加入自己的业务
 * @author pigo.can
 * @email rushingpig@163.com
 * @homepage http://www.pigo.top
 * @date 2015年10月31日 上午12:14:39
 * @ver V1.0
 */
public class XfContextLoaderListener extends ContextLoaderListener {

	private static final Logger logger = LoggerFactory.getLogger(XfContextLoaderListener.class);

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!printProjectLoadMsg()) {
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}

	private boolean printProjectLoadMsg() {

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\r\n======================================================================\r\n");
			sb.append("\r\n==       欢迎使用 “幸福西饼”  - Powered By http://xfxb.net            ==\r\n");
			sb.append("\r\n======================================================================\r\n");
			System.out.println(sb.toString());
		} catch (Exception e) {
			logger.error("something is wrong when load the project....");
			return false;
		}
		return true;
	}

}
