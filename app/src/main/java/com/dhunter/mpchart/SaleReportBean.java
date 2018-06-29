package com.dhunter.mpchart;

import java.util.List;

/**
 * Created by dhunter on 2018/6/29.
 */

public class SaleReportBean {
    private List<SaleReportModel> models;

    public static class SaleReportModel {
        private String productCode;
        private String productName;
        private int saleNum;

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }

    public List<SaleReportModel> getModels() {
        return models;
    }

    public void setModels(List<SaleReportModel> models) {
        this.models = models;
    }
}
