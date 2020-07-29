package com.ruoyi.web.controller.bussiness;

import com.ruoyi.bussiness.domain.SelfExam;
import com.ruoyi.bussiness.service.ISelfExamService;
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
 * 自学考试Controller
 *
 * @author liukang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/bussiness/selfexam")
public class SelfExamController extends BaseController {
    private String prefix = "bussiness/selfexam";

    @Autowired
    private ISelfExamService selfExamService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("bussiness:selfexam:view")
    @GetMapping()
    public String selfexam() {
        return prefix + "/selfexam";
    }

    /**
     * 查询自学考试列表
     */
    @RequiresPermissions("bussiness:selfexam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SelfExam selfExam) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId =selfExam.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                selfExam.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                selfExam.setUserId(null);
            }
            if(count>0 && userId!=null){
                selfExam.setUserId(userId);
            }
        }
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
    public AjaxResult export(SelfExam selfExam) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            Long userId =selfExam.getUserId();
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                selfExam.setUserId(currentUser.getUserId());
            }
            //统计该用户的角色是否有包含所有数据的角色
            long count = currentUser.getRoles().stream().
                    filter(e -> e.getRoleId().toString().equals(iSysConfigService.selectConfigByKey("alldata.roleId")))
                    .count();
            if (count > 0) {
                selfExam.setUserId(null);
            }
            if(count>0 && userId!=null){
                selfExam.setUserId(userId);
            }

        }
        List<SelfExam> list = selfExamService.selectSelfExamList(selfExam);
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
                e.setSelfTotalCharge(0.0);
                e.setSelfOneyearCharge(0.0);
                e.setSelfTwoyearCharge(0L);
            }
            if (showCollaborate == 0 && !currentUser.isAdmin()) {
                e.setCollaborationStation("");
            }
        });
        ExcelUtil<SelfExam> util = new ExcelUtil<SelfExam>(SelfExam.class);
        return util.exportExcel(list, "自学考试");
    }

    /**
     * 新增自学考试
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存自学考试
     */
    @RequiresPermissions("bussiness:selfexam:add")
    @Log(title = "自学考试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SelfExam selfExam) {

        //设置操作人名称
        if(selfExam.getUserId() == null){
            SysUser currentUser = ShiroUtils.getSysUser();
            selfExam.setUserId(currentUser.getUserId());
            selfExam.setUserName(currentUser.getUserName());
        }else{
            SysUser sysUser = userService.selectUserById(selfExam.getUserId());
            selfExam.setUserName(sysUser.getUserName());
        }
        return toAjax(selfExamService.insertSelfExam(selfExam));
    }

    /**
     * 修改自学考试
     */
    @GetMapping("/edit/{selfId}")
    public String edit(@PathVariable("selfId") Long selfId, ModelMap mmap) {
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
    public AjaxResult editSave(SelfExam selfExam) {
        // 修改操作人
        if(selfExam.getUserId()!=null){
            SysUser sysUser = userService.selectUserById(selfExam.getUserId());
            selfExam.setUserName(sysUser.getUserName());
        }
        return toAjax(selfExamService.updateSelfExam(selfExam));
    }

    /**
     * 删除自学考试
     */
    @RequiresPermissions("bussiness:selfexam:remove")
    @Log(title = "自学考试", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(selfExamService.deleteSelfExamByIds(ids));
    }

    /**
     * 自学考试模板下载
     *
     * @return
     */
    @RequiresPermissions("bussiness:selfexam:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<SelfExam> util = new ExcelUtil<SelfExam>(SelfExam.class);
        return util.importTemplateExcel("职业资格证");
    }

    @Log(title = "导入自学考试", businessType = BusinessType.IMPORT)
    @RequiresPermissions("bussiness:selfexam:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<SelfExam> util = new ExcelUtil<SelfExam>(SelfExam.class);
        List<SelfExam> selfExamList = util.importExcel(file.getInputStream());
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        if (currentUser != null) {
            // 如果不是超级管理员
            if (!currentUser.isAdmin()) {
                selfExamList.stream().forEach(selfExam -> {
                    selfExam.setUserId(currentUser.getUserId());
                });
            }
        }
        String message = selfExamService.importSelfExam(selfExamList);
        return AjaxResult.success(message);
    }
}
