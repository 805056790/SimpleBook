package graduation.hnust.simplebook.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ITEM".
 */
public class Item {

    private Long id;
    private Long userId;
    private Long bookId;
    private Integer type;
    private Integer status;
    private String image;
    private Integer imageId;
    private java.util.Date date;
    private String note;
    private Integer consumeType;
    private Integer amount;
    private String longitude;
    private String latitude;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public Item() {
    }

    public Item(Long id) {
        this.id = id;
    }

    public Item(Long id, Long userId, Long bookId, Integer type, Integer status, String image, Integer imageId, java.util.Date date, String note, Integer consumeType, Integer amount, String longitude, String latitude, java.util.Date createdAt, java.util.Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.type = type;
        this.status = status;
        this.image = image;
        this.imageId = imageId;
        this.date = date;
        this.note = note;
        this.consumeType = consumeType;
        this.amount = amount;
        this.longitude = longitude;
        this.latitude = latitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
