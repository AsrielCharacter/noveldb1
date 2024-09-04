package com.lws.blogdb.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "统一响应结果")
public class Result<T> {
    @ApiModelProperty(value = "业务状态码", example = "0-成功  1-失败",dataType = "integer")
    private Integer code;//业务状态码  0-成功  1-失败
    @ApiModelProperty(value = "提示信息", example = "操作成功",dataType = "string")
    private String message;//提示信息
    @ApiModelProperty(value = "响应数据",example = "具体响应数据",dataType = "object")
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {

        return new Result(0, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
