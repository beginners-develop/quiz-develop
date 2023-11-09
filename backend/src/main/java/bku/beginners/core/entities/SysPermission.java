package bku.beginners.core.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_permission")
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 6976935919881542757L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SysPermissionMethod method;

    private String code;

    private String name;

    private String service;

    @Column(name = "object_code")
    private String objectCode;

    @Column(name = "object_name")
    private String objectName;
}
