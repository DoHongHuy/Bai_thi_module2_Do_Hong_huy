package files.service;

import files.model.Techs;

import java.util.List;

public interface ITechsSevice {

//    List<Techs> getTechs();

   List<Techs> getTechs();

    void add(Techs newTechs);

    void updateName(Techs newTechs);

    void updatePrice(Techs newTechs);

    void updateQuantity(Techs newTechs);

    void updateDescription(Techs newTechs);

    void remove(Techs newTechs);

    boolean existById(long id);

    Techs getTechsById(long id);


    void updateQuantityy(long id, int quantity);
}
