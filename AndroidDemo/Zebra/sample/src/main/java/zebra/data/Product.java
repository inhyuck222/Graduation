package zebra.data;

/**
 * Created by multimedia on 2016-05-18.
 */
public class Product {
    int barcode;
    String productName;
    String description;
    int starPoint;
    String category;
    String productUrl;
    int scanCount;
    String companyName;
    int totalReviewCount;

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStarPoint(int starPoint) {
        this.starPoint = starPoint;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setScanCount(int scanCount) {
        this.scanCount = scanCount;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setTotalReviewCount(int totalReviewCount) {
        this.totalReviewCount = totalReviewCount;
    }

    public int getBarcode() {

        return barcode;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public int getStarPoint() {
        return starPoint;
    }

    public String getCategory() {
        return category;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public int getScanCount() {
        return scanCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getTotalReviewCount() {
        return totalReviewCount;
    }
}
