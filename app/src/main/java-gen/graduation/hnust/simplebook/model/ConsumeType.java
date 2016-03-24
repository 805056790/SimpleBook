package graduation.hnust.simplebook.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CONSUME_TYPE".
 */
public class ConsumeType {

    private Long id;
    private String name;
    private Integer status;
    private String image;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public ConsumeType() {
    }

    public ConsumeType(Long id) {
        this.id = id;
    }

    public ConsumeType(Long id, String name, Integer status, String image, java.util.Date createdAt, java.util.Date updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
