package org.company.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity(name = Producer.TBL_NAME)
public class Producer extends AbstractClient {

    public static final String TBL_NAME="producers";

    public static final String FLD_PWPRODUCE="powerProduced";
    public static final String FLD_SUPRESSOR="powerforsell";


    @Column(name = FLD_PWPRODUCE,unique = false,nullable = false)
    @NotNull(message = "generated power of Client cant  be null")
    private float powerProduced;

    @Column(name = FLD_SUPRESSOR,unique = false,nullable = false)
    @NotNull(message = " power for sell of Client cant  be null")
    private float powerforsell;

    public Producer() {

    }

    public Producer(String clientId, String name, List<String> location, float pwConsumption, String email, String password, float powerProduced) {
        super(clientId, name, location, pwConsumption, email, password);
        this.powerProduced = powerProduced;
        this.powerforsell = powerProduced-pwConsumption;
    }

    public float getPowerforsell() {
        return powerforsell;
    }

    public void setPowerforsell(float powerforsell) {
        this.powerforsell = powerforsell;
    }

    public float getPowerProduced() {
        return powerProduced;
    }

    public void setPowerProduced(float powerProduced) {
        this.powerProduced = powerProduced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producer)) return false;
        if (!super.equals(o)) return false;
        Producer producer = (Producer) o;
        return Float.compare(producer.powerProduced, powerProduced) == 0 &&
                Float.compare(producer.powerforsell, powerforsell) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), powerProduced, powerforsell);
    }
}
