package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("distanceId", getDistanceId())
            .append("distanceTeacher", getDistanceTeacher())
            .append("distancePoints", getDistancePoints())
            .append("distanceName", getDistanceName())
            .append("distanceSex", getDistanceSex())
            .append("distanceNation", getDistanceNation())
            .append("distanceIdnumber", getDistanceIdnumber())
            .append("distanceLevel", getDistanceLevel())
            .append("distanceSchool", getDistanceSchool())
            .append("distanceMajor", getDistanceMajor())
            .append("distancePeriods", getDistancePeriods())
            .append("distanceStudentNumber", getDistanceStudentNumber())
            .append("distanceTotalCharge", getDistanceTotalCharge())
            .append("distanceOneyearCharge", getDistanceOneyearCharge())
            .append("distanceTwoyearCharge", getDistanceTwoyearCharge())
            .append("distanceChargeDetail", getDistanceChargeDetail())
            .append("distanceEnroll", getDistanceEnroll())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
