package psyco.coder.ast.gen;

public class Field {
    public String name;
   public  String setter;
    public  String getter;
    public String type;

    public Field() {
    }

    public Field(String name, String setter, String getter, String type) {
        this.name = name;
        this.setter = setter;
        this.getter = getter;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetter() {
        return setter;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", setter='" + setter + '\'' +
                ", getter='" + getter + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

