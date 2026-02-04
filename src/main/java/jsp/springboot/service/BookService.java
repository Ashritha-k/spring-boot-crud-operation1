package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jsp.springboot.dao.BookDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		
		ResponseStructure<Book> response = new ResponseStructure();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book Record saved");
		response.setData(bookDao.saveBook(book));
		return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.CREATED);

}
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> books) {
		ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book Records saved");
		response.setData(bookDao.saveAll(books));
		return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Data Fetched succesfull");
			response.setData(bookDao.getAllBooks());
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Book>> getBookById(Integer id) {
		ResponseStructure<Book> response = new ResponseStructure<Book>();
		 Book book = bookDao.getBookById(id);

		    if (book == null) {
		        throw new IdNotFoundException("Id is not present in the DB");
		    }
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Data Fetched succesfull");
			response.setData(bookDao.getBookById(id));
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		}
	 public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
	        Book updated = bookDao.updateBook(book);
	        if (updated == null) {
	            throw new IdNotFoundException("Id is not present in the DB");
	        }

	        ResponseStructure<Book> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Book updated");
	        response.setData(updated);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  public ResponseEntity<ResponseStructure<String>> deleteBook(Integer id) {
	        ResponseStructure<String> response = new ResponseStructure<>();
	        bookDao.deleteBook(id);
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Book deleted successfully");
	        response.setData("Deleted");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	
		
	}
	

