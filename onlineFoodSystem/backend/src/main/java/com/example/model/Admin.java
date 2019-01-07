//package com.example.model;
//
//import java.util.Collection;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Entity
//@Table(name = "sellers")
//public class Admin implements UserDetails {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	@Id
//	@Column(name="id")
//	private Integer id;
//	private String name;
//	private String password;
//	private String email;
//	@Column(name="is_sys_admin")
//	private boolean isSysAdmin;
//	
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Admin() {
//	}
//	
//
//	public Admin(Integer id, String name, String password, boolean isSysAdmin, String email) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.password = password;
//		this.isSysAdmin = isSysAdmin;
//		this.email = email;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public boolean isSysAdmin() {
//		return isSysAdmin;
//	}
//
//	public void setSysAdmin(boolean isSysAdmin) {
//		this.isSysAdmin = isSysAdmin;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
////		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////
////        Set<SRole> userRoles = this.getSRoles();
////
////
////        if (userRoles != null)
////
////        {
////
////            for (SRole role : userRoles) {
////
////                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
////
////                authorities.add(authority);
////
////            }
////
////        }
//
////        return authorities;
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return this.getName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public String toString() {
//		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", isSysAdmin="
//				+ isSysAdmin + "]";
//	}
//	
//}
