package bku.beginners.core.entities;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "role_user")
public class RoleUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "role_id", insertable = false, updatable = false)
    private Integer roleId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "creatoruserid")
    private Integer creatorUserId;

    @Column(name = "deleteruserid")
    private Integer deleterUserId;

    @CreationTimestamp
    @Column(name = "creationtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @UpdateTimestamp
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
