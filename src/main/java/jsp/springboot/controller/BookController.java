package jsp.springboot.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.BookRepository;
import jsp.springboot.service.BookService;
@RestController
@RequestMapping("library/book")
public class BookController {
	@Autowired
	private BookService bookService;
	// save single book record....
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
		
		
	}
	// Save multiple book records....
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> books) {
		
		return bookService.saveAllBooks(books);
	}
	
	@GetMapping
	//get all books records.....
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		
			return bookService.getAllBooks();
		}
	
	@GetMapping("/{id}")
	// get book records based on id ....
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);
	}
	@PutMapping
    public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
	}

	 @DeleteMapping("/{id}")
	    public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
	        return bookService.deleteBook(id);
	    }
//	//1
//	@GetMapping("/title/{title}")
//	public ResponseEntity<ResponseStructure<List<Book>>> fetchbytitle(@PathVariable String title)
//	{
//		ResponseStructure<List<Book>> response = new ResponseStructure();
//		List<Book> books = bookRepository.findByTitle(title);
//		if (books.isEmpty())
//			{
//			throw new  NoRecordAvailableException("No records present in DB");
//		} else {
//			response.setStatusCode(HttpStatus.OK.value());
//			response.setMessage("Data Fetched succesfull");
//			response.setData(books);
//			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//		}	
//	}
//	//2
//	@GetMapping("/{title}/{author}")
//	public ResponseEntity<ResponseStructure<List<Book>>> fetchbytitle(@PathVariable String title ,String author)
//	{
//		ResponseStructure<List<Book>> response = new ResponseStructure();
//		List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
//		if (books.isEmpty())
//			{
//			throw new  NoRecordAvailableException("No records present in DB");
//		} else {
//			response.setStatusCode(HttpStatus.OK.value());
//			response.setMessage("Data Fetched succesfull");
//			response.setData(books);
//			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//		}
//	}
//	//3
//		@GetMapping("/price/{price}")
//		public ResponseEntity<ResponseStructure<List<Book>>> fetchbyPrice(@PathVariable double price)
//		{
//			ResponseStructure<List<Book>> response = new ResponseStructure();
//			List<Book> books = bookRepository.findByPriceGreaterThan(price);
//			if (books.isEmpty())
//				{
//				throw new  NoRecordAvailableException("No records present in DB");
//			} else {
//				response.setStatusCode(HttpStatus.OK.value());
//				response.setMessage("Data Fetched succesfull");
//				response.setData(books);
//				return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//			}
//		}
//		//4
//			@GetMapping("/range1}/{range2}")
//			public ResponseEntity<ResponseStructure<List<Book>>> fetchbytitle(@PathVariable Double startRange,Double endRange )
//			{
//				ResponseStructure<List<Book>> response = new ResponseStructure();
//				List<Book> books = bookRepository.findByPriceBetween(startRange,endRange);
//				if (books.isEmpty())
//					{
//					throw new  NoRecordAvailableException("No records present in DB");
//				} else {
//					response.setStatusCode(HttpStatus.OK.value());
//					response.setMessage("Data Fetched succesfull");
//					response.setData(books);
//					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//				}
//				
//	}
//			@GetMapping("/query")
//			public ResponseEntity<ResponseStructure<List<Book>>> fetchbyAvailability()
//			{
//				ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
//				List<Book> books = bookRepository.getByAvilability();
//				if (books.isEmpty())
//					{
//					throw new  NoRecordAvailableException("No records present in DB");
//				} else {
//					response.setStatusCode(HttpStatus.OK.value());
//					response.setMessage("Data Fetched succesfull");
//					response.setData(books);
//					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//				}
//			}
//			@GetMapping("/genre/{genre}")
//			public ResponseEntity<ResponseStructure<List<Book>>> fetchbyGenre(@PathVariable  String genre )
//			{
//				ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
//				List<Book> books = bookRepository.getByGenere(genre);
//				if (books.isEmpty())
//					{
//					throw new  NoRecordAvailableException("No records present in DB");
//				} else {
//					response.setStatusCode(HttpStatus.OK.value());
//					response.setMessage("Data Fetched succesfull");
//					response.setData(books);
//					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//				}
//			}
//			@GetMapping("/PublishedYear/{year}")
//			public ResponseEntity<ResponseStructure<List<Book>>> fetchbyYear(@PathVariable Integer PublishedYear )
//			{
//				ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
//				List<Book> books = bookRepository.getByPublishedYear(PublishedYear);
//				if (books.isEmpty())
//					{
//					throw new  NoRecordAvailableException("No records present in DB");
//				} else {
//					response.setStatusCode(HttpStatus.OK.value());
//					response.setMessage("Data Fetched succesfull");
//					response.setData(books);
//					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
//				}
//			}
//			
			
	
	
}
