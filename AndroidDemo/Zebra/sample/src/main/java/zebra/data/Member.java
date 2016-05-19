package zebra.data;

import java.util.Date;

/**
 * Created by multimedia on 2016-05-18.
 */
public class Member {
    String id;
    String password;
    String name;
    int phoneNumber;
    int point;
    int level;
    Date lastReviewDate;
    int reviewCount;
    int totalReviewCount;

    public void setId(String id) {
        this.id = id;
    }

    public void setPaswword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLastReviewDate(Date lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setTotalReviewCount(int totalReviewCount) {        this.totalReviewCount = totalReviewCount;    }

    public String getId() {       return id;    }

    public String getPaswword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getPoint() {
        return point;
    }

    public int getLevel() {
        return level;
    }

    public Date getLastReviewDate() {
        return lastReviewDate;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public int getTotalReviewCount() {
        return totalReviewCount;
    }

}
