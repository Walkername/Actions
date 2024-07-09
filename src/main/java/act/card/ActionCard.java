package act.card;

public class ActionCard {

    private String name;
    private int priority;
    private String content;

    public ActionCard(String name, int priority, String content) {
        this.name = name;
        this.priority = priority;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
