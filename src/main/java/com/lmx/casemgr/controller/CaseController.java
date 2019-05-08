package com.lmx.casemgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lmx.casemgr.entity.CaseInfo;
import com.lmx.casemgr.entity.PersonInfo;
import com.lmx.casemgr.service.CaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value="/case")
public class CaseController {

    @Resource
    private CaseService caseService;

    @RequestMapping(value="/hello")
    public String helloWorld() {
        return "hello world";
    }

    @RequestMapping(value="/addRecord", method = RequestMethod.POST)
    public int addRecord(@RequestBody PersonInfo personInfo) {

        int id = caseService.addRecord(personInfo);
        return id;
    }

    @RequestMapping(value="/queryPersonList", method = RequestMethod.POST)
    public String queryPersonList(@RequestBody Map filter) {

        PageInfo<PersonInfo> personList = caseService.queryPersonList(filter);
        return JSONObject.toJSONString(personList);
    }

    @RequestMapping(value="/queryCasesOfOne", method = RequestMethod.POST)
    public String queryCasesOfOne(@RequestBody Map filter) {

        PageInfo<CaseInfo> caseList = caseService.queryCasesOfOne(filter);
        return JSONObject.toJSONString(caseList);
    }

    @RequestMapping(value="/queryCaseById", method = RequestMethod.GET)
    public String queryCaseById(@RequestParam(value = "pId") int pId) {

        PersonInfo personInfo = caseService.queryCaseById(pId);
        return JSONObject.toJSONString(personInfo);
    }

    @RequestMapping(value="/queryPersonInfo", method = RequestMethod.GET)
    public String queryPersonInfo(@RequestParam(value = "pId") int pId) {

        PersonInfo personInfo = caseService.queryPersonInfo(pId);
        return JSONObject.toJSONString(personInfo);
    }

    @RequestMapping(value="/addCase", method = RequestMethod.POST)
    public int addCase(@RequestBody CaseInfo caseInfo) {
        int id = caseService.addCase(caseInfo);
        return id;
    }

    @RequestMapping(value="/finishTreat", method = RequestMethod.POST)
    public int finishTreat(@RequestBody Map param) {
        return caseService.finishTreat((int)param.get("pId"));
    }

    @RequestMapping(value="/downloadCase", method = RequestMethod.GET)
    public String downloadCase(@RequestParam(value = "cId") int pId,
                               HttpServletResponse response) throws IOException {

        return caseService.downloadCase(pId, response);
    }
}
