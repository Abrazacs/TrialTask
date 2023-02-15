package ru.sergeysemenov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sergeysemenov.entities.Quote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {

    @Query(value = "SELECT * FROM QUOTES q ORDER BY q.rank DESC LIMIT 10",  nativeQuery = true)
    List<Quote> findBestTenByVotes();
    @Query(value = "SELECT * FROM QUOTES q ORDER BY q.rank LIMIT 10",  nativeQuery = true)
    List<Quote> findWorstTenByVotes();

    @Query(value = "SELECT * FROM QUOTES ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> getRandomQuote();


}
