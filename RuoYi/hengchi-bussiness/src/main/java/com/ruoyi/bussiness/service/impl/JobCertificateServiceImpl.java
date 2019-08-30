package com.ruoyi.bussiness.service.impl;

import java.util.List;

import com.ruoyi.bussiness.domain.DistanceEducation;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bussiness.mapper.JobCertificateMapper;
import com.ruoyi.bussiness.domain.JobCertificate;
import com.ruoyi.bussiness.service.IJobCertificateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 职业资格证Service业务层处理
 * 
 * @author liukang
 * @date 2019-08-30
 */
@Service
@Slf4j
public class JobCertificateServiceImpl implements IJobCertificateService 
{
    @Autowired
    private JobCertificateMapper jobCertificateMapper;

    /**
     * 查询职业资格证
     * 
     * @param jobId 职业资格证ID
     * @return 职业资格证
     */
    @Override
    public JobCertificate selectJobCertificateById(Long jobId)
    {
        return jobCertificateMapper.selectJobCertificateById(jobId);
    }

    /**
     * 查询职业资格证列表
     * 
     * @param jobCertificate 职业资格证
     * @return 职业资格证
     */
    @Override
    public List<JobCertificate> selectJobCertificateList(JobCertificate jobCertificate)
    {
        return jobCertificateMapper.selectJobCertificateList(jobCertificate);
    }

    /**
     * 新增职业资格证
     * 
     * @param jobCertificate 职业资格证
     * @return 结果
     */
    @Override
    public int insertJobCertificate(JobCertificate jobCertificate)
    {
        jobCertificate.setCreateTime(DateUtils.getNowDate());
        return jobCertificateMapper.insertJobCertificate(jobCertificate);
    }

    /**
     * 修改职业资格证
     * 
     * @param jobCertificate 职业资格证
     * @return 结果
     */
    @Override
    public int updateJobCertificate(JobCertificate jobCertificate)
    {
        jobCertificate.setUpdateTime(DateUtils.getNowDate());
        return jobCertificateMapper.updateJobCertificate(jobCertificate);
    }

    /**
     * 删除职业资格证对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobCertificateByIds(String ids)
    {
        return jobCertificateMapper.deleteJobCertificateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除职业资格证信息
     * 
     * @param jobId 职业资格证ID
     * @return 结果
     */
    public int deleteJobCertificateById(Long jobId)
    {
        return jobCertificateMapper.deleteJobCertificateById(jobId);
    }

    @Override
    public String importCertificate(List<JobCertificate> jobCertificateList) {

        if (StringUtils.isNull(jobCertificateList) || jobCertificateList.size() == 0) {
            throw new BusinessException("导入远程教育数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (JobCertificate jobCertificate : jobCertificateList) {
            try {
                this.insertJobCertificate(jobCertificate);
                successNum++;
                successMsg.append("<br/>" + successNum + "、信息 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、信息 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
