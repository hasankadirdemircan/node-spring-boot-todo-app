package com.hkdemircan.todoapp.model;

import com.hkdemircan.todoapp.model.base.BaseModelAudit;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends BaseModelAudit {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7713856648419571706L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
