package com.javaweb.repository.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "role")
public class RoleEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	    private List<UserEntity> user = new ArrayList<>();

//	    @OneToMany(mappedBy="roles",fetch = FetchType.LAZY)
//	    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();
	 
	 
	 
//	    public List<UserRoleEntity> getUserRoleEntities() {
//		return userRoleEntities;
//	}
//
//	public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//		this.userRoleEntities = userRoleEntities;
//	}

		@Column(name="name", nullable = false)
	    private String name;

	    public List<UserEntity> getUser() {
			return user;
		}

		public void setUser(List<UserEntity> user) {
			this.user = user;
		}

		@Column(name="code", unique = true, nullable = false)
	    private String code;

	  
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
}
