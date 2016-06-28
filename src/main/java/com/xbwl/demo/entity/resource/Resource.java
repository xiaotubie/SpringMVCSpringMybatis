package com.xbwl.demo.entity.resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 权限资源类
 * @author huangqiong
 *  2015.09.29
 */
public class Resource implements Serializable {
	 /** 属性说明 */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long parentId;
    private String resCode;
    private String resName;
    private String resUrl;
    private Long resType;
    private Long state;
    private Long resSort;
    private Date createDate;
    private Date updateDate;
    private Long createId;
    private Long updateId;
    
    private String parentName;
    private String createName;
    private String updateName;
    private List<Resource> childMenu;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    public String getResName() {
        return resName;
    }
    public void setResName(String resName) {
        this.resName = resName;
    }
    public String getResUrl() {
        return resUrl;
    }
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
    public Long getResType() {
        return resType;
    }
    public void setResType(Long resType) {
        this.resType = resType;
    }
    public Long getState() {
        return state;
    }
    public void setState(Long state) {
        this.state = state;
    }
    public Long getResSort() {
        return resSort;
    }
    public void setResSort(Long resSort) {
        this.resSort = resSort;
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
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
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
    public List<Resource> getChildMenu() {
        return childMenu;
    }
    public void setChildMenu(List<Resource> childMenu) {
        this.childMenu = childMenu;
    }
    
}
