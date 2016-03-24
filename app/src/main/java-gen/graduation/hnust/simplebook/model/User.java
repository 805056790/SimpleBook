package graduation.hnust.simplebook.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER".
 */
public class User {

    private Long id;
    private String userName;
    private String mobile;
    private String email;
    private String password;
    private Integer status;
    private String QqToken;
    private String weixinToken;
    private String weiboToken;
    private String nickname;
    private Integer type;
    private Integer gender;
    private java.util.Date birthday;
    private String image;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String userName, String mobile, String email, String password, Integer status, String QqToken, String weixinToken, String weiboToken, String nickname, Integer type, Integer gender, java.util.Date birthday, String image, java.util.Date createdAt, java.util.Date updatedAt) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.status = status;
        this.QqToken = QqToken;
        this.weixinToken = weixinToken;
        this.weiboToken = weiboToken;
        this.nickname = nickname;
        this.type = type;
        this.gender = gender;
        this.birthday = birthday;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQqToken() {
        return QqToken;
    }

    public void setQqToken(String QqToken) {
        this.QqToken = QqToken;
    }

    public String getWeixinToken() {
        return weixinToken;
    }

    public void setWeixinToken(String weixinToken) {
        this.weixinToken = weixinToken;
    }

    public String getWeiboToken() {
        return weiboToken;
    }

    public void setWeiboToken(String weiboToken) {
        this.weiboToken = weiboToken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
