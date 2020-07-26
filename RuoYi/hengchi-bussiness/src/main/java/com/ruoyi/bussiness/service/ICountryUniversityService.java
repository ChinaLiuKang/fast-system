package com.ruoyi.bussiness.service;

import com.ruoyi.bussiness.domain.AdultExam;
import com.ruoyi.bussiness.domain.CountryUniversity;
import java.util.List;

/**
 * 国家开放大学Service接口
 * 
 * @author liukang
 * @date 2020-07-26
 */
public interface ICountryUniversityService 
{
    /**
     * 查询国家开放大学
     * 
     * @param id 国家开放大学ID
     * @return 国家开放大学
     */
    public CountryUniversity selectCountryUniversityById(Long id);

    /**
     * 查询国家开放大学列表
     * 
     * @param countryUniversity 国家开放大学
     * @return 国家开放大学集合
     */
    public List<CountryUniversity> selectCountryUniversityList(CountryUniversity countryUniversity);

    /**
     * 新增国家开放大学
     * 
     * @param countryUniversity 国家开放大学
     * @return 结果
     */
    public int insertCountryUniversity(CountryUniversity countryUniversity);

    /**
     * 修改国家开放大学
     * 
     * @param countryUniversity 国家开放大学
     * @return 结果
     */
    public int updateCountryUniversity(CountryUniversity countryUniversity);

    /**
     * 批量删除国家开放大学
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCountryUniversityByIds(String ids);

    /**
     * 删除国家开放大学信息
     * 
     * @param id 国家开放大学ID
     * @return 结果
     */
    public int deleteCountryUniversityById(Long id);


    /**
     * 导入国家开放大学数据
     *
     * @param countryUniversityList 数据列表
     * @return 结果
     */
    public String importAdultExam(List<CountryUniversity> countryUniversityList);
}
