package com.lws.blogdb.controller;

import com.lws.blogdb.entity.Author;
import com.lws.blogdb.entity.Result;
import com.lws.blogdb.service.AuthorService;
import com.lws.blogdb.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@ResponseBody
@Api(tags = "作者相关接口", description = "对于作者数据的操作")
@RequestMapping("/author")
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口的描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"),
    })
    public Result test(String name) {
        return Result.success("响应成功"+name);
    }

    @PostMapping("/reg")
    @ApiOperation(value = "注册作者", notes = "注册作者的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "author", value = "作者实体", required = true, dataType = "Author", paramType = "body"),
    })
    public Result register( @Validated(Author.registry.class) @RequestBody Author author) {
        Integer author1 = authorService.register(author);
        if (author1!= null&&author1>0) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    public Result login(@Pattern(regexp="^\\S{6,20}$",message="作者名字6~20位非空字符") String name,@Pattern(regexp="^\\S{6,20}$",message="密码6~20位非空字符") String password) {
        Author author = authorService.login(name, password);
        if (Objects.nonNull(author)) {
            //给与JWT token
            Map<String, Object> map = new HashMap<>();
            map.put("id", author.getAuthorid());
            String token = JwtUtil.genToken(map);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }




}
