package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountLibraryRepository  extends JpaRepository<AccountLibraryEntity, Integer> {
    AccountLibraryEntity findByAccountIDAndGameID(int accountID, int gameID);
    List<AccountLibraryEntity> findByAccountID(int accountID);
}
