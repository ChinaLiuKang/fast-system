package com.ruoyi.bussiness.service;

import com.ruoyi.bussiness.domain.SelfExam;
import java.util.List;

/**
 * 自学考试Service接口
 * 
 * @author liukang
 * @date 2019-08-30
 */
public interface ISelfExamService 
{
    /**
     * 查询自学考试
     * 
     * @param selfId 自学考试ID
     * @return 自学考试
     */
    public SelfExam selectSelfExamById(Long selfId);

    /**
     * 查询自学考试列表
     * 
     * @param selfExam 自学考试
     * @return 自学考试集合
     */
    public List<SelfExam> selectSelfExamList(SelfExam selfExam);

    /**
     * 新增自学考试
     * 
     * @param selfExam 自学考试
     * @return 结果
     */
    public int insertSelfExam(SelfExam selfExam);

    /**
     * 修改自学考试
     * 
     * @param selfExam 自学考试
     * @return 结果
     */
    public int updateSelfExam(SelfExam selfExam);

    /**
     * 批量删除自学考试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSelfExamByIds(String ids);

    /**
     * 删除自学考试信息
     * 
     * @param selfId 自学考试ID
     * @return 结果
     */
    public int deleteSelfExamById(Long selfId);

    /**
     * 导入自学考试数据列表
     *
     * @param selfExamList 自学考试数据列表
     * @return 结果
     */
    public String importSelfExam(List<SelfExam> selfExamList);
}
