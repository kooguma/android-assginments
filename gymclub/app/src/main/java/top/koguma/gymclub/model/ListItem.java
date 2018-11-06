package top.koguma.gymclub.model;

import com.laputapp.model.BaseModel;

public class ListItem extends BaseModel {

    public String imageUrl;
    public String title;
    public String avatarUrl;
    public String userName;
    public String flavors;

    public static ListItem testInstance() {
        ListItem item = new ListItem();
        item.imageUrl = "";
        item.title = "#平板撑 标题标题";
        item.avatarUrl = "";
        item.userName = "koguma";
        item.flavors = "233";
        return item;
    }
}
