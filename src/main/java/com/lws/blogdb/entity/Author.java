package com.lws.blogdb.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * 
 * @TableName author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author implements Serializable {
    /**
     * 作者ID 雪花算法
     */
    @NotEmpty(groups = {updateBase.class},message="作者ID不能为空")
    private String authorid;

    /**
     * 登录名
     */
    @Pattern(groups = {registry.class,updateBase.class,loginByLoginName.class},regexp="^\\S{6,20}$",message="作者名字6~20位非空字符")
    private String loginname;

    /**
     * 密码
     */
    @Pattern(groups = {registry.class,loginByLoginName.class},regexp="^\\S{6,20}$",message="密码6~20位非空字符")
    private String loginpwd;

    /**
     * 昵称
     */
    @Pattern(groups = {updateBase.class},regexp="^\\S{2,10}$",message="昵称2~10位非空字符")
    private String nickname;

    /**
     * 邮箱
     */
    @Email(groups = {registry.class,updateBase.class},message="邮箱格式不正确")
    private String email;

    /**
     * 电话号码
     */
    @Pattern(groups = {registry.class,updateBase.class},regexp="^1[34578]\\d{9}$",message="电话号码格式不正确")
    private String tel;

    /**
     * 头像地址
     */
    private String headpic;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatetime;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", authorid=").append(authorid);
        sb.append(", loginname=").append(loginname);
        sb.append(", loginpwd=").append(loginpwd);
        sb.append(", nickname=").append(nickname);
        sb.append(", email=").append(email);
        sb.append(", tel=").append(tel);
        sb.append(", headpic=").append(headpic);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public interface registry{

    }
    public interface updateBase {

    }
    public interface loginByLoginName {

    }
}