package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountLibraryRepository  extends JpaRepository<AccountLibraryEntity, Integer> {
    AccountLibraryEntity findByAccountIDAndGameID(int accountID, int gameID);

    List<AccountLibraryEntity> findByAccountID(int accountID);
}
