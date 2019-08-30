package com.ruoyi.bussiness.mapper;

import com.ruoyi.bussiness.domain.AdultExam;
import java.util.List;

/**
 * 成考信息Mapper接口
 * 
 * @author liukang
 * @date 2019-08-30
 */
public interface AdultExamMapper 
{
    /**
     * 查询成考信息
     * 
     * @param adultId 成考信息ID
     * @return 成考信息
     */
    public AdultExam selectAdultExamById(Long adultId);

    /**
     * 查询成考信息列表
     * 
     * @param adultExam 成考信息
     * @return 成考信息集合
     */
    public List<AdultExam> selectAdultExamList(AdultExam adultExam);

    /**
     * 新增成考信息
     * 
     * @param adultExam 成考信息
     * @return 结果
     */
    public int insertAdultExam(AdultExam adultExam);

    /**
     * 修改成考信息
     * 
     * @param adultExam 成考信息
     * @return 结果
     */
    public int updateAdultExam(AdultExam adultExam);

    /**
     * 删除成考信息
     * 
     * @param adultId 成考信息ID
     * @return 结果
     */
    public int deleteAdultExamById(Long adultId);

    /**
     * 批量删除成考信息
     * 
     * @param adultIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdultExamByIds(String[] adultIds);
}
