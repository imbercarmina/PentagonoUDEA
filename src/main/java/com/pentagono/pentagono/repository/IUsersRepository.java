package com.pentagono.pentagono.repository;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUsersRepository extends IGenericRepository<Users, Long>{

    /*List<Users> findByName(String name);

    List<Users> getAllUsers(Long idUsers);*/

}
