package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 自学考试对象 sm_self_exam
 * 
 * @author liukang
 * @date 2019-08-30
 */
public class SelfExam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键编号 */
    private Long selfId;

    /** 招生老师 */
    @Excel(name = "招生老师")
    private String selfTeacher;

    /** 姓名 */
    @Excel(name = "姓名")
    private String selfName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String selfSex;

    /** 民族 */
    @Excel(name = "民族")
    private String selfNation;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String selfIdnumber;

    /** 层次 */
    @Excel(name = "层次")
    private String selfLevel;

    /** 类别 */
    @Excel(name = "类别")
    private String selfType;

    /** 报读学校 */
    @Excel(name = "报读学校")
    private String selfSchool;

    /** 报读专业 */
    @Excel(name = "报读专业")
    private String selfMajor;

    /** 考生号 */
    @Excel(name = "考生号")
    private String selfStudentNumber;

    /** 总费用 */
    @Excel(name = "总费用")
    private Double selfTotalCharge;

    /** 第一年缴费 */
    @Excel(name = "第一年缴费")
    private Double selfOneyearCharge;

    /** 第二年缴费 */
    @Excel(name = "第二年缴费")
    private Long selfTwoyearCharge;

    /** 缴费情况 */
    @Excel(name = "缴费情况")
    private String selfChargeDetail;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSelfId(Long selfId)
    {
        this.selfId = selfId;
    }

    public Long getSelfId() 
    {
        return selfId;
    }
    public void setSelfTeacher(String selfTeacher) 
    {
        this.selfTeacher = selfTeacher;
    }

    public String getSelfTeacher() 
    {
        return selfTeacher;
    }
    public void setSelfName(String selfName) 
    {
        this.selfName = selfName;
    }

    public String getSelfName() {
        return selfName;
    }

    public String getSelfSex() {
        return selfSex;
    }

    public void setSelfSex(String selfSex) {
        this.selfSex = selfSex;
    }

    public void setSelfNation(String selfNation)
    {
        this.selfNation = selfNation;
    }

    public String getSelfNation() 
    {
        return selfNation;
    }
    public void setSelfIdnumber(String selfIdnumber) 
    {
        this.selfIdnumber = selfIdnumber;
    }

    public String getSelfIdnumber() 
    {
        return selfIdnumber;
    }
    public void setSelfLevel(String selfLevel) 
    {
        this.selfLevel = selfLevel;
    }

    public String getSelfLevel() 
    {
        return selfLevel;
    }
    public void setSelfType(String selfType) 
    {
        this.selfType = selfType;
    }

    public String getSelfType() 
    {
        return selfType;
    }
    public void setSelfSchool(String selfSchool) 
    {
        this.selfSchool = selfSchool;
    }

    public String getSelfSchool() 
    {
        return selfSchool;
    }
    public void setSelfMajor(String selfMajor) 
    {
        this.selfMajor = selfMajor;
    }

    public String getSelfMajor() 
    {
        return selfMajor;
    }
    public void setSelfStudentNumber(String selfStudentNumber) 
    {
        this.selfStudentNumber = selfStudentNumber;
    }

    public String getSelfStudentNumber() 
    {
        return selfStudentNumber;
    }
    public void setSelfTotalCharge(Double selfTotalCharge) 
    {
        this.selfTotalCharge = selfTotalCharge;
    }

    public Double getSelfTotalCharge() 
    {
        return selfTotalCharge;
    }
    public void setSelfOneyearCharge(Double selfOneyearCharge) 
    {
        this.selfOneyearCharge = selfOneyearCharge;
    }

    public Double getSelfOneyearCharge() 
    {
        return selfOneyearCharge;
    }
    public void setSelfTwoyearCharge(Long selfTwoyearCharge) 
    {
        this.selfTwoyearCharge = selfTwoyearCharge;
    }

    public Long getSelfTwoyearCharge() 
    {
        return selfTwoyearCharge;
    }
    public void setSelfChargeDetail(String selfChargeDetail) 
    {
        this.selfChargeDetail = selfChargeDetail;
    }

    public String getSelfChargeDetail() 
    {
        return selfChargeDetail;
    }

    @Override
    public String toString() {
        return "SelfExam{" +
                "selfId=" + selfId +
                ", selfTeacher='" + selfTeacher + '\'' +
                ", selfName='" + selfName + '\'' +
                ", selfSex='" + selfSex + '\'' +
                ", selfNation='" + selfNation + '\'' +
                ", selfIdnumber='" + selfIdnumber + '\'' +
                ", selfLevel='" + selfLevel + '\'' +
                ", selfType='" + selfType + '\'' +
                ", selfSchool='" + selfSchool + '\'' +
                ", selfMajor='" + selfMajor + '\'' +
                ", selfStudentNumber='" + selfStudentNumber + '\'' +
                ", selfTotalCharge=" + selfTotalCharge +
                ", selfOneyearCharge=" + selfOneyearCharge +
                ", selfTwoyearCharge=" + selfTwoyearCharge +
                ", selfChargeDetail='" + selfChargeDetail + '\'' +
                ", userId=" + userId +
                '}';
    }
}
