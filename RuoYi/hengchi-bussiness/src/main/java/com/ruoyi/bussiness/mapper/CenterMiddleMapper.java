package com.ruoyi.bussiness.mapper;

import com.ruoyi.bussiness.domain.CenterMiddle;
import java.util.List;

/**
 * 中央电中Mapper接口
 * 
 * @author liukang
 * @date 2019-08-30
 */
public interface CenterMiddleMapper 
{
    /**
     * 查询中央电中
     * 
     * @param centerId 中央电中ID
     * @return 中央电中
     */
    public CenterMiddle selectCenterMiddleById(Long centerId);

    /**
     * 查询中央电中列表
     * 
     * @param centerMiddle 中央电中
     * @return 中央电中集合
     */
    public List<CenterMiddle> selectCenterMiddleList(CenterMiddle centerMiddle);

    /**
     * 新增中央电中
     * 
     * @param centerMiddle 中央电中
     * @return 结果
     */
    public int insertCenterMiddle(CenterMiddle centerMiddle);

    /**
     * 修改中央电中
     * 
     * @param centerMiddle 中央电中
     * @return 结果
     */
    public int updateCenterMiddle(CenterMiddle centerMiddle);

    /**
     * 删除中央电中
     * 
     * @param centerId 中央电中ID
     * @return 结果
     */
    public int deleteCenterMiddleById(Long centerId);

    /**
     * 批量删除中央电中
     * 
     * @param centerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCenterMiddleByIds(String[] centerIds);
}
