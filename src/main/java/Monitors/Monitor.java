package Monitors;

public class Monitor {
    String name;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Monitor(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Monitor() {
    }
}
