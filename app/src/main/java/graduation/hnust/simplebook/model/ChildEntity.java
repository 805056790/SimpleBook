package graduation.hnust.simplebook.model;


import lombok.Getter;
import lombok.Setter;

public class ChildEntity {
    private String childTitle;

    @Setter @Getter
    private Item item;

    public ChildEntity(String childTitle) {
        this.childTitle = childTitle;
    }

    public String getChildTitle() {
        return childTitle;
    }

}
