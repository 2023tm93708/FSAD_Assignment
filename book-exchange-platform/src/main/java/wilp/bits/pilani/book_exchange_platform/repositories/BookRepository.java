package wilp.bits.pilani.book_exchange_platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wilp.bits.pilani.book_exchange_platform.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}