package act.card;

import java.io.Serializable;

public class ActionCard implements Serializable {

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

    @Override
    public String toString() {
        return this.name;
    }
}
