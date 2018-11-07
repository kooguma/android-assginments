package top.koguma.gymclub.model;

import com.laputapp.model.BaseModel;

public class Dashboard extends BaseModel {

    public String imageUrl;
    public String title;
    public String avatarUrl;
    public String userName;
    public String flavors;

    public static Dashboard testInstance() {
        Dashboard item = new Dashboard();
        item.imageUrl = "";
        item.title = "#平板撑 标题标题";
        item.avatarUrl = "";
        item.userName = "koguma";
        item.flavors = "233";
        return item;
    }
}
