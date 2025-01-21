package com.sintraqos.portfolioproject.userLibrary.DAL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLibraryRepository extends JpaRepository<UserLibraryEntity, Integer> {
    UserLibraryEntity findByAccountIDAndGameID(int accountID, int gameID);

    List<UserLibraryEntity> findByAccountID(int accountID);
}
