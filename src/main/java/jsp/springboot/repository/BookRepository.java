package jsp.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	
	//fetch based on title 
	List<Book> findByTitle(String title);
	// fetch based on two fields
	List<Book> findByTitleAndAuthor(String title, String author);
	// fetch based on between 2 range 
	List<Book> findByPriceBetween(Double startRange , Double endRange);
	// fetch based on a field 
	List<Book> findByPriceGreaterThan(Double price);
	
	@Query("select b from Book  b where b.availability=true")
	List<Book> getByAvilability();
	@Query("select b from Book  b where b.publishedYear=?1")
	List<Book> getByPublishedYear(Integer year);
	@Query("select b from Book b where b.genere=:genere")
	List<Book> getByGenere(String genere);
	
	
}
