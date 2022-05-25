package files.service;

import files.model.OrderItem;
import files.model.Techs;
import files.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TechsSevice implements ITechsSevice {

    public static String path = "data/techs.scv";

    private static TechsSevice instance;

    public static TechsSevice getInstance() {
        if (instance == null)
            instance = new TechsSevice();
        return instance;
    }


    @Override
    public List<Techs> getTechs() {
        List<Techs> newTechs = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newTechs.add(Techs.parseTechs(record));
        }
        return newTechs;
    }

    @Override
    public void add(Techs newTechs) {
        newTechs.setCreatDate(Instant.now());
        List<Techs> techs = getTechs();
        techs.add(newTechs);
        CSVUtils.write(path, techs);
    }

    @Override
    public void updateName(Techs newTechs) {
        List<Techs> techs = getTechs();
        for (Techs tech : techs) {
            if (tech.getId() == newTechs.getId()) {
                String name = tech.getNameTechs();
                if (name != null && !name.isEmpty())
                    tech.setNameTechs(newTechs.getNameTechs());
                tech.setUpdateDate(Instant.now());
                CSVUtils.write(path, techs);
                break;
            }
        }
    }

    @Override
    public void updatePrice(Techs newTechs) {
        List<Techs> techs = getTechs();
        for (Techs tech : techs) {
            if (tech.getId() == newTechs.getId()) {
                double price = tech.getPriceTechs();
                if (price > 0)
                    tech.setPriceTechs(newTechs.getPriceTechs());
                tech.setUpdateDate(Instant.now());
                CSVUtils.write(path, techs);
                break;
            }
        }
    }

    @Override
    public void updateQuantity(Techs newTechs) {
        List<Techs> techs = getTechs();
        for (Techs tech : techs) {
            if (tech.getId() == newTechs.getId()) {
                double Quantity = tech.getQuantityTechs();
                if (Quantity > 0)
                    tech.setQuantityTechs(newTechs.getQuantityTechs());
                tech.setUpdateDate(Instant.now());
                CSVUtils.write(path, techs);
                break;
            }
        }
    }

    @Override
    public void updateDescription(Techs newTechs) {
        List<Techs> techs = getTechs();
        for (Techs tech : techs) {
            if (tech.getId() == newTechs.getId()) {
                String Description = tech.getType();
                if (Description != null && !Description.isEmpty())
                    tech.setType(newTechs.getType());
                tech.setUpdateDate(Instant.now());
                CSVUtils.write(path, techs);
                break;
            }
        }
    }


    @Override
    public void remove(Techs newTechs) {
        List<Techs> techs = getTechs();
        techs.removeIf(techs1 -> techs1.getId() == newTechs.getId());
        CSVUtils.write(path, techs);
    }


    @Override
    public boolean existById(long id) {
        return getTechsById(id) != null;
    }


    @Override
    public Techs getTechsById(long id) {
        List<Techs> techs = getTechs();
        for (Techs tech : techs) {
            if (tech.getId() == id)
                return tech;
        }
        return null;
    }

    @Override
    public void updateQuantityy(long id, int quantity) {
        List<Techs> techs = getTechs();
        for (Techs techss : techs) {
            if (techss.getId() == id)
                if (techss.getQuantityTechs() >= quantity) {
                    techss.setQuantityTechs(techss.getQuantityTechs() - quantity);
                    CSVUtils.write(path, techs);
                    break;
                }
        }
    }

    public void update() {
        List<Techs> techs = getTechs();
        CSVUtils.write(path, techs);
    }

    public boolean isExistId(ArrayList<Techs> list, long id) {
        for (Techs techs : list) {
            if (techs.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
