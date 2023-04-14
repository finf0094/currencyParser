import java.io.Serializable;

public class ReqAndResponse implements Serializable {
    private double money;

    public ReqAndResponse(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ReqAndResponse{" +
                "money=" + money +
                '}';
    }
}
