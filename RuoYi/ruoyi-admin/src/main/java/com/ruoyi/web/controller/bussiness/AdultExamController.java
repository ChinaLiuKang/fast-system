package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.AdultExam;
import com.ruoyi.bussiness.service.IAdultExamService;
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
 * 成考信息Controller
 *
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/adultexam")
public class AdultExamController extends BaseController {
    private String prefix = "bussiness/adultexam";

    @Autowired
    private IAdultExamService adultExamService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("bussiness:adultexam:view")
    @GetMapping()
    public String adultexam() {
        return prefix + "/adultexam";
    }

    /**
     * 查询成考信息列表
     */
    @RequiresPermissions("bussiness:adultexam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AdultExam adultExam) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = adultExam.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                adultExam.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                adultExam.setUserId(null);
            }
            if (userId != null && count > 0) {
                adultExam.setUserId(userId);
            }
        }
        startPage();
        List<AdultExam> list = adultExamService.selectAdultExamList(adultExam);
        return getDataTable(list);
    }

    /**
     * 导出成考信息列表
     */
    @RequiresPermissions("bussiness:adultexam:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AdultExam adultExam) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = adultExam.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                adultExam.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                adultExam.setUserId(null);
            }
            if (userId != null && count > 0) {
                adultExam.setUserId(userId);
            }
        }
        List<AdultExam> list = adultExamService.selectAdultExamList(adultExam);

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
                e.setAdultChargeStandard(0.0);
                e.setAdultOneyearCharge(0.0);
                e.setAdultTwoyearCharge(0L);
            }
            if (showCollaborate == 0 && !currentUser.isAdmin()) {
                e.setAdultCorrespondence("");
            }
        });
        ExcelUtil<AdultExam> util = new ExcelUtil<AdultExam>(AdultExam.class);
        return util.exportExcel(list, "成考信息");
    }

    /**
     * 成考信息模板下载
     *
     * @return
     */
    @RequiresPermissions("bussiness:adultexam:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<AdultExam> util = new ExcelUtil<AdultExam>(AdultExam.class);
        return util.importTemplateExcel("成考信息");
    }

    @Log(title = "导入成考信息", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:adultexam:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<AdultExam> util = new ExcelUtil<AdultExam>(AdultExam.class);
        List<AdultExam> userList = util.importExcel(file.getInputStream());
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                userList.stream().forEach(e -> {
                    e.setUserId(currentUser.getUserId());
                });
            }
        }
        String message = adultExamService.importAdultExam(userList);
        return AjaxResult.success(message);
    }

    /**
     * 新增成考信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存成考信息
     */
    @RequiresPermissions("bussiness:adultexam:add")
    @Log(title = "成考信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AdultExam adultExam) {
        //设置操作人名称
        if (adultExam.getUserId() == null) {
            SysUser currentUser = ShiroUtils.getSysUser();
            adultExam.setUserId(currentUser.getUserId());
            adultExam.setUserName(currentUser.getUserName());
        } else {
            //没有设置操作人取当前人
            SysUser sysUser = userService.selectUserById(adultExam.getUserId());
            adultExam.setUserName(sysUser.getUserName());
        }
        return toAjax(adultExamService.insertAdultExam(adultExam));
    }

    /**
     * 修改成考信息
     */
    @GetMapping("/edit/{adultId}")
    public String edit(@PathVariable("adultId") Long adultId, ModelMap mmap) {
        AdultExam adultExam = adultExamService.selectAdultExamById(adultId);
        mmap.put("adultExam", adultExam);
        return prefix + "/edit";
    }

    /**
     * 修改保存成考信息
     */
    @RequiresPermissions("bussiness:adultexam:edit")
    @Log(title = "成考信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AdultExam adultExam) {
        // 修改操作人
        if (adultExam.getUserId() != null) {
            SysUser sysUser = userService.selectUserById(adultExam.getUserId());
            adultExam.setUserName(sysUser.getUserName());
        }
        return toAjax(adultExamService.updateAdultExam(adultExam));
    }

    /**
     * 删除成考信息
     */
    @RequiresPermissions("bussiness:adultexam:remove")
    @Log(title = "成考信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(adultExamService.deleteAdultExamByIds(ids));
    }
}
