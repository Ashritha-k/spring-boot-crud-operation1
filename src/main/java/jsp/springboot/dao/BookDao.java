package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.BookRepository;

@Repository
public class BookDao {
	@Autowired
	private BookRepository bookRepository;
	
	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}
	 public List<Book> saveAll(List<Book> books) {
	        return bookRepository.saveAll(books);
	    }
	 public List<Book> getAllBooks(){
		 List<Book> books = bookRepository.findAll();
			if (books.isEmpty())
				{
				throw new  NoRecordAvailableException("No records present in DB");
			}
			else return bookRepository.findAll();
	 }
	 public Book getBookById(Integer id) {
	
			Optional<Book> opt = bookRepository.findById(id);
			if (opt.isPresent()) {
				return opt.get();
			}
			else return null;
	 } 
	 public Book updateBook(Book book) {
	        if (book.getId() == null) {
	            throw new IdNotFoundException("Id must be provided");
	        }
	        Optional<Book> opt = bookRepository.findById(book.getId());
	        if (opt.isPresent()) {
	            return bookRepository.save(book);
	        }
	        throw new IdNotFoundException("Id not found");
	    }
	 public void deleteBook(Integer id) {
	        Optional<Book> opt = bookRepository.findById(id);
	        if (opt.isPresent()) {
	            bookRepository.deleteById(id);
	        } else {
	            throw new IdNotFoundException("Id not found");
	        }
	    }

	   



	 
}
