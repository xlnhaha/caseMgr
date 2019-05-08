package com.lmx.casemgr.service;

import com.github.pagehelper.PageInfo;
import com.lmx.casemgr.entity.CaseInfo;
import com.lmx.casemgr.entity.PersonInfo;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CaseService {
    int addRecord(PersonInfo personInfo);

    int addCase(CaseInfo caseInfo);

    PageInfo<PersonInfo> queryPersonList(Map params);

    PageInfo<CaseInfo> queryCasesOfOne(Map params);

    PersonInfo queryCaseById(int id);

    PersonInfo queryPersonInfo(int id);

    int finishTreat(int pId);

    String downloadCase(int cId, HttpServletResponse response) throws IOException;

}
