package excelexport.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Model {
    private BigDecimal id;
    private String     resblockName;
    private String     resblockCode;

}
