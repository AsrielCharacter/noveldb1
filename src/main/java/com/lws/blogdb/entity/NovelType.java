package com.lws.blogdb.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName noveltype
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NovelType implements Serializable {
    /**
     * 类型ID
     */
    @NotNull(message = "类型ID不能为空", groups = {update.class})
    private Integer typeId;

    /**
     * 类型名
     */
    @NotEmpty(message = "分类名不能为空", groups = {add.class, update.class})
    private String typeName;

    /**
     * 别名
     */
    @NotEmpty(message = "别名不能为空", groups = {add.class, update.class})
    private String byName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @Serial
    private static final long serialVersionUID = 1L;
    public interface add{}
    public interface update{}


}