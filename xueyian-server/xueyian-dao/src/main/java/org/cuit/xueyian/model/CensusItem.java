package org.cuit.xueyian.model;

public class CensusItem {

    // 每一项数据
    private String name;

    // 对应的人数
    private Integer  value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CensusItem(){

    }

    public CensusItem(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

}
