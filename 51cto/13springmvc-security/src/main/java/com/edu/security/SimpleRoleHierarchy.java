package com.edu.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.util.*;

//@Component
public class SimpleRoleHierarchy extends JdbcTemplate implements RoleHierarchy {

	public SimpleRoleHierarchy(DataSource dataSource){
		super(dataSource);
	}
	
	/**
	 * 输入参数是登陆用户的所有权限（角色）
	 * 实现逻辑：
	 * 循环每一个输入的权限，然后查询数据库，查询到该权限的所有下级权限
	 */
	@Override
	public Collection<? extends GrantedAuthority> getReachableGrantedAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		//set去重
		Set<GrantedAuthority> result = new HashSet<>();
		
		authorities.forEach(role -> {
			String name = role.getAuthority().replace("ROLE_", "");
			String sql = "select role_name from tb_roles where spath like (select concat('%',spath) from tb_roles where role_name='"+name+"')";
			List<Map<String, Object>> list = this.queryForList(sql);
			list.forEach(map -> {result.add(new SimpleGrantedAuthority("ROLE_" + map.get("role_name").toString()));});
		});
		
		return result;
	}
}





