package com.ruoyi.bussiness.service;

import com.ruoyi.bussiness.domain.DistanceEducation;
import java.util.List;

/**
 * 远程教育Service接口
 * 
 * @author liukang
 * @date 2019-08-30
 */
public interface IDistanceEducationService 
{
    /**
     * 查询远程教育
     * 
     * @param distanceId 远程教育ID
     * @return 远程教育
     */
    public DistanceEducation selectDistanceEducationById(Long distanceId);

    /**
     * 查询远程教育列表
     * 
     * @param distanceEducation 远程教育
     * @return 远程教育集合
     */
    public List<DistanceEducation> selectDistanceEducationList(DistanceEducation distanceEducation);

    /**
     * 新增远程教育
     * 
     * @param distanceEducation 远程教育
     * @return 结果
     */
    public int insertDistanceEducation(DistanceEducation distanceEducation);

    /**
     * 修改远程教育
     * 
     * @param distanceEducation 远程教育
     * @return 结果
     */
    public int updateDistanceEducation(DistanceEducation distanceEducation);

    /**
     * 批量删除远程教育
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistanceEducationByIds(String ids);

    /**
     * 删除远程教育信息
     * 
     * @param distanceId 远程教育ID
     * @return 结果
     */
    public int deleteDistanceEducationById(Long distanceId);

    /**
     * 导入远程教育数据
     *
     * @param distanceEducationList 远程教育数据列表
     * @return 结果
     */
    public String importDistance(List<DistanceEducation> distanceEducationList);
}
