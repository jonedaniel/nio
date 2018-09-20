package excelexport;

import excelexport.model.Model;

public class Generator {
    public static void main(String[] args) {
        ExcelExport.exportPoi(sql, Model.class);
    }

    static final String sql = "select ID id,RESBLOCK_NAME resblockName,RESBLOCK_CODE resblockCode from (select RESBLOCK_NAME,RESBLOCK_CODE,ID from hdic.T_HM_RESBLOCK) where ROWNUM < 10";
}
