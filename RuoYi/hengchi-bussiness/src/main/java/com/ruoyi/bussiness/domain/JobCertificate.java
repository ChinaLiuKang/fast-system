package com.ruoyi.bussiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 职业资格证对象 sm_job_certificate
 * 
 * @author liukang
 * @date 2019-08-30
 */
public class JobCertificate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键编号 */
    private Long jobId;

    @Excel(name= "操作人")
    String userName;

    /** 招生老师 */
    @Excel(name = "招生老师")
    private String jobTeacher;

    /** 姓名 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String jobName;

    /** 性别 */
    @Excel(name = "性别")
    private String jobSex;

    /** 民族 */
    @Excel(name = "民族")
    private String jobNation;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String jobIdnumber;

    /** 出证部门 */
    @Excel(name = "出证部门")
    private String jobOutdept;

    /** 工种 */
    @Excel(name = "工种")
    private String jobTypework;

    /** 上报时间 */
    @Excel(name = "上报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date jobIntime;

    /** 总费用 */
    @Excel(name = "总费用")
    private Double jobTotalCharge;

    /** 缴费情况 */
    @Excel(name = "缴费情况")
    private String jobChargeDetail;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    private Long userId;

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

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }
    public void setJobTeacher(String jobTeacher) 
    {
        this.jobTeacher = jobTeacher;
    }

    public String getJobTeacher() 
    {
        return jobTeacher;
    }
    public void setJobName(String jobName) 
    {
        this.jobName = jobName;
    }

    public String getJobName() 
    {
        return jobName;
    }

    public String getJobSex() {
        return jobSex;
    }

    public void setJobSex(String jobSex) {
        this.jobSex = jobSex;
    }

    public void setJobNation(String jobNation)
    {
        this.jobNation = jobNation;
    }

    public String getJobNation() 
    {
        return jobNation;
    }
    public void setJobIdnumber(String jobIdnumber) 
    {
        this.jobIdnumber = jobIdnumber;
    }

    public String getJobIdnumber() 
    {
        return jobIdnumber;
    }
    public void setJobOutdept(String jobOutdept) 
    {
        this.jobOutdept = jobOutdept;
    }

    public String getJobOutdept() 
    {
        return jobOutdept;
    }
    public void setJobTypework(String jobTypework) 
    {
        this.jobTypework = jobTypework;
    }

    public String getJobTypework() 
    {
        return jobTypework;
    }
    public void setJobIntime(Date jobIntime) 
    {
        this.jobIntime = jobIntime;
    }

    public Date getJobIntime() 
    {
        return jobIntime;
    }
    public void setJobTotalCharge(Double jobTotalCharge) 
    {
        this.jobTotalCharge = jobTotalCharge;
    }

    public Double getJobTotalCharge() 
    {
        return jobTotalCharge;
    }
    public void setJobChargeDetail(String jobChargeDetail) 
    {
        this.jobChargeDetail = jobChargeDetail;
    }

    public String getJobChargeDetail() 
    {
        return jobChargeDetail;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JobCertificate{" +
                "jobId=" + jobId +
                ", jobTeacher='" + jobTeacher + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobSex='" + jobSex + '\'' +
                ", jobNation='" + jobNation + '\'' +
                ", jobIdnumber='" + jobIdnumber + '\'' +
                ", jobOutdept='" + jobOutdept + '\'' +
                ", jobTypework='" + jobTypework + '\'' +
                ", jobIntime=" + jobIntime +
                ", jobTotalCharge=" + jobTotalCharge +
                ", jobChargeDetail='" + jobChargeDetail + '\'' +
                ", remark='" + remark + '\'' +
                ", userId=" + userId +
                '}';
    }
}
