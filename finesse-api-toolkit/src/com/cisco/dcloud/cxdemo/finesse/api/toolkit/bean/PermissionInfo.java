package com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.*;

public class PermissionInfo extends BaseApiBean {
  private Boolean canCreate;
  private Boolean canDelete;
  private Boolean canUpdate;
  private Boolean departmentAdmin;
  private String role;

  public Boolean getCanCreate() {
     return this.canCreate;
  }

  public void setCanCreate(Boolean canCreate) {
     this.canCreate = canCreate;
  }

  public Boolean getCanDelete() {
     return this.canDelete;
  }

  public void setCanDelete(Boolean canDelete) {
     this.canDelete = canDelete;
  }

  public Boolean getCanUpdate() {
     return this.canUpdate;
  }

  public void setCanUpdate(Boolean canUpdate) {
     this.canUpdate = canUpdate;
  }

  public Boolean getDepartmentAdmin() {
     return this.departmentAdmin;
  }

  public void setDepartmentAdmin(Boolean departmentAdmin) {
     this.departmentAdmin = departmentAdmin;
  }

  public String getRole() {
     return this.role;
  }

  public void setRole(String role) {
     this.role = role;
  }


}
