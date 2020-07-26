package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 中央电中对象 sm_center_middle
 * 
 * @author liukang
 * @date 2019-08-30
 */
public class CenterMiddle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long centerId;

    @Excel(name= "操作人")
    String userName;

    @Excel(name="合作站")
    private String collaborationStation;

    @Excel(name="合作费用")
    private BigDecimal collaborationCost;

    /** 招生老师 */
    @Excel(name = "招生老师")
    private String centerTeacher;

    /** 姓名 */
    @Excel(name = "姓名")
    private String centerName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String centerSex;

    /** 民族 */
    @Excel(name = "民族")
    private String centerNation;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String centerIdnumber;

    /** 层次 */
    @Excel(name = "层次")
    private String centerLevel;

    /** 报读学校 */
    @Excel(name = "报读学校")
    private String centerSchool;

    /** 报读专业 */
    @Excel(name = "报读专业")
    private String centerMajor;

    /** 报名时间 */
    @Excel(name = "报名时间")
    private String centerTime;

    /** 总费用 */
    @Excel(name = "总费用")
    private Double centerTotalCharge;

    /** 缴费详情 */
    @Excel(name = "缴费详情")
    private String centerChargeDetail;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String centerCertificateNumber;

    /** 毕业情况 */
    @Excel(name = "毕业情况")
    private String centerGraduateDetail;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    private Long userId;

    public String getCollaborationStation() {
        return collaborationStation;
    }

    public void setCollaborationStation(String collaborationStation) {
        this.collaborationStation = collaborationStation;
    }

    public BigDecimal getCollaborationCost() {
        return collaborationCost;
    }

    public void setCollaborationCost(BigDecimal collaborationCost) {
        this.collaborationCost = collaborationCost;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCenterId(Long centerId)
    {
        this.centerId = centerId;
    }

    public Long getCenterId() 
    {
        return centerId;
    }
    public void setCenterTeacher(String centerTeacher) 
    {
        this.centerTeacher = centerTeacher;
    }

    public String getCenterTeacher() 
    {
        return centerTeacher;
    }
    public void setCenterName(String centerName) 
    {
        this.centerName = centerName;
    }

    public String getCenterName() 
    {
        return centerName;
    }

    public String getCenterSex() {
        return centerSex;
    }

    public void setCenterSex(String centerSex) {
        this.centerSex = centerSex;
    }

    public void setCenterNation(String centerNation)
    {
        this.centerNation = centerNation;
    }

    public String getCenterNation() 
    {
        return centerNation;
    }
    public void setCenterIdnumber(String centerIdnumber) 
    {
        this.centerIdnumber = centerIdnumber;
    }

    public String getCenterIdnumber() 
    {
        return centerIdnumber;
    }
    public void setCenterLevel(String centerLevel) 
    {
        this.centerLevel = centerLevel;
    }

    public String getCenterLevel() 
    {
        return centerLevel;
    }
    public void setCenterSchool(String centerSchool) 
    {
        this.centerSchool = centerSchool;
    }

    public String getCenterSchool() 
    {
        return centerSchool;
    }
    public void setCenterMajor(String centerMajor) 
    {
        this.centerMajor = centerMajor;
    }

    public String getCenterMajor() 
    {
        return centerMajor;
    }
    public void setCenterTime(String centerTime) 
    {
        this.centerTime = centerTime;
    }

    public String getCenterTime() 
    {
        return centerTime;
    }
    public void setCenterTotalCharge(Double centerTotalCharge) 
    {
        this.centerTotalCharge = centerTotalCharge;
    }

    public Double getCenterTotalCharge() 
    {
        return centerTotalCharge;
    }
    public void setCenterChargeDetail(String centerChargeDetail) 
    {
        this.centerChargeDetail = centerChargeDetail;
    }

    public String getCenterChargeDetail() 
    {
        return centerChargeDetail;
    }
    public void setCenterCertificateNumber(String centerCertificateNumber) 
    {
        this.centerCertificateNumber = centerCertificateNumber;
    }

    public String getCenterCertificateNumber() 
    {
        return centerCertificateNumber;
    }
    public void setCenterGraduateDetail(String centerGraduateDetail) 
    {
        this.centerGraduateDetail = centerGraduateDetail;
    }

    public String getCenterGraduateDetail() 
    {
        return centerGraduateDetail;
    }


    @Override
    public String toString() {
        return "CenterMiddle{" +
                "centerId=" + centerId +
                ", centerTeacher='" + centerTeacher + '\'' +
                ", centerName='" + centerName + '\'' +
                ", centerSex='" + centerSex + '\'' +
                ", centerNation='" + centerNation + '\'' +
                ", centerIdnumber='" + centerIdnumber + '\'' +
                ", centerLevel='" + centerLevel + '\'' +
                ", centerSchool='" + centerSchool + '\'' +
                ", centerMajor='" + centerMajor + '\'' +
                ", centerTime='" + centerTime + '\'' +
                ", centerTotalCharge=" + centerTotalCharge +
                ", centerChargeDetail='" + centerChargeDetail + '\'' +
                ", centerCertificateNumber='" + centerCertificateNumber + '\'' +
                ", centerGraduateDetail='" + centerGraduateDetail + '\'' +
                ", remark='" + remark + '\'' +
                ", userId=" + userId +
                '}';
    }
}
