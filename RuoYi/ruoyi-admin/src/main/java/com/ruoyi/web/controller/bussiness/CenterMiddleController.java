package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.CenterMiddle;
import com.ruoyi.bussiness.service.ICenterMiddleService;
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
 * 中央电中Controller
 *
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/middle")
public class CenterMiddleController extends BaseController {
    private String prefix = "bussiness/middle";

    @Autowired
    private ICenterMiddleService centerMiddleService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("bussiness:middle:view")
    @GetMapping()
    public String middle() {
        return prefix + "/middle";
    }

    /**
     * 查询中央电中列表
     */
    @RequiresPermissions("bussiness:middle:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CenterMiddle centerMiddle) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = centerMiddle.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                centerMiddle.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                centerMiddle.setUserId(null);
            }
            if(userId!=null && count>0){
                centerMiddle.setUserId(userId);
            }
        }
        startPage();
        List<CenterMiddle> list = centerMiddleService.selectCenterMiddleList(centerMiddle);
        return getDataTable(list);
    }

    /**
     * 导出中央电中列表
     */
    @RequiresPermissions("bussiness:middle:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CenterMiddle centerMiddle) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = centerMiddle.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                centerMiddle.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                centerMiddle.setUserId(null);
            }
            if(userId!=null && count>0){
                centerMiddle.setUserId(userId);
            }
        }
        List<CenterMiddle> list = centerMiddleService.selectCenterMiddleList(centerMiddle);
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
                e.setCenterTotalCharge(0.0);
            }
            if (showCollaborate == 0 && !currentUser.isAdmin()) {
                e.setCollaborationStation("");
            }
        });
        ExcelUtil<CenterMiddle> util = new ExcelUtil<CenterMiddle>(CenterMiddle.class);
        return util.exportExcel(list, "中央电中");
    }

    /**
     * 新增中央电中
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存中央电中
     */
    @RequiresPermissions("bussiness:middle:add")
    @Log(title = "中央电中", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CenterMiddle centerMiddle) {

        //设置操作人名称
        if(centerMiddle.getUserId() == null){
            SysUser currentUser = ShiroUtils.getSysUser();
            centerMiddle.setUserId(currentUser.getUserId());
            centerMiddle.setUserName(currentUser.getUserName());
        }else{
            SysUser sysUser = userService.selectUserById(centerMiddle.getUserId());
            centerMiddle.setUserName(sysUser.getUserName());
        }
        return toAjax(centerMiddleService.insertCenterMiddle(centerMiddle));
    }

    /**
     * 修改中央电中
     */
    @GetMapping("/edit/{centerId}")
    public String edit(@PathVariable("centerId") Long centerId, ModelMap mmap) {
        CenterMiddle centerMiddle = centerMiddleService.selectCenterMiddleById(centerId);
        mmap.put("centerMiddle", centerMiddle);
        return prefix + "/edit";
    }

    /**
     * 修改保存中央电中
     */
    @RequiresPermissions("bussiness:middle:edit")
    @Log(title = "中央电中", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CenterMiddle centerMiddle) {
        // 修改操作人
        if(centerMiddle.getUserId()!=null){
            SysUser sysUser = userService.selectUserById(centerMiddle.getUserId());
            centerMiddle.setUserName(sysUser.getUserName());
        }
        return toAjax(centerMiddleService.updateCenterMiddle(centerMiddle));
    }

    /**
     * 删除中央电中
     */
    @RequiresPermissions("bussiness:middle:remove")
    @Log(title = "中央电中", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(centerMiddleService.deleteCenterMiddleByIds(ids));
    }


    /**
     * 中央电中模板下载
     *
     * @return
     */
    @RequiresPermissions("bussiness:middle:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<CenterMiddle> util = new ExcelUtil<CenterMiddle>(CenterMiddle.class);
        return util.importTemplateExcel("中央电中");
    }

    @Log(title = "导入中央电中", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:middle:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<CenterMiddle> util = new ExcelUtil<CenterMiddle>(CenterMiddle.class);
        List<CenterMiddle> userList = util.importExcel(file.getInputStream());
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                userList.stream().forEach(centerMiddle -> centerMiddle.setUserId(currentUser.getUserId()));
            }
        }
        String message = centerMiddleService.importCenterMiddle(userList);
        return AjaxResult.success(message);
    }
}
