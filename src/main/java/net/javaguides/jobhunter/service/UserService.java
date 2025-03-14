package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.User;
import net.javaguides.jobhunter.domain.response.ResCreateUserDTO;
import net.javaguides.jobhunter.domain.response.ResUpdateUserDTO;
import net.javaguides.jobhunter.domain.response.ResUserDTO;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    User handleCreateUser(User user);
    void handleDeleteUser(long id);
    User fetchUserById(long id);
    ResultPaginationDTO fetchAllUser(Specification<User> spec, Pageable pageable);
    User handleUpdateUser(User reqUser);
    User handleGetUserByUsername(String username);
    boolean isEmailExist(String email);
    ResCreateUserDTO convertToResCreateUserDTO(User user);
    ResUpdateUserDTO convertToResUpdateUserDTO(User user);
    ResUserDTO convertToResUserDTO(User user);
    void updateUserToken(String token, String email);
    User getUserByRefreshTokenAndEmail(String token, String email);
}
