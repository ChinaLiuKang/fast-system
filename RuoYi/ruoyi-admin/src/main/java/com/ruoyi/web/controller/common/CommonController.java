package com.ruoyi.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bussiness.domain.*;
import com.ruoyi.bussiness.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;

import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    @Autowired
    IAdultExamService adultExamService;

    @Autowired
    private ICenterMiddleService centerMiddleService;

    @Autowired
    private IDistanceEducationService distanceEducationService;

    @Autowired
    private IJobCertificateService jobCertificateService;

    @Autowired
    private ISelfExamService selfExamService;


    @GetMapping("/common/getEduInfo")
    public String getEduInfo(String idCard, String userName, Integer eduType, ModelMap mmap) {
        if (StringUtils.isEmpty(idCard) || StringUtils.isEmpty(userName) || eduType == null || eduType.toString().equals("")) {
            mmap.put("isEmpty", 0);
            mmap.put("eduType","");
        } else {
            mmap.put("timestamp",System.currentTimeMillis());
            mmap.put("userName", userName);
            mmap.put("idCard", idCard);
            mmap.put("eduType",eduType);
            //成人高考
            if (eduType == 1) {
                AdultExam adultExam = new AdultExam();
                adultExam.setAdultName(userName);
                adultExam.setAdultIdnumber(idCard);
                List<AdultExam> adultExams = adultExamService.selectAdultExamList(adultExam);
                if(adultExams.size()>0){
                    mmap.put("sex", adultExams.get(0).getAdultSex());
                    mmap.put("school", adultExams.get(0).getAdultSchool());
                    mmap.put("major", adultExams.get(0).getAdultMajor());
                    mmap.put("level", adultExams.get(0).getAdultLevel());
                    //学号
                    mmap.put("studentNumber", adultExams.get(0).getAdultAccount());
                    mmap.put("examStudentNumber", adultExams.get(0).getAdultStudentNumber());
                }else{
                    mmap.put("isEmpty", 0);
                }
            }
            //远程教育
            if (eduType == 2) {
                DistanceEducation distanceEducation = new DistanceEducation();
                distanceEducation.setDistanceName(userName);
                distanceEducation.setDistanceIdnumber(idCard);
                List<DistanceEducation> distanceEducations = distanceEducationService.selectDistanceEducationList(distanceEducation);
                if(distanceEducations.size()>0){
                    mmap.put("sex", distanceEducations.get(0).getDistanceSex());
                    mmap.put("school", distanceEducations.get(0).getDistanceSchool());
                    mmap.put("major", distanceEducations.get(0).getDistanceMajor());
                    mmap.put("level", distanceEducations.get(0).getDistanceLevel());
                    mmap.put("studentNumber", distanceEducations.get(0).getDistanceStudentNumber());
                }else{
                    mmap.put("isEmpty", 0);
                }
            }
            //自学考试
            if (eduType == 3) {
                SelfExam selfExam = new SelfExam();
                selfExam.setSelfName(userName);
                selfExam.setSelfIdnumber(idCard);
                List<SelfExam> selfExams = selfExamService.selectSelfExamList(selfExam);
                if(selfExams.size()>0){
                    mmap.put("sex", selfExams.get(0).getSelfSex());
                    mmap.put("school", selfExams.get(0).getSelfSchool());
                    mmap.put("major", selfExams.get(0).getSelfMajor());
                    mmap.put("level", selfExams.get(0).getSelfLevel());
                    //考生号
                    mmap.put("examStudentNumber", selfExams.get(0).getSelfStudentNumber());
                }else{
                    mmap.put("isEmpty", 0);
                }
            }
            //职业资格证
            if (eduType == 4) {
                JobCertificate jobCertificate = new JobCertificate();
                jobCertificate.setJobName(userName);
                jobCertificate.setJobIdnumber(idCard);
                List<JobCertificate> jobCertificates = jobCertificateService.selectJobCertificateList(jobCertificate);
                if(jobCertificates.size()>0){
                    mmap.put("sex", jobCertificates.get(0).getJobSex());
                    mmap.put("typeWork", jobCertificates.get(0).getJobTypework());
                }else{
                    mmap.put("isEmpty", 0);
                }
            }
            //中央电中
            if (eduType == 5) {
                CenterMiddle centerMiddle = new CenterMiddle();
                centerMiddle.setCenterName(userName);
                centerMiddle.setCenterIdnumber(idCard);
                List<CenterMiddle> centerMiddles = centerMiddleService.selectCenterMiddleList(centerMiddle);
                if(centerMiddles.size()>0){
                    mmap.put("sex", centerMiddles.get(0).getCenterSex());
                    mmap.put("school", centerMiddles.get(0).getCenterSchool());
                    mmap.put("major", centerMiddles.get(0).getCenterMajor());
                    mmap.put("level", centerMiddles.get(0).getCenterLevel());
                }else{
                    mmap.put("isEmpty", 0);
                }
            }
        }
        return "bussiness/common" + "/getEduInfo";
    }
}
