package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 远程教育对象 sm_distance_education
 * 
 * @author liukang
 * @date 2019-08-30
 */
public class DistanceEducation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键编号 */
    private Long distanceId;

    @Excel(name= "操作人")
    String userName;

    @Excel(name="合作站")
    private String collaborationStation;

    @Excel(name="合作费用")
    private BigDecimal collaborationCost;

    /** 招生老师 */
    @Excel(name = "招生老师")
    private String distanceTeacher;

    /** 函授点 */
    @Excel(name = "函授点")
    private String distancePoints;

    /** 姓名 */
    @Excel(name = "姓名")
    private String distanceName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String distanceSex;

    /** 民族 */
    @Excel(name = "民族")
    private String distanceNation;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String distanceIdnumber;

    /** 层次 */
    @Excel(name = "层次")
    private String distanceLevel;

    /** 报读学校 */
    @Excel(name = "报读学校")
    private String distanceSchool;

    /** 报读专业 */
    @Excel(name = "报读专业")
    private String distanceMajor;

    /** 批次 */
    @Excel(name = "批次")
    private String batch;

    /** 期数 */
    @Excel(name = "期数")
    private String distancePeriods;

    /** 学号 */
    @Excel(name = "学号")
    private String distanceStudentNumber;

    /** 总学费 */
    @Excel(name = "总学费")
    private Double distanceTotalCharge;

    /** 第一年缴费 */
    @Excel(name = "第一年缴费")
    private Double distanceOneyearCharge;

    /** 第二年缴费 */
    @Excel(name = "第二年缴费")
    private Double distanceTwoyearCharge;

    /** 缴费情况 */
    @Excel(name = "缴费情况")
    private String distanceChargeDetail;

    /** 录取情况 */
    @Excel(name = "录取情况")
    private String distanceEnroll;

    /** 毕业情况 */
    @Excel(name = "毕业情况")
    private String centerGraduateDetail;
    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    private Long userId;


    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCenterGraduateDetail() {
        return centerGraduateDetail;
    }

    public void setCenterGraduateDetail(String centerGraduateDetail) {
        this.centerGraduateDetail = centerGraduateDetail;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDistanceId(Long distanceId)
    {
        this.distanceId = distanceId;
    }

    public Long getDistanceId() 
    {
        return distanceId;
    }
    public void setDistanceTeacher(String distanceTeacher) 
    {
        this.distanceTeacher = distanceTeacher;
    }

    public String getDistanceTeacher() 
    {
        return distanceTeacher;
    }
    public void setDistancePoints(String distancePoints) 
    {
        this.distancePoints = distancePoints;
    }

    public String getDistancePoints() 
    {
        return distancePoints;
    }
    public void setDistanceName(String distanceName) 
    {
        this.distanceName = distanceName;
    }

    public String getDistanceName() 
    {
        return distanceName;
    }

    public String getDistanceSex() {
        return distanceSex;
    }

    public void setDistanceSex(String distanceSex) {
        this.distanceSex = distanceSex;
    }

    public void setDistanceNation(String distanceNation)
    {
        this.distanceNation = distanceNation;
    }

    public String getDistanceNation() 
    {
        return distanceNation;
    }
    public void setDistanceIdnumber(String distanceIdnumber) 
    {
        this.distanceIdnumber = distanceIdnumber;
    }

    public String getDistanceIdnumber() 
    {
        return distanceIdnumber;
    }
    public void setDistanceLevel(String distanceLevel) 
    {
        this.distanceLevel = distanceLevel;
    }

    public String getDistanceLevel() 
    {
        return distanceLevel;
    }
    public void setDistanceSchool(String distanceSchool) 
    {
        this.distanceSchool = distanceSchool;
    }

    public String getDistanceSchool() 
    {
        return distanceSchool;
    }
    public void setDistanceMajor(String distanceMajor) 
    {
        this.distanceMajor = distanceMajor;
    }

    public String getDistanceMajor() 
    {
        return distanceMajor;
    }
    public void setDistancePeriods(String distancePeriods) 
    {
        this.distancePeriods = distancePeriods;
    }

    public String getDistancePeriods() 
    {
        return distancePeriods;
    }
    public void setDistanceStudentNumber(String distanceStudentNumber) 
    {
        this.distanceStudentNumber = distanceStudentNumber;
    }

    public String getDistanceStudentNumber() 
    {
        return distanceStudentNumber;
    }
    public void setDistanceTotalCharge(Double distanceTotalCharge) 
    {
        this.distanceTotalCharge = distanceTotalCharge;
    }

    public Double getDistanceTotalCharge() 
    {
        return distanceTotalCharge;
    }
    public void setDistanceOneyearCharge(Double distanceOneyearCharge) 
    {
        this.distanceOneyearCharge = distanceOneyearCharge;
    }

    public Double getDistanceOneyearCharge() 
    {
        return distanceOneyearCharge;
    }
    public void setDistanceTwoyearCharge(Double distanceTwoyearCharge) 
    {
        this.distanceTwoyearCharge = distanceTwoyearCharge;
    }

    public Double getDistanceTwoyearCharge() 
    {
        return distanceTwoyearCharge;
    }
    public void setDistanceChargeDetail(String distanceChargeDetail) 
    {
        this.distanceChargeDetail = distanceChargeDetail;
    }

    public String getDistanceChargeDetail() 
    {
        return distanceChargeDetail;
    }
    public void setDistanceEnroll(String distanceEnroll) 
    {
        this.distanceEnroll = distanceEnroll;
    }

    public String getDistanceEnroll() 
    {
        return distanceEnroll;
    }

    @Override
    public String toString() {
        return "DistanceEducation{" +
                "distanceId=" + distanceId +
                ", distanceTeacher='" + distanceTeacher + '\'' +
                ", distancePoints='" + distancePoints + '\'' +
                ", distanceName='" + distanceName + '\'' +
                ", distanceSex='" + distanceSex + '\'' +
                ", distanceNation='" + distanceNation + '\'' +
                ", distanceIdnumber='" + distanceIdnumber + '\'' +
                ", distanceLevel='" + distanceLevel + '\'' +
                ", distanceSchool='" + distanceSchool + '\'' +
                ", distanceMajor='" + distanceMajor + '\'' +
                ", distancePeriods='" + distancePeriods + '\'' +
                ", distanceStudentNumber='" + distanceStudentNumber + '\'' +
                ", distanceTotalCharge=" + distanceTotalCharge +
                ", distanceOneyearCharge=" + distanceOneyearCharge +
                ", distanceTwoyearCharge=" + distanceTwoyearCharge +
                ", distanceChargeDetail='" + distanceChargeDetail + '\'' +
                ", distanceEnroll='" + distanceEnroll + '\'' +
                ", remark='" + remark + '\'' +
                ", userId=" + userId +
                '}';
    }
}
