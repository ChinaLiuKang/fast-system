package com.ruoyi.bussiness.service.impl;

import java.util.List;

import com.ruoyi.bussiness.domain.JobCertificate;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bussiness.mapper.SelfExamMapper;
import com.ruoyi.bussiness.domain.SelfExam;
import com.ruoyi.bussiness.service.ISelfExamService;
import com.ruoyi.common.core.text.Convert;

/**
 * 自学考试Service业务层处理
 * 
 * @author liukang
 * @date 2019-08-30
 */
@Service
@Slf4j
public class SelfExamServiceImpl implements ISelfExamService 
{
    @Autowired
    private SelfExamMapper selfExamMapper;

    /**
     * 查询自学考试
     * 
     * @param selfId 自学考试ID
     * @return 自学考试
     */
    @Override
    public SelfExam selectSelfExamById(Long selfId)
    {
        return selfExamMapper.selectSelfExamById(selfId);
    }

    /**
     * 查询自学考试列表
     * 
     * @param selfExam 自学考试
     * @return 自学考试
     */
    @Override
    public List<SelfExam> selectSelfExamList(SelfExam selfExam)
    {
        return selfExamMapper.selectSelfExamList(selfExam);
    }

    /**
     * 新增自学考试
     * 
     * @param selfExam 自学考试
     * @return 结果
     */
    @Override
    public int insertSelfExam(SelfExam selfExam)
    {
        selfExam.setCreateTime(DateUtils.getNowDate());
        return selfExamMapper.insertSelfExam(selfExam);
    }

    /**
     * 修改自学考试
     * 
     * @param selfExam 自学考试
     * @return 结果
     */
    @Override
    public int updateSelfExam(SelfExam selfExam)
    {
        selfExam.setUpdateTime(DateUtils.getNowDate());
        return selfExamMapper.updateSelfExam(selfExam);
    }

    /**
     * 删除自学考试对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSelfExamByIds(String ids)
    {
        return selfExamMapper.deleteSelfExamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除自学考试信息
     * 
     * @param selfId 自学考试ID
     * @return 结果
     */
    public int deleteSelfExamById(Long selfId)
    {
        return selfExamMapper.deleteSelfExamById(selfId);
    }

    @Override
    public String importSelfExam(List<SelfExam> selfExamList) {
        if (StringUtils.isNull(selfExamList) || selfExamList.size() == 0) {
            throw new BusinessException("导入远程教育数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SelfExam selfExam : selfExamList) {
            try {
                this.insertSelfExam(selfExam);
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
