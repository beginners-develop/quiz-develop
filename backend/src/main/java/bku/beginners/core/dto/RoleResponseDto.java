package bku.beginners.core.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class RoleResponseDto {
    private Integer id;
    private String code;
    private String name;
    private Boolean defaultRole;
    private List<RolePermissionDto> permissions;
}
