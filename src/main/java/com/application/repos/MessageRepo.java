package com.application.repos;

import com.application.beans.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {

    @Query("SELECT max(id) FROM Message m where m.author.id=:userId")
    public long getLastId(@Param("userId") long userId);

    @Query("SELECT m FROM Message m WHERE m.author.id=:userId and m.filePath is not null ")
    public Iterable<Message> getUploaded(@Param("userId") long userId);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Modifying
    @Query("UPDATE Message m SET m.filePath=:filePath where m.id=:id and m.author.id=:userId")
    public void updateFilePath(@Param("id") long id,@Param("userId") long userId,@Param("filePath") String filePath);

    @Query("SELECT m FROM Message m")
    Iterable<Message> getAll();
}
