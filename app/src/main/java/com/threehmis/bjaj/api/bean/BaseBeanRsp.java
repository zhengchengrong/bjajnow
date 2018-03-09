package com.threehmis.bjaj.api.bean;

import java.util.List;

/**
 * Created by 3hcd on 2016/12/13 0013.
 */

public class BaseBeanRsp<DATA>  {
    /**
     * projectList : []
     * result : 未找到监督计划，新建一份监督计划
     * verification : true
     * showSubmit : 1
     * jokerVO : {"address":"朝阳区新源南路8号院3号楼（左家庄）","beginDate":"","endDate":"","dangerousList":[],"monitorList":[{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"}],"projectId":"5f82526c-ffae-4b4d-b63b-0d357c7db42d","projectNum":"京建安[2018]0060号","projectName":"朝阳区新源南路8号院3号楼101室局部内装工程","jdjhJSDW":"北京新荣记餐饮管理有限公司"}
     */

    public  String result;
    public boolean verification;
    public    String showSubmit;
    public  JokerVOBean jokerVO;
    public List<DATA> projectList;

    // 根据后台给的信息，看什么代码代表成功，我的是 001
    public boolean isSuccess() {
        return verification == true;
    }
    public boolean isError() {
        return verification == false;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(String showSubmit) {
        this.showSubmit = showSubmit;
    }

    public JokerVOBean getJokerVO() {
        return jokerVO;
    }

    public void setJokerVO(JokerVOBean jokerVO) {
        this.jokerVO = jokerVO;
    }

    public List<DATA> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<DATA> projectList) {
        this.projectList = projectList;
    }

    public  class JokerVOBean {
        /**
         * address : 朝阳区新源南路8号院3号楼（左家庄）
         * beginDate :
         * endDate :
         * dangerousList : []
         * monitorList : [{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"},{"monitorType":"监督组长","monitorTitleNo":"112121212","monitorName":"武卫兵"}]
         * projectId : 5f82526c-ffae-4b4d-b63b-0d357c7db42d
         * projectNum : 京建安[2018]0060号
         * projectName : 朝阳区新源南路8号院3号楼101室局部内装工程
         * jdjhJSDW : 北京新荣记餐饮管理有限公司
         */

        private String address;
        private String beginDate;
        private String endDate;
        private String projectId;
        private String projectNum;
        private String projectName;
        private String jdjhJSDW;



        private List<?> dangerousList;
        private List<MonitorListBean> monitorList;
        private List<MonitorListBean> personList;

        private List<MonitorListBean> jokerVOList;

        public List<MonitorListBean> getJokerVOList() {
            return jokerVOList;
        }

        public void setJokerVOList(List<MonitorListBean> jokerVOList) {
            this.jokerVOList = jokerVOList;
        }

        public List<MonitorListBean> getPersonList() {
            return personList;
        }

        public void setPersonList(List<MonitorListBean> personList) {
            this.personList = personList;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectNum() {
            return projectNum;
        }

        public void setProjectNum(String projectNum) {
            this.projectNum = projectNum;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getJdjhJSDW() {
            return jdjhJSDW;
        }

        public void setJdjhJSDW(String jdjhJSDW) {
            this.jdjhJSDW = jdjhJSDW;
        }

        public List<?> getDangerousList() {
            return dangerousList;
        }

        public void setDangerousList(List<?> dangerousList) {
            this.dangerousList = dangerousList;
        }

        public List<MonitorListBean> getMonitorList() {
            return monitorList;
        }

        public void setMonitorList(List<MonitorListBean> monitorList) {
            this.monitorList = monitorList;
        }

        public  class MonitorListBean {
            /**
             * monitorType : 监督组长
             * monitorTitleNo : 112121212
             * monitorName : 武卫兵
             */
            private String monitorType;
            private String monitorTitleNo;
            private String monitorName;
            private String personId;

            private String branchName;
            private String pk;

            private String lawType;
            private String lawContent;
            private String id;

            public String getLawType() {
                return lawType;
            }

            public void setLawType(String lawType) {
                this.lawType = lawType;
            }

            public String getLawContent() {
                return lawContent;
            }

            public void setLawContent(String lawContent) {
                this.lawContent = lawContent;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
            public String getBranchName() {
                return branchName;
            }

            public void setBranchName(String branchName) {
                this.branchName = branchName;
            }

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public String getPersonId() {
                return personId;
            }

            public void setPersonId(String personId) {
                this.personId = personId;
            }

            public String getMonitorType() {
                return monitorType;
            }

            public void setMonitorType(String monitorType) {
                this.monitorType = monitorType;
            }

            public String getMonitorTitleNo() {
                return monitorTitleNo;
            }

            public void setMonitorTitleNo(String monitorTitleNo) {
                this.monitorTitleNo = monitorTitleNo;
            }

            public String getMonitorName() {
                return monitorName;
            }

            public void setMonitorName(String monitorName) {
                this.monitorName = monitorName;
            }
        }
    }

/*    public boolean verification;  //是否有数据返回 true 有 flase 没有

//    public int total, iscollection;
//
    public String result; //标示后台接口异常信息 如 无此ID数据

    public int showSubmit; //showSubmit("0" 显示　"1" 不显示)

    public ArrayList<DATA> projectList; //返回的数据对象





    // 根据后台给的信息，看什么代码代表成功，我的是 001
    public boolean isSuccess() {
        return verification == true;
    }
    public boolean isError() {
        return verification == false;
    }



    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(int showSubmit) {
        this.showSubmit = showSubmit;
    }

    public ArrayList<DATA> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<DATA> projectList) {
        this.projectList = projectList;
    }*/
}
