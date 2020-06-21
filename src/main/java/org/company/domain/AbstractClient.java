package org.company.domain;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity(name=AbstractClient.TBL_NAME)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractClient extends AbstractEntity<Long> {

    public static final String TBL_NAME="clients";

    public static final String FLD_NAME="name";
    public static final String FLD_CLIENTID="clientId";
    public static final String FLD_EMAIL="email";
    public static final String FLD_LOCATION="location";
    public static final String FLD_PASSWORD="password";
    public static final String FLD_CONSUMTION="pwConsumption";


    @Column(name = FLD_CLIENTID,unique = true,nullable = false)
    @NotNull(message = "id of Clinet can't be null")
    @Size(min = 2,max =30,message = "Length of id Client   can be 2-30")
    private String clientId;

    @Column(name = FLD_NAME,unique = false,nullable = false)
    @NotNull(message = "name of Client cant  be null")
    @Size(min = 2,max =30,message = "Length of Client name  can be 2-30")
    private String name;

    @ElementCollection
    @Column(name = FLD_LOCATION,unique = false,nullable = false)
    @NotNull(message = "Location list of Client cant  be null")
    @Size(min = 2,max =20,message = "Length of Client location list name  can be 2-20")
    private List<String> location;


    @Column(name = FLD_CONSUMTION,unique = false,nullable = false)
    @NotNull(message = "consumption of Client cant  be null")
    private float pwConsumption;



    @Column(name = FLD_EMAIL,unique = true,nullable = false)
    @NotNull(message = "email of Client cant  be null")
    @Size(min = 2,max =30,message = "Length of Client email  can be 2-30")
    private String email;



    @Column(name = FLD_PASSWORD,unique = false,nullable = false)
    @NotNull(message = "password of Client cant  be null")
    @Size(min = 2,max =30,message = "Length of Client password  can be 2-20")
    private String password;


    public AbstractClient() {
    }

    public AbstractClient(String clientId, String name, List<String> location, float pwConsumption, String email, String password) {
        this.clientId = clientId;
        this.name = name;
        this.location = location;
        this.pwConsumption = pwConsumption;
        this.email = email;
        this.password = hashPassword(password);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public float getPwConsumption() {
        return pwConsumption;
    }

    public void setPwConsumption(float pwConsumption) {
        this.pwConsumption = pwConsumption;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }
    public void setHashedPass(String password) {
        this.password = password;
    }


    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractClient)) return false;
        AbstractClient that = (AbstractClient) o;
        return Float.compare(that.pwConsumption, pwConsumption) == 0 &&
                clientId.equals(that.clientId) &&
                name.equals(that.name) &&
                location.equals(that.location) &&
                email.equals(that.email) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, name, location, pwConsumption, email, password);
    }
}
