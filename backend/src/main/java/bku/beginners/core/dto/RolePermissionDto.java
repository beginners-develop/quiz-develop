package bku.beginners.core.dto;

import java.util.List;

public class RolePermissionDto {
    private String service;
    private String objectCode;
    private String objectName;
    private List<SysPermission> sysPermissions;
}
