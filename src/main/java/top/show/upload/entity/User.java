package top.show.upload.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019-7-27
 */
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User implements Serializable {

    private static final long serialVersionUID = -2578929215841524014L;
    @Column(nullable = false, length = 20)

    private String username;
    @Column(nullable = false, length = 20)

    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

}
