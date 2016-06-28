package com.xbwl.demo.entity.role;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

    /** 属性说明 */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String roleCode;
    private String roleName;
    private Date createDate;
    private Date updateDate;
    private Long createId;
    private Long updateId;
    private Long state;
    
    private String createName;
    private String updateName;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public Long getCreateId() {
        return createId;
    }
    public void setCreateId(Long createId) {
        this.createId = createId;
    }
    
    public Long getUpdateId() {
        return updateId;
    }
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }
         
    public Long getState() {
        return state;
    }
    public void setState(Long state) {
        this.state = state;
    }
    
    
    public String getCreateName() {
        return createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    
    public String getUpdateName() {
        return updateName;
    }
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
    
    
} 
