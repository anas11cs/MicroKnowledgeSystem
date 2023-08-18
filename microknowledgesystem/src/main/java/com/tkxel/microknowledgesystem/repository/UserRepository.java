package com.tkxel.microknowledgesystem.repository;

import com.tkxel.microknowledgesystem.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    boolean existsByIdAndUserPassword(String id, String password);
    @Query("select user from UserModel user left join fetch user.staredMicroknowledge " +
            "left join fetch  user.readertoComment " +
            "left join fetch user.containedMicroknowledge  where user.userName =:userName")
    UserModel findByUserName(@Param("userName") String userName);


}
