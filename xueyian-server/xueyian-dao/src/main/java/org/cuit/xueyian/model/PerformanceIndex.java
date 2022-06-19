package org.cuit.xueyian.model;

public class PerformanceIndex {
    private Integer id;

    /**
    * 产量
    */
    private Double output;

    /**
    * >=output
    */
    private Integer outputScore;

    /**
    * 时均产能
    */
    private Double averCapacity;

    /**
    * >= aver_capacity
    */
    private Integer averCapacityScore;

    /**
    * 不良率
    */
    private Double defectRate;

    /**
    * <=defect_rate
    */
    private Integer defectRateScore;

    /**
    * 产品合格率
    */
    private Double passRate;

    /**
    * >=pass_rate
    */
    private Integer passRateScore;

    /**
    * 一次性合格率
    */
    private Double onepassRate;

    /**
    * >=onepass_rate
    */
    private Integer onepassRateScore;

    /**
    * 返工率
    */
    private Double reworkRate;

    /**
    * <=rework_rate
    */
    private Integer reworkRateScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getOutput() {
        return output;
    }

    public void setOutput(Double output) {
        this.output = output;
    }

    public Integer getOutputScore() {
        return outputScore;
    }

    public void setOutputScore(Integer outputScore) {
        this.outputScore = outputScore;
    }

    public Double getAverCapacity() {
        return averCapacity;
    }

    public void setAverCapacity(Double averCapacity) {
        this.averCapacity = averCapacity;
    }

    public Integer getAverCapacityScore() {
        return averCapacityScore;
    }

    public void setAverCapacityScore(Integer averCapacityScore) {
        this.averCapacityScore = averCapacityScore;
    }

    public Double getDefectRate() {
        return defectRate;
    }

    public void setDefectRate(Double defectRate) {
        this.defectRate = defectRate;
    }

    public Integer getDefectRateScore() {
        return defectRateScore;
    }

    public void setDefectRateScore(Integer defectRateScore) {
        this.defectRateScore = defectRateScore;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Integer getPassRateScore() {
        return passRateScore;
    }

    public void setPassRateScore(Integer passRateScore) {
        this.passRateScore = passRateScore;
    }

    public Double getOnepassRate() {
        return onepassRate;
    }

    public void setOnepassRate(Double onepassRate) {
        this.onepassRate = onepassRate;
    }

    public Integer getOnepassRateScore() {
        return onepassRateScore;
    }

    public void setOnepassRateScore(Integer onepassRateScore) {
        this.onepassRateScore = onepassRateScore;
    }

    public Double getReworkRate() {
        return reworkRate;
    }

    public void setReworkRate(Double reworkRate) {
        this.reworkRate = reworkRate;
    }

    public Integer getReworkRateScore() {
        return reworkRateScore;
    }

    public void setReworkRateScore(Integer reworkRateScore) {
        this.reworkRateScore = reworkRateScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", output=").append(output);
        sb.append(", outputScore=").append(outputScore);
        sb.append(", averCapacity=").append(averCapacity);
        sb.append(", averCapacityScore=").append(averCapacityScore);
        sb.append(", defectRate=").append(defectRate);
        sb.append(", defectRateScore=").append(defectRateScore);
        sb.append(", passRate=").append(passRate);
        sb.append(", passRateScore=").append(passRateScore);
        sb.append(", onepassRate=").append(onepassRate);
        sb.append(", onepassRateScore=").append(onepassRateScore);
        sb.append(", reworkRate=").append(reworkRate);
        sb.append(", reworkRateScore=").append(reworkRateScore);
        sb.append("]");
        return sb.toString();
    }
}