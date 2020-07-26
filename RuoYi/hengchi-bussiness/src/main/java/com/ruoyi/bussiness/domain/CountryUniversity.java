package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国家开放大学对象 sm_country_university
 * 
 * @author liukang
 * @date 2020-07-26
 */
public class CountryUniversity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户编号 */
    private Long userId;

    /** 操作人 */
    @Excel(name = "操作人")
    private String userName;

    /** 合作站 */
    @Excel(name = "合作站")
    private String collaborationStation;

    /** 合作费用 */
    @Excel(name = "合作费用")
    private Double collaborationCost;

    /** 招生老师 */
    @Excel(name = "招生老师")
    private String teacher;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idnumber;

    /** 层次 */
    @Excel(name = "层次")
    private String level;

    /** 报读学校 */
    @Excel(name = "报读学校")
    private String school;

    /** 报读专业 */
    @Excel(name = "报读专业")
    private String major;

    /** 批次 */
    @Excel(name = "批次")
    private String batch;

    /** 总费用 */
    @Excel(name = "总费用")
    private Double totalCharge;

    /** 第一年费用 */
    @Excel(name = "第一年费用")
    private Double oneyearCharge;

    /** 第二年费用 */
    @Excel(name = "第二年费用")
    private Double twoyearCharge;

    /** 缴费情况 */
    @Excel(name = "缴费情况")
    private String chargeDetail;

    /** 毕业情况 */
    @Excel(name = "毕业情况")
    private String graduateDetail;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setCollaborationStation(String collaborationStation) 
    {
        this.collaborationStation = collaborationStation;
    }

    public String getCollaborationStation() 
    {
        return collaborationStation;
    }
    public void setCollaborationCost(Double collaborationCost) 
    {
        this.collaborationCost = collaborationCost;
    }

    public Double getCollaborationCost() 
    {
        return collaborationCost;
    }
    public void setTeacher(String teacher) 
    {
        this.teacher = teacher;
    }

    public String getTeacher() 
    {
        return teacher;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setIdnumber(String idnumber) 
    {
        this.idnumber = idnumber;
    }

    public String getIdnumber() 
    {
        return idnumber;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setBatch(String batch) 
    {
        this.batch = batch;
    }

    public String getBatch() 
    {
        return batch;
    }
    public void setTotalCharge(Double totalCharge) 
    {
        this.totalCharge = totalCharge;
    }

    public Double getTotalCharge() 
    {
        return totalCharge;
    }
    public void setOneyearCharge(Double oneyearCharge) 
    {
        this.oneyearCharge = oneyearCharge;
    }

    public Double getOneyearCharge() 
    {
        return oneyearCharge;
    }
    public void setTwoyearCharge(Double twoyearCharge) 
    {
        this.twoyearCharge = twoyearCharge;
    }

    public Double getTwoyearCharge() 
    {
        return twoyearCharge;
    }
    public void setChargeDetail(String chargeDetail) 
    {
        this.chargeDetail = chargeDetail;
    }

    public String getChargeDetail() 
    {
        return chargeDetail;
    }
    public void setGraduateDetail(String graduateDetail) 
    {
        this.graduateDetail = graduateDetail;
    }

    public String getGraduateDetail() 
    {
        return graduateDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("collaborationStation", getCollaborationStation())
            .append("collaborationCost", getCollaborationCost())
            .append("teacher", getTeacher())
            .append("name", getName())
            .append("sex", getSex())
            .append("nation", getNation())
            .append("idnumber", getIdnumber())
            .append("level", getLevel())
            .append("school", getSchool())
            .append("major", getMajor())
            .append("batch", getBatch())
            .append("totalCharge", getTotalCharge())
            .append("oneyearCharge", getOneyearCharge())
            .append("twoyearCharge", getTwoyearCharge())
            .append("chargeDetail", getChargeDetail())
            .append("graduateDetail", getGraduateDetail())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
