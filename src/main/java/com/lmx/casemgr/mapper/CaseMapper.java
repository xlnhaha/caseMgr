package com.lmx.casemgr.mapper;

import com.lmx.casemgr.entity.CaseInfo;
import com.lmx.casemgr.entity.PersonInfo;

import java.util.List;
import java.util.Map;

public interface CaseMapper {

    int addPersonInfo(PersonInfo personInfo);

    int addCaseInfo(CaseInfo caseInfo);

    List<PersonInfo> queryPersonList(Map params);

    List<CaseInfo> queryCasesOfOne(Map params);

    PersonInfo queryCaseById(int id);

    PersonInfo queryPersonInfo(int id);

    int finishTreat(int pId);

    int addTreatTimes(int pId);
}
