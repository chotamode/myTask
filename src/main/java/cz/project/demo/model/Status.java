package cz.project.demo.model;

public enum Status {
    DONE("STATUS_DONE"), NOTDONE("STATUS_NOTDONE");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
