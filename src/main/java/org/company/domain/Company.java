package org.company.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity(name = Company.TBL_NAME)
public class Company extends AbstractEntity<Long> {



    public static final String FLD_REFERENCE="reference";
    public static final String FLD_TYPE="type";
    public static final String FLD_UNIT="unit";
    public static final String FLD_RATE="updatingRate";
    public static final String TBL_NAME="companies";
    public static final String FLD_CID="companyId";


    @Column(name = FLD_REFERENCE,unique = false,nullable = false)
    @NotNull(message = "name of Company reference cant  be null")
    @Size(min = 2,max =30,message = "Length of Company reference name  can be 2-30")
    private String reference;



    @Column(name = FLD_CID,unique = true,nullable = false)
    @NotNull(message = "id of Company reference cant  be null")
    @Size(min = 2,max =30,message = "Length of id Company    can be 2-30")
    private String companyId;

    @Column(name = FLD_TYPE,unique = false,nullable = false)
    @NotNull(message = "type of Company provider can't be null")
    @Size(min = 2,max =10,message = "Length of Company type name  can be 2-10")
    private String type;

    @Column(name = FLD_UNIT,unique = false)
    private String unit;

    @Column(name = FLD_RATE,unique = false,nullable = false)
    @NotNull(message = "updating rate of Company provider can't be null")
    private String updatingRate;

    public Company() {

    }

    public Company(Long aLong, String reference, String type, String updatingRate,String companyId) {
        super(aLong);
        this.reference = reference;
        this.type = type;
        this.unit = "Watt";
        this.updatingRate = updatingRate;
        this.companyId=companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUpdatingRate() {
        return updatingRate;
    }

    public void setUpdatingRate(String updatingRate) {
        this.updatingRate = updatingRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return reference.equals(company.reference) &&
                companyId.equals(company.companyId) &&
                type.equals(company.type) &&
                Objects.equals(unit, company.unit) &&
                updatingRate.equals(company.updatingRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reference, companyId, type, unit, updatingRate);
    }
}
