package com.github.ginirohikocha.spring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author GinirohikoCha
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String identityType;

    private String identifier;

    private String credential;

    private String salt;


}
