package com.lws.blogdb.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lws.blogdb.validation.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

/**
 * 
 * @TableName novel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Novel implements Serializable {
    /**
     * 小说ID 一样用雪花算法
     */
    @NotEmpty(groups = update.class,message = "小说ID不能为空")
    private String novelId;

    /**
     * 标题
     */
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z0-9\\s·]{2,20}+$", message = "标题只能包含中文、英文、数字、空格、以及符号·", groups = {add.class,update.class})
    private String title;

    /**
     * 类型ID
     */
    @NotNull(groups = {add.class,update.class},message = "小说类型不能为空")
    private Integer typeId;

    /**
     * 封面地址
     */
    @URL(groups = {add.class,update.class},message = "封面地址格式不正确")
    private String coverUrl;

    /**
     * 小说内容 谁没事把小说写数据库啊
     */
    @NotEmpty(groups = {add.class,update.class},message = "小说内容不能为空")
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 状态：0.未发表，1.已发表
     */
//    @Pattern(regexp = "^[01]$", message = "状态只能为0或1", groups = {add.class,update.class})
    @State(groups = {add.class,update.class})
    private Integer state;

    /**
     * 作者ID 雪花算法
     */
    @NotEmpty(groups = add.class,message = "作者ID不能为空")
    private String authorId;

    @Serial
    private static final long serialVersionUID = 1L;

    public interface add {}
    public interface update {}

    @Override
    public String toString() {
        return "Novel{" +
                "novelId='" + novelId + '\'' +
                ", title='" + title + '\'' +
                ", typeId=" + typeId +
                ", coverUrl='" + coverUrl + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", state=" + state +
                ", authorId=" + authorId +
                '}';
    }
}