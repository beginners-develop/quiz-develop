package bku.beginners.core.entities;

public enum SysPermissionMethod {
    GET(0), POST(1), PUT(2), DELETE(3);
    private Integer value;
    SysPermissionMethod(Integer value) {
        this.value = value;
    }
}
