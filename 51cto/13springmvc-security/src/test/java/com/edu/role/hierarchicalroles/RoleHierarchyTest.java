package com.edu.role.hierarchicalroles;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class RoleHierarchyTest {

	@Test
	public void testRoleHierarchy(){
		RoleHierarchyImpl rh = new RoleHierarchyImpl();
		rh.setHierarchy("ROLE_USER > ROLE_GUEST ROLE_ADMIN > ROLE_USER");
		
		List authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST"), new SimpleGrantedAuthority("ROLE_ADMIN"));
		System.out.println(rh.getReachableGrantedAuthorities(authorities));
	}
}
