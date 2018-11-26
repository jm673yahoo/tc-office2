package ca.mydemo.tcoffice.domain.profile;

public class UserProfile {

    private String groupLevelId;
    private UserType userType;
    private String partnerId;
    private String bizId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }


    public String getGroupLevelId() {
        return groupLevelId;
    }

    public void setGroupLevelId(String groupLevelId) {
        this.groupLevelId = groupLevelId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


}
