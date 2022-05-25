package files.service;

import files.model.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> getAdmin();

    Admin loginAdmin(String username, String password);

    void add(Admin newAdmin);

    void update(Admin newAdmin);

    void updateName(Admin newAdmin);

    void updatePhone(Admin newAdmin);

    void updateAddress(Admin newAdmin);

    void remove(Admin newAdmin);

    boolean existById(long id);

    boolean checkDuplicateEmail(String Email);

    boolean checkDuplicatePhone(String phone);

    boolean checkDuplicateUserName(String userName);

    Admin getUserById(long id);

    Admin getUserByAdminName(String userName);

}


