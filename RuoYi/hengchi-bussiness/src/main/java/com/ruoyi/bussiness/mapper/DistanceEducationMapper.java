package com.ruoyi.bussiness.mapper;

import com.ruoyi.bussiness.domain.DistanceEducation;
import java.util.List;

/**
 * 远程教育Mapper接口
 * 
 * @author liukang
 * @date 2019-08-30
 */
public interface DistanceEducationMapper 
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
     * 删除远程教育
     * 
     * @param distanceId 远程教育ID
     * @return 结果
     */
    public int deleteDistanceEducationById(Long distanceId);

    /**
     * 批量删除远程教育
     * 
     * @param distanceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistanceEducationByIds(String[] distanceIds);
}
