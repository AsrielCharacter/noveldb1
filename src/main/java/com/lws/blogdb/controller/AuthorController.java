package com.lws.blogdb.controller;

import com.lws.blogdb.entity.Author;
import com.lws.blogdb.entity.Result;
import com.lws.blogdb.service.AuthorService;
import com.lws.blogdb.utils.JwtUtil;
import com.lws.blogdb.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
        String name = author.getLoginname();
        //查询登录名字是否存在
        Author authorByName = authorService.selectAuthorByName(name);
        if (Objects.nonNull(authorByName)) {
            return Result.error("登录名重复");
        }


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
            //现在是写死的权限
            map.put("role", "author");
            String token = JwtUtil.genToken(map);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }


    @GetMapping("/info")
    @ApiOperation(value = "获取当前登录的作者信息", notes = "获取当前登录的作者信息的接口")
    public Result getAuthorInfo() {
        //获取当前登录的作者信息
        Author author = authorService.getAuthorInfo();
        if (Objects.nonNull(author)) {
            return Result.success(author);
        }
        return Result.error("未登录");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新已经登录的作者基础信息", notes = "该接口用于更新已登录作者的基本信息(除头像和密码)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "author", value = "作者实体", required = true, dataType = "Author", paramType = "body"),
    })
    public Result updateAuthor(@Validated(Author.updateBase.class) @RequestBody Author author) {
        String name = author.getLoginname();
        //查询登录名字是否存在
        Author authorByName = authorService.selectAuthorByName(name);
        if (Objects.nonNull(authorByName)) {
            return Result.error("登录名重复");
        }

        //虽然前端传来的Author有id,但是不能相信前端的信息,所以这里(service层)需要重新在ThreadLocal中获取当前登录的作者信息一次
        Integer author1 = authorService.updateBaseInfoAuthor(author);
        if (author1 != null && author1 > 0) {
            return Result.success();
        }

        return Result.error("更新失败");
    }

    @PatchMapping("/updateHeadPic")
    @ApiOperation(value = "更新已经登录的作者头像地址", notes = "该接口用于更新已登录作者的头像地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "headPic", value = "头像地址", required = true, dataType = "String", paramType = "query"),
    })
    public Result updateHeadPic(@RequestParam("headPic") @URL(message = "头像地址格式错误") String headPic) {
        Integer author1 = authorService.updateHeadPic(headPic);
        if (author1 != null && author1 > 0) {
            return Result.success();
        }
        return Result.error("更新失败");
    }

    @PatchMapping("/updatePwd")
    @ApiOperation(value = "更新已经登录的作者密码", notes = "该接口用于更新已登录作者的密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true, dataType = "String", paramType = "query"),
    })
    //这里需要加事务注解,否则更新密码失败,原子性问题,暂时的解决办法
    @Transactional
    public Result updatePwd(@Pattern(regexp="^\\S{6,20}$",message="密码6~20位非空字符") @RequestParam("oldPwd") String oldPwd,
                            @Pattern(regexp="^\\S{6,20}$",message="密码6~20位非空字符") @RequestParam("newPwd") String newPwd) {
        //先判断新旧密码是否一致 这里要获取数据库密码,不能相信前端
        String oldHashPwd = authorService.getAuthorInfo().getLoginpwd();
        String userOldPwd = Md5Util.getMd5(oldPwd);
        if (!userOldPwd.equals(oldHashPwd)){
            return Result.error("旧密码错误");
        }
        String newHashPwd = Md5Util.getMd5(newPwd);
        if (oldHashPwd.equals(newHashPwd)){
            return Result.error("新旧密码相同");
        }
        //以下写法性能不行
//        if (Objects.equals(oldHashPwd, newHashPwd)) {
//            return Result.error("新旧密码不能相同");
//        }

        Integer author1 = authorService.updatePwd(newHashPwd);
        if (author1 != null && author1 > 0) {
            return Result.success();
        }
        return Result.error("更新失败");
    }






}
