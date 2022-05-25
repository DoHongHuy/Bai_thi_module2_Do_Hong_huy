package files.model;

import java.time.Instant;
import java.util.Objects;

public class Techs implements Comparable<Techs> {

    private long id;
    private String nameTechs;
    private double priceTechs;
    private int quantityTechs;
    private String type;
    private Instant creatDate;
    private Instant updateDate;

    public Techs(Long id, String nameTechs, Double priceTechs, Integer quantityTechs, String type) {
        this.id = id;
        this.nameTechs = nameTechs;
        this.priceTechs = priceTechs;
        this.quantityTechs = quantityTechs;
        this.type = type;
    }

    public Techs() {
    }

    public static Techs parseTechs(String rar) {
        Techs techs = new Techs();
        String[] strings = rar.split(",");
        techs.id = Integer.parseInt(strings[0]);
        techs.nameTechs = strings[1];
        techs.priceTechs = Double.parseDouble(strings[2]);
        techs.quantityTechs = Integer.parseInt(strings[3]);
        techs.type = strings[4];
        String temp = strings[5];
        techs.creatDate = Instant.parse(temp);
        temp = strings[6];
        if (temp != null && !temp.equals("null"))
            techs.updateDate = Instant.parse(temp);
        return techs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameTechs() {
        return nameTechs;
    }

    public void setNameTechs(String nameTechs) {
        this.nameTechs = nameTechs;
    }

    public double getPriceTechs() {
        return priceTechs;
    }

    public void setPriceTechs(double priceTechs) {
        this.priceTechs = priceTechs;
    }

    public int getQuantityTechs() {
        return quantityTechs;
    }

    public void setQuantityTechs(int quantityTechs) {
        this.quantityTechs = quantityTechs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Instant creatDate) {
        this.creatDate = creatDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return id + "," +
                nameTechs + "," +
                priceTechs + "," +
                quantityTechs + "," +
                type + "," +
                creatDate + ","
                + updateDate;
    }

    @Override
    public int compareTo(Techs o) {
        if (priceTechs - o.priceTechs == 0) {
            if (quantityTechs - o.quantityTechs == 0) {
                return nameTechs.compareTo(o.nameTechs);
            } else {
                return quantityTechs - o.quantityTechs;
            }
        }
        return (int) (priceTechs - o.priceTechs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Techs techs = (Techs) o;
        return id == techs.id && quantityTechs == techs.quantityTechs && priceTechs == techs.priceTechs && Objects.equals(nameTechs, techs.nameTechs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTechs, priceTechs, quantityTechs);
    }
}
