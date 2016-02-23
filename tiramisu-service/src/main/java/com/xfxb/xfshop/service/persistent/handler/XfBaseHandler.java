package com.xfxb.xfshop.service.persistent.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * 实现自定义的Mybatis TypeHandler
 * @author pigo.can
 * @email  rushingpig@163.com
 * @homepage http://www.pigo.top
 * @date   2015年11月4日 下午10:23:03
 * @ver    V1.0
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class XfBaseHandler extends BaseTypeHandler<String>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		
		return rs.getString(columnName);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return rs.getString(columnIndex);
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		
		return cs.getString(columnIndex);
	}

}
