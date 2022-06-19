package org.cuit.xueyian.model;

public class EmployeePerformance {
    private Integer id;

    private String name;

    private String kpi;

    private Double output;

    private Double averCapacity;

    private Double defectRate;

    private Double passRate;

    private Double onepassRate;

    private Double reworkRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public Double getOutput() {
        return output;
    }

    public void setOutput(Double output) {
        this.output = output;
    }

    public Double getAverCapacity() {
        return averCapacity;
    }

    public void setAverCapacity(Double averCapacity) {
        this.averCapacity = averCapacity;
    }

    public Double getDefectRate() {
        return defectRate;
    }

    public void setDefectRate(Double defectRate) {
        this.defectRate = defectRate;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Double getOnepassRate() {
        return onepassRate;
    }

    public void setOnepassRate(Double onepassRate) {
        this.onepassRate = onepassRate;
    }

    public Double getReworkRate() {
        return reworkRate;
    }

    public void setReworkRate(Double reworkRate) {
        this.reworkRate = reworkRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", kpi=").append(kpi);
        sb.append(", output=").append(output);
        sb.append(", averCapacity=").append(averCapacity);
        sb.append(", defectRate=").append(defectRate);
        sb.append(", passRate=").append(passRate);
        sb.append(", onepassRate=").append(onepassRate);
        sb.append(", reworkRate=").append(reworkRate);
        sb.append("]");
        return sb.toString();
    }
}