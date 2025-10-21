package hiber.model;

import javax.persistence.*;
import java.math.BigDecimal;
//@Entity
//@Table(name = "cars")
@Embeddable
public class Car {
    @Column(name = "model")
    String model;
    @Column(name = "series")
    int series;
    @Column(name = "price",precision = 19, scale = 2) // Указываем точность для BigDecimal
    BigDecimal price;


    public Car() {
        super();
    }
    public Car(String model, int series, BigDecimal price) {
        super();
        this.model = model;
        this.series = series;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Car{" +
                ", model='" + model + '\'' +
                ", series=" + series +
                ", price=" + price +

                '}';
    }
}
