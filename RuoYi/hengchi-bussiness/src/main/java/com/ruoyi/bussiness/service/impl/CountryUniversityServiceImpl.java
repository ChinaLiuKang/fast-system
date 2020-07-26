package com.ruoyi.bussiness.service.impl;

import java.util.List;

import com.ruoyi.bussiness.domain.AdultExam;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bussiness.mapper.CountryUniversityMapper;
import com.ruoyi.bussiness.domain.CountryUniversity;
import com.ruoyi.bussiness.service.ICountryUniversityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 国家开放大学Service业务层处理
 * 
 * @author liukang
 * @date 2020-07-26
 */
@Service
@Slf4j
public class CountryUniversityServiceImpl implements ICountryUniversityService 
{
    @Autowired
    private CountryUniversityMapper countryUniversityMapper;

    /**
     * 查询国家开放大学
     * 
     * @param id 国家开放大学ID
     * @return 国家开放大学
     */
    @Override
    public CountryUniversity selectCountryUniversityById(Long id)
    {
        return countryUniversityMapper.selectCountryUniversityById(id);
    }

    /**
     * 查询国家开放大学列表
     * 
     * @param countryUniversity 国家开放大学
     * @return 国家开放大学
     */
    @Override
    public List<CountryUniversity> selectCountryUniversityList(CountryUniversity countryUniversity)
    {
        return countryUniversityMapper.selectCountryUniversityList(countryUniversity);
    }

    /**
     * 新增国家开放大学
     * 
     * @param countryUniversity 国家开放大学
     * @return 结果
     */
    @Override
    public int insertCountryUniversity(CountryUniversity countryUniversity)
    {
        countryUniversity.setCreateTime(DateUtils.getNowDate());
        return countryUniversityMapper.insertCountryUniversity(countryUniversity);
    }

    /**
     * 修改国家开放大学
     * 
     * @param countryUniversity 国家开放大学
     * @return 结果
     */
    @Override
    public int updateCountryUniversity(CountryUniversity countryUniversity)
    {
        countryUniversity.setUpdateTime(DateUtils.getNowDate());
        return countryUniversityMapper.updateCountryUniversity(countryUniversity);
    }

    /**
     * 删除国家开放大学对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCountryUniversityByIds(String ids)
    {
        return countryUniversityMapper.deleteCountryUniversityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除国家开放大学信息
     * 
     * @param id 国家开放大学ID
     * @return 结果
     */
    public int deleteCountryUniversityById(Long id)
    {
        return countryUniversityMapper.deleteCountryUniversityById(id);
    }

    @Override
    public String importAdultExam(List<CountryUniversity> countryUniversityList) {
        if (StringUtils.isNull(countryUniversityList) || countryUniversityList.size() == 0) {
            throw new BusinessException("导入国家开放大学数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (CountryUniversity countryUniversity : countryUniversityList) {
            try {
                this.insertCountryUniversity(countryUniversity);
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
