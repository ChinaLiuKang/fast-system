package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.JobCertificate;
import com.ruoyi.bussiness.service.IJobCertificateService;
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
 * 职业资格证Controller
 * 
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/certificate")
public class JobCertificateController extends BaseController
{
    private String prefix = "bussiness/certificate";

    @Autowired
    private IJobCertificateService jobCertificateService;

    @RequiresPermissions("bussiness:certificate:view")
    @GetMapping()
    public String certificate()
    {
        return prefix + "/certificate";
    }

    /**
     * 查询职业资格证列表
     */
    @RequiresPermissions("bussiness:certificate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JobCertificate jobCertificate)
    {
        startPage();
        List<JobCertificate> list = jobCertificateService.selectJobCertificateList(jobCertificate);
        return getDataTable(list);
    }

    /**
     * 导出职业资格证列表
     */
    @RequiresPermissions("bussiness:certificate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JobCertificate jobCertificate)
    {
        List<JobCertificate> list = jobCertificateService.selectJobCertificateList(jobCertificate);
        ExcelUtil<JobCertificate> util = new ExcelUtil<JobCertificate>(JobCertificate.class);
        return util.exportExcel(list, "职业资格证");
    }

    /**
     * 新增职业资格证
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存职业资格证
     */
    @RequiresPermissions("bussiness:certificate:add")
    @Log(title = "职业资格证", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(JobCertificate jobCertificate)
    {
        return toAjax(jobCertificateService.insertJobCertificate(jobCertificate));
    }

    /**
     * 修改职业资格证
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap)
    {
        JobCertificate jobCertificate = jobCertificateService.selectJobCertificateById(jobId);
        mmap.put("jobCertificate", jobCertificate);
        return prefix + "/edit";
    }

    /**
     * 修改保存职业资格证
     */
    @RequiresPermissions("bussiness:certificate:edit")
    @Log(title = "职业资格证", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(JobCertificate jobCertificate)
    {
        return toAjax(jobCertificateService.updateJobCertificate(jobCertificate));
    }

    /**
     * 删除职业资格证
     */
    @RequiresPermissions("bussiness:certificate:remove")
    @Log(title = "职业资格证", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobCertificateService.deleteJobCertificateByIds(ids));
    }

    /**
     * 职业资格证模板下载
     * @return
     */
    @RequiresPermissions("bussiness:certificate:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<JobCertificate> util = new ExcelUtil<JobCertificate>(JobCertificate.class);
        return util.importTemplateExcel("职业资格证");
    }

    @Log(title = "导入职业资格证", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:certificate:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<JobCertificate> util = new ExcelUtil<JobCertificate>(JobCertificate.class);
        List<JobCertificate> jobCertificateList = util.importExcel(file.getInputStream());
        String message = jobCertificateService.importCertificate(jobCertificateList);
        return AjaxResult.success(message);
    }
}
