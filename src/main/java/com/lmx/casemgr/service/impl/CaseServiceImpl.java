package com.lmx.casemgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmx.casemgr.entity.AddrInfo;
import com.lmx.casemgr.mapper.CaseMapper;
import com.lmx.casemgr.entity.CaseInfo;
import com.lmx.casemgr.entity.PersonInfo;
import com.lmx.casemgr.service.CaseService;
import com.lmx.casemgr.utils.DicsUtil;
import com.mysql.jdbc.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CaseServiceImpl implements CaseService {

    @Resource
    private CaseMapper caseMapper;

    private Logger logger = LoggerFactory.getLogger("CaseServiceImpl");

    Configuration configuration = new Configuration();

    @Override
    public int addRecord(PersonInfo personInfo) {
        caseMapper.addPersonInfo(personInfo);
        CaseInfo caseInfo = personInfo.getCaseInfo();
        caseInfo.setPersonId(personInfo.getId());
        caseMapper.addCaseInfo(caseInfo);
        return caseInfo.getId();
    }

    @Override
    public int addCase(CaseInfo caseInfo) {
        caseMapper.addCaseInfo(caseInfo);
        caseMapper.addTreatTimes(caseInfo.getPersonId());
        return caseInfo.getId();
    }

    @Override
    public PageInfo<PersonInfo> queryPersonList(Map params) {
        PageHelper.startPage((int)params.get("currentPage"),(int)params.get("pageSize"));
        List<PersonInfo> demoList = caseMapper.queryPersonList(params);
        PageInfo<PersonInfo> pageInfo = new PageInfo<>(demoList);
        return pageInfo;
    }

    @Override
    public PageInfo<CaseInfo> queryCasesOfOne(Map params) {
        PageHelper.startPage((int)params.get("currentPage"),(int)params.get("pageSize"));
        List<CaseInfo> demoList = caseMapper.queryCasesOfOne(params);
        PageInfo<CaseInfo> pageInfo = new PageInfo<CaseInfo>(demoList);
        return pageInfo;
    }

    @Override
    public PersonInfo queryCaseById(int cId) {
        return caseMapper.queryCaseById(cId);
    }

    @Override
    public PersonInfo queryPersonInfo(int cId) {
        return caseMapper.queryPersonInfo(cId);
    }

    @Override
    public int finishTreat(int pId) {
        return caseMapper.finishTreat(pId);
    }

    @Override
    public String downloadCase(int cId, HttpServletResponse res) throws IOException{
        PersonInfo pInfo = caseMapper.queryCaseById(cId);
        // 整合数据到map中
        Map<String, String> map = createMapByInfo(pInfo);
        // 利用map和template生成word,并写入到response中
        FileInputStream fin = null;
        OutputStream out = null;
        File file = null;
        try {
            String path = CaseServiceImpl.class.getResource("").getPath();
            logger.debug(path);
            logger.info(path);
            configuration.setDirectoryForTemplateLoading(new File("E://"));
            configuration.setDefaultEncoding("UTF-8");
            Template template = configuration.getTemplate("template.ftl");
            String fileName = map.get("name") + "_" + map.get("createTime") + ".docx";
            file = new File(fileName);
            Writer writer = new FileWriter(file);
            template.process(map, writer);
            fin = new FileInputStream(file);
            res.setCharacterEncoding("utf-8");
            res.setContentType("application/msword");
            res.setHeader("Content-Disposition", "attachment;filename="
                            .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            out = res.getOutputStream();
            byte[] buffer = new byte[512];  // 缓冲区
            int bytesToRead = -1;
            while((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(500);
            return "失败";
        } finally {
            if (fin != null) {
                fin.close();
            }
            if (out != null) {
                out.close();
            }
            if (file != null) {
                file.delete();
            }
        }
        return "成功";
    }

    private static Map<String, String> createMapByInfo(PersonInfo pInfo) {
        Map<String, String> map = new HashMap<>();
        CaseInfo caseInfo = pInfo.getCaseInfo();
        map.put("name", pInfo.getName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("createTime", simpleDateFormat.format(caseInfo.getCreateTime()));
        map.put("phone", pInfo.getPhone());
        map.put("sex", pInfo.getSex().equals("man") ? "男" : "女");
        map.put("painClass", DicsUtil.painClassMap.get(pInfo.getPainClass()));
        map.put("painLong", DicsUtil.painLongMap.get(pInfo.getPainLong()));
        map.put("addr", createAddrStr(pInfo.getAddr()));
        map.put("paddr", pInfo.getPaddr());
        map.put("age", pInfo.getAge());

        map.put("frontImg", caseInfo.getFrontImg().substring(22));
        map.put("backImg", caseInfo.getBackImg().substring(22));
        map.put("sideImg", caseInfo.getSideImg().substring(22));
        map.put("zhusu", caseInfo.getZhusu());
        map.put("xbs", caseInfo.getXbs());
        map.put("chati", caseInfo.getChati());
        map.put("zhenduan", caseInfo.getZhenduan());
        map.put("treatLog", caseInfo.getTreatLog());
        return map;
    }

    private static String createAddrStr(AddrInfo addr) {
        StringBuffer addStr = new StringBuffer();
        if ( !StringUtils.isNullOrEmpty(addr.getProvince())) {
            addStr.append(DicsUtil.locationMap.get(addr.getProvince()));
        }
        if ( !StringUtils.isNullOrEmpty(addr.getMunicipal())) {
            addStr.append(DicsUtil.locationMap.get(addr.getMunicipal()));
        }
        if ( !StringUtils.isNullOrEmpty(addr.getCity())) {
            addStr.append(DicsUtil.locationMap.get(addr.getCity()));
        }
        if ( !StringUtils.isNullOrEmpty(addr.getArea())) {
            addStr.append(DicsUtil.locationMap.get(addr.getArea()));
        }
        if ( !StringUtils.isNullOrEmpty(addr.getVillage())) {
            addStr.append(DicsUtil.locationMap.get(addr.getVillage()));
        }
        return addStr.toString();
    }
}
