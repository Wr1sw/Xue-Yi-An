package org.cuit.xueyian.service;

import javafx.geometry.Pos;
import org.cuit.xueyian.dao.*;
import org.cuit.xueyian.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeRemoveService {
    @Autowired
    EmployeeremoveMapper employeeremoveMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    JObLevelMapper jObLevelMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
   EmployeeService employeeService;
    //分页获取数据
    public PageResp<Employeeremove> getEmployeeListByPage(Integer PageSize, Integer currentPage){
        if (PageSize == null ||  currentPage == null) {
            throw new RuntimeException("非法数据");
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数

        PageResp<Employeeremove> res = new PageResp<>();

        List<Employeeremove> list = employeeremoveMapper.getEmployeeListByPage(map); // 所有数据
        setOtherInfo(list); //设置姓名数据

        //获取总条数
        res.setTotal(employeeremoveMapper.getTotal());
        res.setData(list);
        return res;
    }

    // 查询
    public PageResp<Employeeremove> queryInfo(Employeeremove empRm, Integer PageSize, Integer currentPage){
        if (PageSize == null ||  currentPage == null) {
            throw new RuntimeException("非法数据");
        }
        // 查询 员工id 现在posid,jobid,depid


        HashMap<String,Object> map=new HashMap<>();

        map.put("name",empRm.getName());
        map.put("afterDepId",empRm.getAfterdepid());
        map.put("afterJobId",empRm.getAfterjobid());
        map.put("afterPosId",empRm.getAfterposid());

        map.put("PageSize",PageSize);
        map.put("offsetData", (currentPage-1)*PageSize); // 设置数据库数据开始的条数

        List<Employeeremove> list = employeeremoveMapper.queryInfo(map);
        if (list != null && list.size() > 0) {
            //list不为空
            setOtherInfo(list);
        }


        PageResp<Employeeremove> res = new PageResp<>();

        Long length = employeeremoveMapper.queryInfoLength(map);
        if(length<0 ) throw new RuntimeException("数据异常");
        res.setTotal(length);
        res.setData(list);
        return res;
    }

    /**
     * 功能描述:  处理 所有字段的名称
     * @Param: [list]
     * @Return: java.util.List<org.cuit.xueyian.model.Employeeremove>
     * @Author: wsy
     * @Date: 2022/5/30 0:43
     */
    private List<Employeeremove>  setOtherInfo(List<Employeeremove> list){
        //取出 beforeDepId 这一列
        List<Integer> beforeDepIdList = list.stream().map(Employeeremove::getBeforedepid).collect(Collectors.toList());
        List<Integer> afterDepIdList = list.stream().map(Employeeremove::getAfterdepid).collect(Collectors.toList());
        List<Department> beforeDepList =  departmentMapper.selectNameByIdList(beforeDepIdList); // 数据库查到 对应name
        List<Department> afterDepList =  departmentMapper.selectNameByIdList(afterDepIdList); // 数据库查到 对应name

        //取出 beforeJobId 这一列
        List<Integer> beforeJobId = list.stream().map(Employeeremove::getBeforejobid).collect(Collectors.toList());
        List<Integer> afterJobId = list.stream().map(Employeeremove::getAfterjobid).collect(Collectors.toList());
        List<JObLevel> beforeJobList =  jObLevelMapper.selectNameByIdList(beforeJobId); // 数据库查到 对应name
        List<JObLevel> afterJobList =  jObLevelMapper.selectNameByIdList(afterJobId); // 数据库查到 对应name

        //取出 beforePosId 这一列
        List<Integer> beforePosId = list.stream().map(Employeeremove::getBeforeposid).collect(Collectors.toList());
        List<Integer> afterPosId = list.stream().map(Employeeremove::getAfterposid).collect(Collectors.toList());
        List<Position> beforePosList =  positionMapper.selectNameByIdList(beforePosId); // 数据库查到 对应name
        List<Position> afterPosList =  positionMapper.selectNameByIdList(afterPosId); // 数据库查到 对应name

        for(Employeeremove empMv :list){
            for(Department dep :beforeDepList){
                if(Objects.equals(empMv.getBeforedepid(), dep.getId())){
                    empMv.setBeforedepName(dep.getName());
                }
            }

            for(Department dep :afterDepList){
                if(Objects.equals(empMv.getAfterdepid(), dep.getId())){
                    empMv.setAfterdepName(dep.getName());
                }
            }
            for(JObLevel job :beforeJobList){
                if(Objects.equals(empMv.getBeforejobid(), job.getId())){
                    empMv.setBeforejobName(job.getName());
                }
            }

            for(JObLevel job :afterJobList){
                if(Objects.equals(empMv.getAfterjobid(), job.getId())){
                    empMv.setAfterjobName(job.getName());
                }
            }
            for(Position pos :beforePosList){
                if(Objects.equals(empMv.getBeforeposid(), pos.getId())){
                    empMv.setBeforeposName(pos.getName());
                }
            }

            for(Position pos :afterPosList){
                if(Objects.equals(empMv.getAfterposid(), pos.getId())){
                    empMv.setAfterposName(pos.getName());
                }
            }
        }
        return list;
    }

    //根据employeeRemove ID获取调动信息
    public Employeeremove  selectByEmpRmId(Integer id){
        Employeeremove  empMv =  employeeremoveMapper.getEmpRmById(id);

        if(empMv == null) throw new RuntimeException("数据异常");
        empMv.setBeforejobName(jObLevelMapper.selectByPrimaryKey(empMv.getBeforejobid()).getName());
        empMv.setBeforedepName(departmentMapper.selectByPrimaryKey(empMv.getBeforedepid()).getName());
        empMv.setBeforeposName(positionMapper.selectByPrimaryKey(empMv.getBeforeposid()).getName());
        return empMv;
    }

    // 获取 dep,job,pos,List
    public HashMap<String,List<Object>>  getNameList(){

        List<Department> departmentList = departmentMapper.getID_Name();
        List<JObLevel> jobLevelList = jObLevelMapper.getID_Name();
        List<Position> positionList = positionMapper.getID_Name();

        HashMap<String,List<Object>> map = new HashMap<>();
        map.put("departmentList", Collections.singletonList(departmentList));
        map.put("jobLevelList", Collections.singletonList(jobLevelList));
        map.put("positionList", Collections.singletonList(positionList));
        return map;
    }

    // 添加
    public Integer addEmpRm(Employeeremove empMv){

        // 需要修改 emp表信息
        Employee emp = new Employee();
        emp.setId(empMv.getEid());
        emp.setDepartmentid(empMv.getAfterdepid());
        emp.setJoblevelid(empMv.getAfterjobid());
        emp.setPosid(empMv.getAfterposid());

        Integer empLength = employeeMapper.updateByPrimaryKeySelective(emp);
        if(empLength <0) throw new RuntimeException("员工更新异常");

        Integer empEcLength = employeeremoveMapper.insertSelective(empMv);
        if(empEcLength <0) throw new RuntimeException("员工调动添加异常");
        return empEcLength;
    }

    // 修改
    public Integer editEmpRm(Employeeremove empMv){
        Employee emp = new Employee();
        emp.setId(empMv.getEid());
        emp.setDepartmentid(empMv.getAfterdepid());
        emp.setJoblevelid(empMv.getAfterjobid());
        emp.setPosid(empMv.getAfterposid());

        Integer empLength = employeeMapper.updateByPrimaryKeySelective(emp);
        if(empLength <0) throw new RuntimeException("员工更新异常");

        Integer empEcLength = employeeremoveMapper.updateByPrimaryKeySelective(empMv);
        if(empEcLength <0) throw new RuntimeException("员工调动更新异常");
        return empEcLength;
    }


    public Employeeremove getEmpInfo(Integer eid){
        if(eid == null) throw  new RuntimeException("数据异常");
        Employee emp = employeeMapper.selectAllInfoById(eid);
        Employeeremove empRm = new Employeeremove();
        empRm.setEid(eid);
        empRm.setName(emp.getName());
        empRm.setBeforeposid(emp.getPosid());
        empRm.setBeforeposName(emp.getPosition().getName());
        empRm.setBeforedepid(emp.getDepartmentid());
        empRm.setBeforedepName(emp.getDepartment().getName());
        empRm.setBeforejobid(emp.getJoblevelid());
        empRm.setBeforejobName(emp.getjOblevel().getName());
        return empRm;
    }

    // 获取部门与emp映射信息
    public List<Map<String,Object>> getDepEmpList(){
        return employeeService.getDepEmpList();
    }

    // 删除 -- 大概不用------
}
