
package za.ac.cput.repository;
// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ComicBookRepository extends JpaRepository<ComicBook, Long> {
    List<ComicBook> findByNameContainingIgnoreCase(String name);
    List<ComicBook> findBySku(Long sku);
    List<ComicBook> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT c FROM ComicBook c JOIN c.genres g WHERE g IN :genres")
    List<ComicBook> findByGenresIn(@Param("genres") Set<Genre> genres);

    // Method to get the quantity left for a specific book by SKU
    @Query("SELECT b.quantity FROM ComicBook b WHERE b.sku = :bookSKU")
    int getBookQuantity(@Param("bookSKU") Long bookSKU);

    // Method to decrement the quantity if available after book is bought
    @Query("UPDATE ComicBook b SET b.quantity = b.quantity - :quantity WHERE b.sku = :bookSKU AND b.quantity >= :quantity")
    @Modifying
    int decrementBookQuantity(@Param("bookSKU") Long bookId, @Param("quantity") int quantity);

}
