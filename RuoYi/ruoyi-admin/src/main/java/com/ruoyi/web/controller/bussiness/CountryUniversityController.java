package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.CountryUniversity;
import com.ruoyi.bussiness.service.ICountryUniversityService;
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

import java.util.List;

/**
 * 国家开放大学Controller
 * 
 * @author liukang
 * @date 2020-07-26
 */
@Controller
@RequestMapping("/bussiness/university")
public class CountryUniversityController extends BaseController
{
    private String prefix = "bussiness/university";

    @Autowired
    private ICountryUniversityService countryUniversityService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("bussiness:university:view")
    @GetMapping()
    public String university()
    {
        return prefix + "/university";
    }

    /**
     * 查询国家开放大学列表
     */
    @RequiresPermissions("bussiness:university:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CountryUniversity countryUniversity)
    {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = countryUniversity.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                countryUniversity.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                countryUniversity.setUserId(null);
            }
            if (userId != null && count > 0) {
                countryUniversity.setUserId(userId);
            }
        }
        startPage();
        List<CountryUniversity> list = countryUniversityService.selectCountryUniversityList(countryUniversity);
        return getDataTable(list);
    }

    /**
     * 导出国家开放大学列表
     */
    @RequiresPermissions("bussiness:university:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CountryUniversity countryUniversity)
    {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId = countryUniversity.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                countryUniversity.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                countryUniversity.setUserId(null);
            }
            if (userId != null && count > 0) {
                countryUniversity.setUserId(userId);
            }
        }
        List<CountryUniversity> list = countryUniversityService.selectCountryUniversityList(countryUniversity);

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
                e.setCollaborationCost(0.0);
            }
            if (showFee == 0 && !currentUser.isAdmin()) {
                e.setTotalCharge(0.0);
                e.setOneyearCharge(0.0);
                e.setTwoyearCharge(0.0);
            }
            if (showCollaborate == 0 && !currentUser.isAdmin()) {
                e.setCollaborationStation("");
            }
        });
        ExcelUtil<CountryUniversity> util = new ExcelUtil<CountryUniversity>(CountryUniversity.class);
        return util.exportExcel(list, "university");
    }


    /**
     * 国家开放大学信息模板下载
     *
     * @return
     */
    @RequiresPermissions("bussiness:university:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<CountryUniversity> util = new ExcelUtil<CountryUniversity>(CountryUniversity.class);
        return util.importTemplateExcel("国家开放大学");
    }


    @Log(title = "导入国家开放大学", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:university:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<CountryUniversity> util = new ExcelUtil<CountryUniversity>(CountryUniversity.class);
        List<CountryUniversity> userList = util.importExcel(file.getInputStream());
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
        String message = countryUniversityService.importAdultExam(userList);
        return AjaxResult.success(message);
    }

    /**
     * 新增国家开放大学
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存国家开放大学
     */
    @RequiresPermissions("bussiness:university:add")
    @Log(title = "国家开放大学", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CountryUniversity countryUniversity)
    {
        //设置操作人名称
        if (countryUniversity.getUserId() == null) {
            SysUser currentUser = ShiroUtils.getSysUser();
            countryUniversity.setUserId(currentUser.getUserId());
            countryUniversity.setUserName(currentUser.getUserName());
        } else {
            //没有设置操作人取当前人
            SysUser sysUser = userService.selectUserById(countryUniversity.getUserId());
            countryUniversity.setUserName(sysUser.getUserName());
        }
        return toAjax(countryUniversityService.insertCountryUniversity(countryUniversity));
    }

    /**
     * 修改国家开放大学
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CountryUniversity countryUniversity = countryUniversityService.selectCountryUniversityById(id);
        mmap.put("countryUniversity", countryUniversity);
        return prefix + "/edit";
    }

    /**
     * 修改保存国家开放大学
     */
    @RequiresPermissions("bussiness:university:edit")
    @Log(title = "国家开放大学", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CountryUniversity countryUniversity)
    {
        // 修改操作人
        if (countryUniversity.getUserId() != null) {
            SysUser sysUser = userService.selectUserById(countryUniversity.getUserId());
            countryUniversity.setUserName(sysUser.getUserName());
        }
        return toAjax(countryUniversityService.updateCountryUniversity(countryUniversity));
    }

    /**
     * 删除国家开放大学
     */
    @RequiresPermissions("bussiness:university:remove")
    @Log(title = "国家开放大学", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(countryUniversityService.deleteCountryUniversityByIds(ids));
    }
}
