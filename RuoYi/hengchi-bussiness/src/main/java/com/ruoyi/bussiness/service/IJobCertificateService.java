package com.ruoyi.bussiness.service;

import com.ruoyi.bussiness.domain.JobCertificate;
import java.util.List;

/**
 * 职业资格证Service接口
 * 
 * @author liukang
 * @date 2019-08-30
 */
public interface IJobCertificateService 
{
    /**
     * 查询职业资格证
     * 
     * @param jobId 职业资格证ID
     * @return 职业资格证
     */
    public JobCertificate selectJobCertificateById(Long jobId);

    /**
     * 查询职业资格证列表
     * 
     * @param jobCertificate 职业资格证
     * @return 职业资格证集合
     */
    public List<JobCertificate> selectJobCertificateList(JobCertificate jobCertificate);

    /**
     * 新增职业资格证
     * 
     * @param jobCertificate 职业资格证
     * @return 结果
     */
    public int insertJobCertificate(JobCertificate jobCertificate);

    /**
     * 修改职业资格证
     * 
     * @param jobCertificate 职业资格证
     * @return 结果
     */
    public int updateJobCertificate(JobCertificate jobCertificate);

    /**
     * 批量删除职业资格证
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobCertificateByIds(String ids);

    /**
     * 删除职业资格证信息
     * 
     * @param jobId 职业资格证ID
     * @return 结果
     */
    public int deleteJobCertificateById(Long jobId);

    /**
     * 导入职业资格证数据列表
     *
     * @param jobCertificateList 职业资格证数据列表
     * @return 结果
     */
    public String importCertificate(List<JobCertificate> jobCertificateList);
}
