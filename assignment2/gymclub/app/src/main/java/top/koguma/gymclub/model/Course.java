package top.koguma.gymclub.model;

import com.laputapp.model.BaseModel;

public class Course extends BaseModel {

    public String title;
    public String imgUrl;
    public String description;
    public String readCount;
    public String collectCount;
    public String avatar;
    public String name;

    public static Course testInstance() {
        Course course = new Course();
        course.title = "[一周最热] 最搞笑的减肥法，不运动也能燃脂！";
        course.description = "还在只靠慢跑减肥吗？你该试试最高效的减肥方法";
        course.readCount = "123";
        course.collectCount = "123";
        course.name = "koguma";
        return course;
    }
}
