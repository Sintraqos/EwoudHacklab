package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.UserLibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLibraryRepository extends JpaRepository<UserLibraryEntity, Integer> {
    UserLibraryEntity findByAccountIDAndGameID(int accountID, int gameID);

    List<UserLibraryEntity> findByAccountID(int accountID);
}
