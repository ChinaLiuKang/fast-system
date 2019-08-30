package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成考信息对象 sm_adult_exam
 * 
 * @author liukang
 * @date 2019-08-30
 */
public class AdultExam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键编号 */
    private Long adultId;

    /** 招生老师 */
    @Excel(name = "招生老师")
    private String adultTeacher;

    /** 姓名 */
    @Excel(name = "姓名")
    private String adultName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String adultSex;

    /** 民族 */
    @Excel(name = "民族")
    private String adultNation;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String adultIdnumber;

    /** 层次 */
    @Excel(name = "层次")
    private String adultLevel;

    /** 类别 */
    @Excel(name = "类别")
    private String adultType;

    /** 报读学校 */
    @Excel(name = "报读学校")
    private String adultSchool;

    /** 报读专业 */
    @Excel(name = "报读专业")
    private String adultMajor;

    /** 年级 */
    @Excel(name = "年级")
    private String adultGrade;

    /** 账户名 */
    @Excel(name = "账户名")
    private String adultAccount;

    /** 准考证号 */
    @Excel(name = "准考证号")
    private String adultExamNumber;

    /** 考生号 */
    @Excel(name = "考生号")
    private String adultStudentNumber;

    /** 总加分 */
    @Excel(name = "总加分")
    private Double adultAddScore;

    /** 收费标准 */
    @Excel(name = "收费标准")
    private Double adultChargeStandard;

    /** 第一年缴费 */
    @Excel(name = "第一年缴费")
    private Double adultOneyearCharge;

    /** 第二年缴费 */
    @Excel(name = "第二年缴费")
    private Long adultTwoyearCharge;

    /** 缴费情况 */
    @Excel(name = "缴费情况")
    private String adultChargeDetail;

    /** 录取情况 */
    @Excel(name = "录取情况")
    private String adultEnroll;

    public void setAdultId(Long adultId) 
    {
        this.adultId = adultId;
    }

    public Long getAdultId() 
    {
        return adultId;
    }
    public void setAdultTeacher(String adultTeacher) 
    {
        this.adultTeacher = adultTeacher;
    }

    public String getAdultTeacher() 
    {
        return adultTeacher;
    }
    public void setAdultName(String adultName) 
    {
        this.adultName = adultName;
    }

    public String getAdultName() 
    {
        return adultName;
    }

    public String getAdultSex() {
        return adultSex;
    }

    public void setAdultSex(String adultSex) {
        this.adultSex = adultSex;
    }

    public void setAdultNation(String adultNation)
    {
        this.adultNation = adultNation;
    }

    public String getAdultNation() 
    {
        return adultNation;
    }
    public void setAdultIdnumber(String adultIdnumber) 
    {
        this.adultIdnumber = adultIdnumber;
    }

    public String getAdultIdnumber() 
    {
        return adultIdnumber;
    }
    public void setAdultLevel(String adultLevel) 
    {
        this.adultLevel = adultLevel;
    }

    public String getAdultLevel() 
    {
        return adultLevel;
    }
    public void setAdultType(String adultType) 
    {
        this.adultType = adultType;
    }

    public String getAdultType() 
    {
        return adultType;
    }
    public void setAdultSchool(String adultSchool) 
    {
        this.adultSchool = adultSchool;
    }

    public String getAdultSchool() 
    {
        return adultSchool;
    }
    public void setAdultMajor(String adultMajor) 
    {
        this.adultMajor = adultMajor;
    }

    public String getAdultMajor() 
    {
        return adultMajor;
    }
    public void setAdultGrade(String adultGrade) 
    {
        this.adultGrade = adultGrade;
    }

    public String getAdultGrade() 
    {
        return adultGrade;
    }
    public void setAdultAccount(String adultAccount) 
    {
        this.adultAccount = adultAccount;
    }

    public String getAdultAccount() 
    {
        return adultAccount;
    }
    public void setAdultExamNumber(String adultExamNumber) 
    {
        this.adultExamNumber = adultExamNumber;
    }

    public String getAdultExamNumber() 
    {
        return adultExamNumber;
    }
    public void setAdultStudentNumber(String adultStudentNumber) 
    {
        this.adultStudentNumber = adultStudentNumber;
    }

    public String getAdultStudentNumber() 
    {
        return adultStudentNumber;
    }
    public void setAdultAddScore(Double adultAddScore) 
    {
        this.adultAddScore = adultAddScore;
    }

    public Double getAdultAddScore() 
    {
        return adultAddScore;
    }
    public void setAdultChargeStandard(Double adultChargeStandard) 
    {
        this.adultChargeStandard = adultChargeStandard;
    }

    public Double getAdultChargeStandard() 
    {
        return adultChargeStandard;
    }
    public void setAdultOneyearCharge(Double adultOneyearCharge) 
    {
        this.adultOneyearCharge = adultOneyearCharge;
    }

    public Double getAdultOneyearCharge() 
    {
        return adultOneyearCharge;
    }
    public void setAdultTwoyearCharge(Long adultTwoyearCharge) 
    {
        this.adultTwoyearCharge = adultTwoyearCharge;
    }

    public Long getAdultTwoyearCharge() 
    {
        return adultTwoyearCharge;
    }
    public void setAdultChargeDetail(String adultChargeDetail) 
    {
        this.adultChargeDetail = adultChargeDetail;
    }

    public String getAdultChargeDetail() 
    {
        return adultChargeDetail;
    }
    public void setAdultEnroll(String adultEnroll) 
    {
        this.adultEnroll = adultEnroll;
    }

    public String getAdultEnroll() 
    {
        return adultEnroll;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adultId", getAdultId())
            .append("adultTeacher", getAdultTeacher())
            .append("adultName", getAdultName())
            .append("adultSex", getAdultSex())
            .append("adultNation", getAdultNation())
            .append("adultIdnumber", getAdultIdnumber())
            .append("adultLevel", getAdultLevel())
            .append("adultType", getAdultType())
            .append("adultSchool", getAdultSchool())
            .append("adultMajor", getAdultMajor())
            .append("adultGrade", getAdultGrade())
            .append("adultAccount", getAdultAccount())
            .append("adultExamNumber", getAdultExamNumber())
            .append("adultStudentNumber", getAdultStudentNumber())
            .append("adultAddScore", getAdultAddScore())
            .append("adultChargeStandard", getAdultChargeStandard())
            .append("adultOneyearCharge", getAdultOneyearCharge())
            .append("adultTwoyearCharge", getAdultTwoyearCharge())
            .append("adultChargeDetail", getAdultChargeDetail())
            .append("adultEnroll", getAdultEnroll())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
