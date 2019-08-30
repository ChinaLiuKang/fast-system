package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.SelfExam;
import com.ruoyi.bussiness.service.ISelfExamService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 自学考试Controller
 * 
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/selfexam")
public class SelfExamController extends BaseController
{
    private String prefix = "bussiness/selfexam";

    @Autowired
    private ISelfExamService selfExamService;

    @RequiresPermissions("bussiness:selfexam:view")
    @GetMapping()
    public String selfexam()
    {
        return prefix + "/selfexam";
    }

    /**
     * 查询自学考试列表
     */
    @RequiresPermissions("bussiness:selfexam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SelfExam selfExam)
    {
        startPage();
        List<SelfExam> list = selfExamService.selectSelfExamList(selfExam);
        return getDataTable(list);
    }

    /**
     * 导出自学考试列表
     */
    @RequiresPermissions("bussiness:selfexam:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SelfExam selfExam)
    {
        List<SelfExam> list = selfExamService.selectSelfExamList(selfExam);
        ExcelUtil<SelfExam> util = new ExcelUtil<SelfExam>(SelfExam.class);
        return util.exportExcel(list, "自学考试");
    }

    /**
     * 新增自学考试
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存自学考试
     */
    @RequiresPermissions("bussiness:selfexam:add")
    @Log(title = "自学考试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SelfExam selfExam)
    {
        return toAjax(selfExamService.insertSelfExam(selfExam));
    }

    /**
     * 修改自学考试
     */
    @GetMapping("/edit/{selfId}")
    public String edit(@PathVariable("selfId") Long selfId, ModelMap mmap)
    {
        SelfExam selfExam = selfExamService.selectSelfExamById(selfId);
        mmap.put("selfExam", selfExam);
        return prefix + "/edit";
    }

    /**
     * 修改保存自学考试
     */
    @RequiresPermissions("bussiness:selfexam:edit")
    @Log(title = "自学考试", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SelfExam selfExam)
    {
        return toAjax(selfExamService.updateSelfExam(selfExam));
    }

    /**
     * 删除自学考试
     */
    @RequiresPermissions("bussiness:selfexam:remove")
    @Log(title = "自学考试", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(selfExamService.deleteSelfExamByIds(ids));
    }

    /**
     * 自学考试模板下载
     * @return
     */
    @RequiresPermissions("bussiness:selfexam:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SelfExam> util = new ExcelUtil<SelfExam>(SelfExam.class);
        return util.importTemplateExcel("职业资格证");
    }

    @Log(title = "导入自学考试", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:selfexam:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<SelfExam> util = new ExcelUtil<SelfExam>(SelfExam.class);
        List<SelfExam> jobCertificateList = util.importExcel(file.getInputStream());
        String message = selfExamService.importSelfExam(jobCertificateList);
        return AjaxResult.success(message);
    }
}
