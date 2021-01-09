package com.simpleUI.projectmanagementtool.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "Please Enter a valid email")
	@NotBlank(message = "User name cannot be blank")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "Enter your full name")
	private String fullname;
	
	@NotBlank(message = "Password cannot be blank")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	//OneToMany with Project
	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH,fetch = FetchType.LAZY ,orphanRemoval = true)
	private List<Project> projectList = new ArrayList<Project>();
	
	public User() {
	
	}
	
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	@PreUpdate
	public void preUpdate() {
		this.updatedAt  = new Date();
	}
	@PrePersist
	public void prePersist() {
		this.createdAt  = new Date();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void addUserProject(Project project) {
		projectList.add(project);
		project.setUser(this);
		
	}
	public void removeUserProject(Project project) {
		projectList.remove(project);
		project.setUser(null);
		
	}
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
	
	

}
