package com.hkdemircan.todoapp.dto;

import com.hkdemircan.todoapp.dto.base.BaseDtoAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the User. ")
public class TodoDto extends BaseDtoAudit {

    @ApiModelProperty(notes = "The database generated todo ID")
    private Integer id;

    private String username;
    private String todo;
    private String active;

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

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
