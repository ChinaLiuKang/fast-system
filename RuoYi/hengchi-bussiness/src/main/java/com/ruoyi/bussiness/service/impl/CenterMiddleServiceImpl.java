package com.ruoyi.bussiness.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bussiness.mapper.CenterMiddleMapper;
import com.ruoyi.bussiness.domain.CenterMiddle;
import com.ruoyi.bussiness.service.ICenterMiddleService;
import com.ruoyi.common.core.text.Convert;

/**
 * 中央电中Service业务层处理
 *
 * @author liukang
 * @date 2019-08-30
 */
@Service
@Slf4j
public class CenterMiddleServiceImpl implements ICenterMiddleService {
    @Autowired
    private CenterMiddleMapper centerMiddleMapper;

    /**
     * 查询中央电中
     *
     * @param centerId 中央电中ID
     * @return 中央电中
     */
    @Override
    public CenterMiddle selectCenterMiddleById(Long centerId) {
        return centerMiddleMapper.selectCenterMiddleById(centerId);
    }

    /**
     * 查询中央电中列表
     *
     * @param centerMiddle 中央电中
     * @return 中央电中
     */
    @Override
    public List<CenterMiddle> selectCenterMiddleList(CenterMiddle centerMiddle) {
        return centerMiddleMapper.selectCenterMiddleList(centerMiddle);
    }

    /**
     * 新增中央电中
     *
     * @param centerMiddle 中央电中
     * @return 结果
     */
    @Override
    public int insertCenterMiddle(CenterMiddle centerMiddle) {
        centerMiddle.setCreateTime(DateUtils.getNowDate());
        return centerMiddleMapper.insertCenterMiddle(centerMiddle);
    }

    /**
     * 修改中央电中
     *
     * @param centerMiddle 中央电中
     * @return 结果
     */
    @Override
    public int updateCenterMiddle(CenterMiddle centerMiddle) {
        centerMiddle.setUpdateTime(DateUtils.getNowDate());
        return centerMiddleMapper.updateCenterMiddle(centerMiddle);
    }

    /**
     * 删除中央电中对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCenterMiddleByIds(String ids) {
        return centerMiddleMapper.deleteCenterMiddleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除中央电中信息
     *
     * @param centerId 中央电中ID
     * @return 结果
     */
    public int deleteCenterMiddleById(Long centerId) {
        return centerMiddleMapper.deleteCenterMiddleById(centerId);
    }

    /**
     * 批量导入中央电中数据
     * @param centerMiddleList 中央电中数据列表
     * @return
     */
    @Override
    public String importCenterMiddle(List<CenterMiddle> centerMiddleList) {
        if (StringUtils.isNull(centerMiddleList) || centerMiddleList.size() == 0) {
            throw new BusinessException("导入中央电中信息数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (CenterMiddle centerMiddle : centerMiddleList) {
            try {
                this.insertCenterMiddle(centerMiddle);
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
