package org.cuit.xueyian.model;

import java.util.ArrayList;

/**
 * <p>
 * 人事信息统计实体类
 * </p>
 *
 * @author Miracle
 * @since 2022-05-15
 */
public class Census {

    //图形数据
    private ArrayList<CensusItem> items;

    //饼图的标题
    private String chartTitle;

    //饼图的单位
    private String chartSeries;

    //柱状图的颜色
    private String barColor;

    //柱状图标签颜色
    private String labelColor;

    public ArrayList<CensusItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CensusItem> items) {
        this.items = items;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getChartSeries() {
        return chartSeries;
    }

    public void setChartSeries(String chartSeries) {
        this.chartSeries = chartSeries;
    }

    public String getBarColor() {
        return barColor;
    }

    public void setBarColor(String barColor) {
        this.barColor = barColor;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }
}
