public class Node {
    private String name;
    private String value;

    public Node(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "Node [name=" + name + ", value=" + value + "]";
    }
}
