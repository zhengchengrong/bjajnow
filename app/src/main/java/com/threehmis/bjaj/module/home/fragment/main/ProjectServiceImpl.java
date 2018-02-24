/*
package com.threehmis.bjaj.module.home.fragment.main;

import com.hhh.modules.basedata.dao.IBaseDataDao;
import com.hhh.modules.project.dao.IProjectDao;
import com.hhh.modules.project.service.IProjectService;
import com.hhh.modules.projectdangerous.dao.IProjectDangerousDao;
import com.hhh.supervise.entity.*;
import com.hhh.supervise.model.ProjectListUnitVO;
import com.hhh.supervise.model.ProjectVO;
import com.hhh.supervise.model.TemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author Administrator
 *//*

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements IProjectService {
    private final HttpServletRequest request;

    private final IProjectDao projectDao;
    private final IProjectDangerousDao dangerousDao;
    private final IBaseDataDao baseDataDao;

    @Autowired
    public ProjectServiceImpl(HttpServletRequest request, IProjectDao projectDao, IProjectDangerousDao dangerousDao, IBaseDataDao baseDataDao) {
        this.request = request;

        this.projectDao = projectDao;
        this.dangerousDao = dangerousDao;
        this.baseDataDao = baseDataDao;
    }

    @Override
    public TemplateVO getProjectByProjectId(String projectId) {
        TemplateVO templateVO = new TemplateVO();

        Project project = projectDao.findByProjectId(projectId);
        if (project == null) {
            templateVO.setVerification(false);
            templateVO.setResult("未找到相应工程");

            return templateVO;
        }

        List<ProjectVO> projectVOList = new ArrayList<>();
        ProjectVO projectVO = new ProjectVO();

        projectVO.setProjectId(project.getProjectId());
        projectVO.setProjectName(project.getProjectName());
        projectVO.setProjectStatus(project.getProjectStatus());
        projectVO.setRegisterDate(String.valueOf(project.getRegisterDate()));

        projectVOList.add(projectVO);
        templateVO.setProjectList(projectVOList);
        templateVO.setVerification(true);
        templateVO.setResult("查询工程形象进度信息成功");

        return templateVO;
    }

    @Override
    public TemplateVO getProjectProfileByProjectId(String projectId) {
        TemplateVO templateVO = new TemplateVO();

        Project project = projectDao.findByProjectId(projectId);

        if (project == null) {
            templateVO.setVerification(false);
            templateVO.setResult("查询工程信息失败");

            return templateVO;
        }

        ProjectVO projectVO = new ProjectVO(project);

        List<ProjectListUnitVO> unitVOList = new ArrayList<>();
        if (project.getUnitSet().size() != 0) {
            for (ProjectListUnit unit : project.getUnitSet()) {
                ProjectListUnitVO unitVO = new ProjectListUnitVO();

                ProjectListPerson person = new ProjectListPerson();
                switch (unit.getUnitTypeNote()) {
                    case "建设单位":
                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目负责人".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    case "施工总承包单位":
                        unitVO.setSafeProduceNum(unit.getSafeProducNum());

                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目经理".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    case "监理单位":
                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目安全监理工程师".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    case "勘查单位":
                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目负责人".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    case "设计单位":
                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目负责人".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    case "劳务分包单位":
                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目负责人".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    case "专利分包单位":
                        for (ProjectListPerson p : unit.getPersonSet()) {
                            if ("项目负责人".equals(person.getDuty())) {
                                person = p;
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }

                // TODO 将责任主体信息装入unitVO
                unitVO.setUnitType(unit.getUnitType());
                unitVO.setUnitName(unit.getUnitName());
                unitVO.setGradeNo(unit.getGradeNo());
                unitVO.setGrade(unit.getGrade());

                unitVO.setPersonName(person.getPersonname());
                unitVO.setDuty(person.getDuty());
                unitVO.setTitle(person.getTitle());
                unitVO.setRegisQualification(person.getRegisQualification());
                unitVO.setTitleNo(person.getTitleNo());
                unitVO.setTel(person.getTel());

                unitVOList.add(unitVO);
            }
        }

        List<ProjectDangerous> dangerousList = dangerousDao.findAllByProjectId(projectId);
        List<String> dangerousVOList = new ArrayList<>();
        if (dangerousList.size() != 0) {
            for (ProjectDangerous dangerous : dangerousList) {
                BaseData baseData = baseDataDao.findByScode(dangerous.getScode());

                dangerousVOList.add(baseData.getVcname());
            }
        }

        List<Object> projectProfile = new ArrayList<>();
        projectProfile.add(projectVO);
        projectProfile.add(unitVOList);
        projectProfile.add(dangerousVOList);

        templateVO.setProjectList(projectProfile);
        templateVO.setVerification(true);
        templateVO.setResult("查询工程信息成功");

        return templateVO;
    }

    @Override
    public TemplateVO findAllByKeyword(String keyword, String personId) {
        List<Project> projectList = projectDao.findByProjectNameLikeOrProjectNumLike(keyword, keyword, personId);

        TemplateVO templateVO = new TemplateVO();
        if (projectList == null || projectList.size() == 0) {
            templateVO.setVerification(false);
            templateVO.setResult("没有找到相应工程");

            return templateVO;
        }

        List<ProjectVO> projectVOList = new ArrayList<>();
        for (Project project : projectList) {
            ProjectVO projectVO = new ProjectVO();

            projectVO.setProjectId(project.getProjectId());
            projectVO.setProjectCode(project.getProjectCode());
            projectVO.setProjectGpsX(project.getProjectGpsX());
            projectVO.setProjectGpsY(project.getProjectGpsY());
            projectVO.setProjectName(project.getProjectName());
            projectVO.setSgxkzh(project.getSgxkzh());
            projectVO.setRegion(project.getRegion());

            projectVOList.add(projectVO);
        }

        templateVO.setProjectList(projectVOList);
        templateVO.setVerification(true);
        templateVO.setResult("搜索工程成功");

        return templateVO;
    }
}
*/
