package com.application.repos;

import com.application.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username=:userName")
    User findByName(@Param("userName")String userName);

    @Query("select u.id from User u where u.username=:userName")
    int findIdByName(@Param("userName")String userName);

//    @Query("insert into")
//    void save(@Param("user")User user);
}
