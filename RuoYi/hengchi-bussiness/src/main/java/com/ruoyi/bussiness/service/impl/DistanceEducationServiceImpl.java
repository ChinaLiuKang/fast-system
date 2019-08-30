package com.ruoyi.bussiness.service.impl;

import java.util.List;

import com.ruoyi.bussiness.domain.AdultExam;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bussiness.mapper.DistanceEducationMapper;
import com.ruoyi.bussiness.domain.DistanceEducation;
import com.ruoyi.bussiness.service.IDistanceEducationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 远程教育Service业务层处理
 * 
 * @author liukang
 * @date 2019-08-30
 */
@Service
@Slf4j
public class DistanceEducationServiceImpl implements IDistanceEducationService 
{
    @Autowired
    private DistanceEducationMapper distanceEducationMapper;

    /**
     * 查询远程教育
     * 
     * @param distanceId 远程教育ID
     * @return 远程教育
     */
    @Override
    public DistanceEducation selectDistanceEducationById(Long distanceId)
    {
        return distanceEducationMapper.selectDistanceEducationById(distanceId);
    }

    /**
     * 查询远程教育列表
     * 
     * @param distanceEducation 远程教育
     * @return 远程教育
     */
    @Override
    public List<DistanceEducation> selectDistanceEducationList(DistanceEducation distanceEducation)
    {
        return distanceEducationMapper.selectDistanceEducationList(distanceEducation);
    }

    /**
     * 新增远程教育
     * 
     * @param distanceEducation 远程教育
     * @return 结果
     */
    @Override
    public int insertDistanceEducation(DistanceEducation distanceEducation)
    {
        distanceEducation.setCreateTime(DateUtils.getNowDate());
        return distanceEducationMapper.insertDistanceEducation(distanceEducation);
    }

    /**
     * 修改远程教育
     * 
     * @param distanceEducation 远程教育
     * @return 结果
     */
    @Override
    public int updateDistanceEducation(DistanceEducation distanceEducation)
    {
        distanceEducation.setUpdateTime(DateUtils.getNowDate());
        return distanceEducationMapper.updateDistanceEducation(distanceEducation);
    }

    /**
     * 删除远程教育对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDistanceEducationByIds(String ids)
    {
        return distanceEducationMapper.deleteDistanceEducationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除远程教育信息
     * 
     * @param distanceId 远程教育ID
     * @return 结果
     */
    public int deleteDistanceEducationById(Long distanceId)
    {
        return distanceEducationMapper.deleteDistanceEducationById(distanceId);
    }

    @Override
    public String importDistance(List<DistanceEducation> distanceEducationList) {
        if (StringUtils.isNull(distanceEducationList) || distanceEducationList.size() == 0) {
            throw new BusinessException("导入远程教育数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DistanceEducation distanceEducation : distanceEducationList) {
            try {
                this.insertDistanceEducation(distanceEducation);
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
