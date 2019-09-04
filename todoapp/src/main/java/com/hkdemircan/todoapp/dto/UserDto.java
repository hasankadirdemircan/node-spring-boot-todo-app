package com.hkdemircan.todoapp.dto;

import com.hkdemircan.todoapp.dto.base.BaseDtoAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the User. ")
public class UserDto extends BaseDtoAudit {

    @ApiModelProperty(notes = "The database generated user ID")
    private Integer id;

    @ApiModelProperty(notes = "The database generated username")
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
