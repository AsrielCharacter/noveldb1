package com.lws.blogdb.controller;

import com.github.pagehelper.PageInfo;
import com.lws.blogdb.entity.Novel;
import com.lws.blogdb.entity.PageBean;
import com.lws.blogdb.entity.Result;
import com.lws.blogdb.service.NovelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/novel")
@Api(tags = "小说相关接口",description = "小说相关接口")
public class NovelController {
    @Autowired
    private NovelService novelService;

    @PostMapping()
    @ApiOperation(value = "添加小说",notes = "添加小说")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "novel",value = "小说实体",required = true,dataType = "Novel",paramType = "body")
    })
    public Result addNovel(@RequestBody @Validated(Novel.add.class) Novel novel){
        //注意没判断是否有对应的作者，作者id是否存在,小说类型是否存在
        Integer status = novelService.addNovel(novel);
        if(status!= null){
            return Result.success();
        }
        return Result.error("新增失败");
    }

    @GetMapping()
    @ApiOperation(value = "根据条件查询小说列表",notes = "根据条件查询小说列表")
    public Result getNovel(@RequestParam(required = true) int pageNum,
                           @RequestParam(required = true) int pageSize,
                           @RequestParam(required = false) Integer typeID,
                           @RequestParam(required = false) Integer state
                           ){
        PageBean<Novel> novel = novelService.getNovel(pageNum,pageSize,typeID,state);


        return Result.success(novel);
    }

    @GetMapping("/detail/{novelID}")
    @ApiOperation(value = "根据小说id查询小说详情",notes = "根据小说id查询小说详情")
    public Result getNovelDetail(@PathVariable("novelID") String novelID){
        Novel novel = novelService.selectNovelById(novelID);
        return Result.success(novel);
    }

    @PutMapping()
    @ApiOperation(value = "修改小说信息",notes = "修改小说信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "novel",value = "小说实体",required = true,dataType = "Novel",paramType = "body")
    })
    public Result updateNovel(@RequestBody @Validated(Novel.update.class) Novel novel){
        Integer status = novelService.updateNovel(novel);
        if(status!= null && status == 1){
            return Result.success();
        }
        return Result.error("修改失败");
    }
    @DeleteMapping()
    @ApiOperation(value = "删除小说",notes = "删除小说")
    public Result deleteNovel(@NotNull String novelID){
        Integer status = novelService.deleteNovel(novelID);
        if(status!= null && status == 1){
            return Result.success();
        }
        return Result.error("删除失败");
    }






}
