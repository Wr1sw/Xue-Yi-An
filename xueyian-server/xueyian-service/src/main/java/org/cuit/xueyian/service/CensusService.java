package org.cuit.xueyian.service;

import org.cuit.xueyian.dao.CensusMapper;
import org.cuit.xueyian.model.Census;
import org.cuit.xueyian.model.CensusItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CensusService {

    @Autowired
    CensusMapper mapper;

    /****************************************** 综合统计信息用 ***********************************************************/

    /**
     * 获取公司所有人的毕业学校信息
     * @return 毕业学校集合
     */
    public Census getSchoolInfo(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getSchoolInfo(), "毕业学校信息", "人数", "#aaacf3", "#eb87aa");
    }

    /**
     * 获取公司员工婚姻状况信息
     * @return 婚姻状况信息集合
     */
    public Census getWedlock(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getWedlock(), "婚姻信息", "人数", "#8dc1a9", "#65cbc8");
    }

    /**
     * 获取公司所有的学历信息
     * @return 学历信息集合
     */
    public Census getTopDegree(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getTopDegree(), "学历信息信息", "人数", "#d5befd", "#9c9eff");
    }

    /**
     * 获取公司所有人的专业信息
     * @return 专业信息集合
     */
    public Census getMajor(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getMajor(), "专业信息", "人数", "#85e8b9", "#f18b87");
    }

    /**
     * 获取公司所有人的性别信息
     * @return 性别信息集合
     */
    public Census getGender(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getGender(), "公司员工性别信息", "人数", "#c5d9f3", "#87CEEB");
    }

    /**
     * 获取公司所有人的政治信息
     * @return 政治信息集合
     */
    public Census getPoliticStatus(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getPoliticStatus(), "公司员工政治信息", "人数", "#87CEEB", "#9c9eff");
    }

    /**
     * 获取公司所有人的出生年信息
     * @return 出生年信息集合
     */
    public Census getBirthYear(){
        ArrayList<CensusItem> items = new ArrayList<>();
        CensusItem item;
        HashMap<String, Date> map;
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date minYear= this.getFloorDate(mapper.getMinYear());
        Date maxYear = mapper.getMaxYear();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(minYear);
        Date former = minYear;
        calendar.add(Calendar.YEAR, 10);
        Date latter = calendar.getTime();
        while(former.compareTo(maxYear) < 1){
            map = new HashMap<>();
            map.put("former", former);
            map.put("latter", latter);
            String year = format.format(former).substring(2)+"后";
            Integer value = mapper.getBirthYear(map);
            item = new CensusItem();
            item.setValue(value);
            item.setName(year);
            items.add(item);
            former = latter;
            calendar.add(Calendar.YEAR, 10);
            latter = calendar.getTime();
        }
        return this.getCensus(items, "公司各年龄阶段的人","人数","#a5d951", "#f18b87");
    }



    /******************************************** 人事信息用 ****************************************************/

    /**
     * 获取公司所有部门人数信息集合
     * @return 公司各部门人数集合
     */
    public Census getDepartmentPerson(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getDepartmentPerson(), "各部门人数信息", "人数", "#c5d9f3", "#87CEEB");
    }

    /**
     * 获取公司所有的职称信息
     * @return 职称信息集合
     */
    public Census getTitle(){
        return this.getCensus((ArrayList<CensusItem>) mapper.getTitle(), "职称信息", "人数", "#87CEEB", "#9c9eff");
    }

    /**
     * 获取公司所有员工的工龄
     * @return 工龄信息集合
     */
    public Census getWorkAge(){
        int maxAge = mapper.getMaxWorkAge();;
        int minAge = mapper.getMinWorkAge();
        int end = (int)Math.ceil((double)maxAge/12);
        int start = (int)Math.floor((double)minAge/12);
        ArrayList<CensusItem> items = new ArrayList<>();
        CensusItem item;

        for(int i = start; i <= end; i++){
            Integer former = i * 12;
            Integer latter = (i+1) * 12;
            HashMap<String, Integer> map = new HashMap<>();
            map.put("former", former);
            map.put("latter", latter);
            Integer res = mapper.getWorkAge(map);
            if(res != 0){
                item = new CensusItem();
                item.setName(String.valueOf(i)+"年");
                item.setValue(res);
                items.add(item);
            }
        }
        return this.getCensus(items, "公司员工工龄信息","人数", "#c5d9f3", "#87CEEB");
    }

    /**
     * 获取人事记录信息PersonnelInfo
     * @return 人事记录信息
     */
    public Census getCensus(ArrayList<CensusItem> items, String chartTitle, String chartSeries, String barColor, String labelColor){
        Census info = new Census();
        info.setItems(items);
        info.setChartSeries(chartSeries);
        info.setChartTitle(chartTitle);
        info.setBarColor(barColor);
        info.setLabelColor(labelColor);
        return info;
    }

    /**
     * 得到小于最近年份的整十年
     * @param date 当前年
     * @return 整十年
     */
    public Date getFloorDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String str = format.format(date);
        int day = (int) Math.floor((Double.parseDouble(str)/10)) * 10;
        String res = String.valueOf(day);
        try {
            return format.parse(res);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
