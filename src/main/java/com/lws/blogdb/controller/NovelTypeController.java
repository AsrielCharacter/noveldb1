package com.lws.blogdb.controller;

import com.lws.blogdb.entity.NovelType;
import com.lws.blogdb.entity.Result;
import com.lws.blogdb.service.NovelTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/novelType")
@Api(tags = "小说类型管理",description = "管理小说的类型")
@ResponseBody
@Validated
public class NovelTypeController {
    @Autowired
    private NovelTypeService novelTypeService;

    @PostMapping()
    @ApiOperation(value = "添加小说类型",notes = "添加小说类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "novelType",value = "小说类型",required = true,dataType = "novelType")
    })
    public Result addNovelType(@RequestBody @Validated(NovelType.add.class) NovelType novelType) {
        //现在不考虑重复添加，直接添加
        Integer status = novelTypeService.addNovelType(novelType);

        if (status == 1) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败，请联系管理员");
    }

    @GetMapping("/details/{id}")
    @ApiOperation(value = "根据id查询小说类型详情",notes = "根据路径参数id查询小说类型详情")
    public Result getNovelTypeDetail(@PathVariable("id") @NotNull Integer id) {
        NovelType novelType = novelTypeService.selectById(id);
        if (novelType == null) {
            return Result.error("查询失败，请联系管理员");
        }
        return Result.success(novelType);
    }

    @PutMapping()
    @ApiOperation(value = "修改小说类型",notes = "修改小说类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "novelType",value = "小说类型",required = true,dataType = "novelType")
    })
    public Result updateNovelType(@RequestBody @Validated(NovelType.update.class) NovelType novelType) {

        Integer status = novelTypeService.updateNovelType(novelType);
        if (status == 1) {
            return Result.success();
        }
        return Result.error("修改失败，请联系管理员");
    }

    @DeleteMapping()
    @ApiOperation(value = "删除小说类型",notes = "删除小说类型")
    public Result deleteNovelType(@NotNull Integer typeID) {
        Integer status = novelTypeService.deleteNovelType(typeID);
        if (status == 1) {
            return Result.success();
        }
        return Result.error("删除失败，请联系管理员");
    }


}
