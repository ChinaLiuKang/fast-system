package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.CenterMiddle;
import com.ruoyi.bussiness.domain.DistanceEducation;
import com.ruoyi.bussiness.service.IDistanceEducationService;
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
 * 远程教育Controller
 *
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/education")
public class DistanceEducationController extends BaseController {
    private String prefix = "bussiness/education";

    @Autowired
    private IDistanceEducationService distanceEducationService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("bussiness:education:view")
    @GetMapping()
    public String education() {
        return prefix + "/education";
    }

    /**
     * 查询远程教育列表
     */
    @RequiresPermissions("bussiness:education:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DistanceEducation distanceEducation) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = distanceEducation.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                distanceEducation.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                distanceEducation.setUserId(null);
            }
            if(userId!=null && count>0){
                distanceEducation.setUserId(userId);
            }

        }
        startPage();
        List<DistanceEducation> list = distanceEducationService.selectDistanceEducationList(distanceEducation);
        return getDataTable(list);
    }

    /**
     * 导出远程教育列表
     */
    @RequiresPermissions("bussiness:education:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DistanceEducation distanceEducation) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = distanceEducation.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                distanceEducation.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                distanceEducation.setUserId(null);
            }
            if(userId!=null && count>0){
                distanceEducation.setUserId(userId);
            }
        }
        List<DistanceEducation> list = distanceEducationService.selectDistanceEducationList(distanceEducation);
        //根据权限过滤数据
        //合作费用过滤
        long showcost = currentUser.getRoles().stream().
                filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("showcost")))
                .count();

        long showFee = currentUser.getRoles().stream().
                filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("showFee")))
                .count();

        list.stream().forEach(e -> {
            if (showcost == 0 && !currentUser.isAdmin()) {
                e.setCollaborationCost(new BigDecimal(0));
            }
            if (showFee == 0 && !currentUser.isAdmin()) {
                e.setDistanceTotalCharge(0.0);
                e.setDistanceOneyearCharge(0.0);
                e.setDistanceTwoyearCharge(0.0);
            }
        });
        ExcelUtil<DistanceEducation> util = new ExcelUtil<DistanceEducation>(DistanceEducation.class);
        return util.exportExcel(list, "远程教育");
    }

    /**
     * 新增远程教育
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存远程教育
     */
    @RequiresPermissions("bussiness:education:add")
    @Log(title = "远程教育", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DistanceEducation distanceEducation) {

        //设置操作人名称
        if(distanceEducation.getUserId() == null){
            SysUser currentUser = ShiroUtils.getSysUser();
            distanceEducation.setUserId(currentUser.getUserId());
            distanceEducation.setUserName(currentUser.getUserName());
        }else{
            SysUser sysUser = userService.selectUserById(distanceEducation.getUserId());
            distanceEducation.setUserName(sysUser.getUserName());
        }
        return toAjax(distanceEducationService.insertDistanceEducation(distanceEducation));
    }

    /**
     * 修改远程教育
     */
    @GetMapping("/edit/{distanceId}")
    public String edit(@PathVariable("distanceId") Long distanceId, ModelMap mmap) {
        DistanceEducation distanceEducation = distanceEducationService.selectDistanceEducationById(distanceId);
        mmap.put("distanceEducation", distanceEducation);
        return prefix + "/edit";
    }

    /**
     * 修改保存远程教育
     */
    @RequiresPermissions("bussiness:education:edit")
    @Log(title = "远程教育", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DistanceEducation distanceEducation) {
        // 修改操作人
        if(distanceEducation.getUserId()!=null){
            SysUser sysUser = userService.selectUserById(distanceEducation.getUserId());
            distanceEducation.setUserName(sysUser.getUserName());
        }
        return toAjax(distanceEducationService.updateDistanceEducation(distanceEducation));
    }

    /**
     * 删除远程教育
     */
    @RequiresPermissions("bussiness:education:remove")
    @Log(title = "远程教育", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(distanceEducationService.deleteDistanceEducationByIds(ids));
    }

    /**
     * 远程教育模板下载
     *
     * @return
     */
    @RequiresPermissions("bussiness:education:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<CenterMiddle> util = new ExcelUtil<CenterMiddle>(CenterMiddle.class);
        return util.importTemplateExcel("远程教育");
    }

    @Log(title = "导入远程教育", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:education:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<DistanceEducation> util = new ExcelUtil<DistanceEducation>(DistanceEducation.class);
        List<DistanceEducation> distanceEducationList = util.importExcel(file.getInputStream());
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                distanceEducationList.stream().forEach(distanceEducation -> {
                    distanceEducation.setUserId(currentUser.getUserId());
                });
            }
        }
        String message = distanceEducationService.importDistance(distanceEducationList);
        return AjaxResult.success(message);
    }
}
