package localexport;

import java.math.BigDecimal;

public class Block {
    private String     blockName;
    private BigDecimal houseCount;
    private BigDecimal houseTypeCount;
    private BigDecimal buildingCount;

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public BigDecimal getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(BigDecimal houseCount) {
        this.houseCount = houseCount;
    }

    public BigDecimal getHouseTypeCount() {
        return houseTypeCount;
    }

    public void setHouseTypeCount(BigDecimal houseTypeCount) {
        this.houseTypeCount = houseTypeCount;
    }

    public BigDecimal getBuildingCount() {
        return buildingCount;
    }

    public void setBuildingCount(BigDecimal buildingCount) {
        this.buildingCount = buildingCount;
    }
}
