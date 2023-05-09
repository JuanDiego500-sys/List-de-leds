package co.edu.umanizales.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDE {
    private Led data;
    private NodeDE next;
    private NodeDE previous;

    public NodeDE(Led data) {
        this.data = data;
    }
}
