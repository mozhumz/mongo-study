package com.hyj.mongostudy.web.controller;

import com.hyj.mongostudy.exception.ResData;
import com.hyj.mongostudy.model.po.City;
import com.hyj.mongostudy.service.IDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huyuanjia
 * @date 2019/4/2 16:55
 */
@RestController
@Api(value = "测试相关接口", description = "测试相关接口")
@RequestMapping("/api/demo")
public class DemoController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private IDemoService demoService;



    @ApiOperation(value = "添加")
    @RequestMapping(value = "/testAdd", method = RequestMethod.POST)
    public Object testAdd(@RequestBody City city) {
        demoService.testAdd(city);

        return new ResData(city);
    }

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/testAdd2", method = RequestMethod.POST)
    public Object testAdd2(@RequestBody City city) {
        demoService.testAdd2(city);

        return new ResData(city);
    }

    @ApiOperation(value = "更新")
    @RequestMapping(value = "/updateCity", method = RequestMethod.POST)
    public Object updateCity(@RequestBody City city) {
        demoService.testUpdate(city);

        return new ResData(city);
    }

    @ApiOperation(value = "更新2")
    @RequestMapping(value = "/testUpdate2", method = RequestMethod.POST)
    public Object testUpdate2(@RequestBody City city) {
        demoService.testUpdate2(city);

        return new ResData(city);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/testDelete", method = RequestMethod.POST)
    public Object testDelete(@RequestBody City city) {
        demoService.testDelete(city);

        return new ResData(city);
    }

    @ApiOperation(value = "删除2")
    @RequestMapping(value = "/testDelete2", method = RequestMethod.POST)
    public Object testDelete2(@RequestBody City city) {
        demoService.testDelete2(city);

        return new ResData(city);
    }

}
