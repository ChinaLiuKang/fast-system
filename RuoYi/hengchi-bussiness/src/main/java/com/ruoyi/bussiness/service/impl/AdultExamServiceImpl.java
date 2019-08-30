package com.ruoyi.bussiness.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bussiness.mapper.AdultExamMapper;
import com.ruoyi.bussiness.domain.AdultExam;
import com.ruoyi.bussiness.service.IAdultExamService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成考信息Service业务层处理
 *
 * @author liukang
 * @date 2019-08-30
 */
@Service
@Slf4j
public class AdultExamServiceImpl implements IAdultExamService {
    @Autowired
    private AdultExamMapper adultExamMapper;

    /**
     * 查询成考信息
     *
     * @param adultId 成考信息ID
     * @return 成考信息
     */
    @Override
    public AdultExam selectAdultExamById(Long adultId) {
        return adultExamMapper.selectAdultExamById(adultId);
    }

    /**
     * 查询成考信息列表
     *
     * @param adultExam 成考信息
     * @return 成考信息
     */
    @Override
    public List<AdultExam> selectAdultExamList(AdultExam adultExam) {
        return adultExamMapper.selectAdultExamList(adultExam);
    }

    /**
     * 新增成考信息
     *
     * @param adultExam 成考信息
     * @return 结果
     */
    @Override
    public int insertAdultExam(AdultExam adultExam) {
        adultExam.setCreateTime(DateUtils.getNowDate());
        return adultExamMapper.insertAdultExam(adultExam);
    }

    /**
     * 修改成考信息
     *
     * @param adultExam 成考信息
     * @return 结果
     */
    @Override
    public int updateAdultExam(AdultExam adultExam) {
        adultExam.setUpdateTime(DateUtils.getNowDate());
        return adultExamMapper.updateAdultExam(adultExam);
    }

    /**
     * 删除成考信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAdultExamByIds(String ids) {
        return adultExamMapper.deleteAdultExamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成考信息信息
     *
     * @param adultId 成考信息ID
     * @return 结果
     */
    public int deleteAdultExamById(Long adultId) {
        return adultExamMapper.deleteAdultExamById(adultId);
    }

    @Override
    public String importAdultExam(List<AdultExam> adultExamList) {
        if (StringUtils.isNull(adultExamList) || adultExamList.size() == 0) {
            throw new BusinessException("导入成考信息数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (AdultExam adultExam : adultExamList) {
            try {
                this.insertAdultExam(adultExam);
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
