package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.JobCertificate;
import com.ruoyi.bussiness.service.IJobCertificateService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 职业资格证Controller
 *
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/certificate")
public class JobCertificateController extends BaseController {
    private String prefix = "bussiness/certificate";

    @Autowired
    private IJobCertificateService jobCertificateService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("bussiness:certificate:view")
    @GetMapping()
    public String certificate() {
        return prefix + "/certificate";
    }

    /**
     * 查询职业资格证列表
     */
    @RequiresPermissions("bussiness:certificate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JobCertificate jobCertificate) {

        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = jobCertificate.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                jobCertificate.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                jobCertificate.setUserId(null);
            }
            if(userId!=null && count>0){
                jobCertificate.setUserId(userId);
            }
        }
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
    public AjaxResult export(JobCertificate jobCertificate) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = jobCertificate.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                jobCertificate.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                jobCertificate.setUserId(null);
            }
            if(userId!=null && count>0){
                jobCertificate.setUserId(userId);
            }
        }
        List<JobCertificate> list = jobCertificateService.selectJobCertificateList(jobCertificate);

        //根据权限过滤数据
        //合作费用过滤
        long showcost = currentUser.getRoles().stream().
                filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("showcost")))
                .count();

        long showFee = currentUser.getRoles().stream().
                filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("showFee")))
                .count();

        long showCollaborate = currentUser.getRoles().stream().
                filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("showCollaborate")))
                .count();

        list.stream().forEach(e -> {
            if (showcost == 0 && !currentUser.isAdmin()) {
                e.setCollaborationCost(new BigDecimal(0));
            }
            if (showFee == 0 && !currentUser.isAdmin()) {
                e.setJobTotalCharge(0.0);
            }
            if (showCollaborate == 0 && !currentUser.isAdmin()) {
                e.setJobCorrespondence("");
            }
        });
        ExcelUtil<JobCertificate> util = new ExcelUtil<JobCertificate>(JobCertificate.class);
        return util.exportExcel(list, "职业资格证");
    }

    /**
     * 新增职业资格证
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存职业资格证
     */
    @RequiresPermissions("bussiness:certificate:add")
    @Log(title = "职业资格证", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(JobCertificate jobCertificate) {

        //设置操作人名称
        if(jobCertificate.getUserId() == null){
            SysUser currentUser = ShiroUtils.getSysUser();
            jobCertificate.setUserId(currentUser.getUserId());
            jobCertificate.setUserName(currentUser.getUserName());
        }else{
            SysUser sysUser = userService.selectUserById(jobCertificate.getUserId());
            jobCertificate.setUserName(sysUser.getUserName());
        }
        return toAjax(jobCertificateService.insertJobCertificate(jobCertificate));
    }

    /**
     * 修改职业资格证
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap) {
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
    public AjaxResult editSave(JobCertificate jobCertificate) {
        // 修改操作人
        if(jobCertificate.getUserId()!=null){
            SysUser sysUser = userService.selectUserById(jobCertificate.getUserId());
            jobCertificate.setUserName(sysUser.getUserName());
        }
        return toAjax(jobCertificateService.updateJobCertificate(jobCertificate));
    }

    /**
     * 删除职业资格证
     */
    @RequiresPermissions("bussiness:certificate:remove")
    @Log(title = "职业资格证", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(jobCertificateService.deleteJobCertificateByIds(ids));
    }

    /**
     * 职业资格证模板下载
     *
     * @return
     */
    @RequiresPermissions("bussiness:certificate:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<JobCertificate> util = new ExcelUtil<JobCertificate>(JobCertificate.class);
        return util.importTemplateExcel("职业资格证");
    }

    @Log(title = "导入职业资格证", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:certificate:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<JobCertificate> util = new ExcelUtil<JobCertificate>(JobCertificate.class);
        List<JobCertificate> jobCertificateList = util.importExcel(file.getInputStream());
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                jobCertificateList.stream().forEach(jobCertificate -> {
                    jobCertificate.setUserId(currentUser.getUserId());
                });
            }
        }

        String message = jobCertificateService.importCertificate(jobCertificateList);
        return AjaxResult.success(message);
    }
}
