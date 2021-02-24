package com.github.ginirohikocha.spring.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author GinirohikoCha
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    private String salt;

    private LocalDateTime created;


}
