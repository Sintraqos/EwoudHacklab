package com.sintraqos.portfolioproject.userLibrary.DAL;

// Spring components
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Java components
import java.util.List;

@Repository
public interface UserLibraryRepository extends JpaRepository<UserLibraryEntity, Integer> {
    UserLibraryEntity findByAccountIDAndGameID(int accountID, int gameID);

    List<UserLibraryEntity> findByAccountID(int accountID);
}
