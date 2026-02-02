package jsp.springboot;
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
@RestController
@RequestMapping("library/book")
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	// save single book record....
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		Book b = bookRepository.save(book);
		ResponseStructure<Book> response = new ResponseStructure();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book Record saved");
		response.setData(b);
		return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.CREATED);
	}
	// Save multiple book records....
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> books) {
		ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book Records saved");
		response.setData(bookRepository.saveAll(books));
		return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.CREATED);
	}
	@GetMapping
	//get all books records.....
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		ResponseStructure<List<Book>> response = new ResponseStructure();
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty())
			{
			throw new  NoRecordAvailableException("No records present in DB");
		} else {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Data Fetched succesfull");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
	}
	@GetMapping("/{id}")
	// get book records based on id ....
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		ResponseStructure<Book> response = new ResponseStructure();
		Optional<Book> opt = bookRepository.findById(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Data Fetched succesfull");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Id is not present in the DB");
	}
	@PutMapping
	//update the book record....
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		ResponseStructure<Book> response = new ResponseStructure();
		if (book.getId() == null) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("id should be provided to update a record");
			response.setData(null);
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.NOT_FOUND);
		}
		Optional<Book> opt = bookRepository.findById(book.getId());
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("updated");
			response.setData(bookRepository.save(book));
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Id is not present in the DB");
	}

	@DeleteMapping("/{id}")
	//delete a book record ...............
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		ResponseStructure<String> response = new ResponseStructure();
		Optional<Book> opt = bookRepository.findById(id);
		if (opt.isPresent()) {
			bookRepository.deleteById(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("deleted");
			response.setData("Succesfull");
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Id is not present in the DB");
	}
	//1
	@GetMapping("/title/{title}")
	public ResponseEntity<ResponseStructure<List<Book>>> fetchbytitle(@PathVariable String title)
	{
		ResponseStructure<List<Book>> response = new ResponseStructure();
		List<Book> books = bookRepository.findByTitle(title);
		if (books.isEmpty())
			{
			throw new  NoRecordAvailableException("No records present in DB");
		} else {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Data Fetched succesfull");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}	
	}
	//2
	@GetMapping("/{title}/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> fetchbytitle(@PathVariable String title ,String author)
	{
		ResponseStructure<List<Book>> response = new ResponseStructure();
		List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
		if (books.isEmpty())
			{
			throw new  NoRecordAvailableException("No records present in DB");
		} else {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Data Fetched succesfull");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
	}
	//3
		@GetMapping("/price/{price}")
		public ResponseEntity<ResponseStructure<List<Book>>> fetchbyPrice(@PathVariable double price)
		{
			ResponseStructure<List<Book>> response = new ResponseStructure();
			List<Book> books = bookRepository.findByPriceGreaterThan(price);
			if (books.isEmpty())
				{
				throw new  NoRecordAvailableException("No records present in DB");
			} else {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Data Fetched succesfull");
				response.setData(books);
				return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
			}
		}
		//4
			@GetMapping("/range1}/{range2}")
			public ResponseEntity<ResponseStructure<List<Book>>> fetchbytitle(@PathVariable Double startRange,Double endRange )
			{
				ResponseStructure<List<Book>> response = new ResponseStructure();
				List<Book> books = bookRepository.findByPriceBetween(startRange,endRange);
				if (books.isEmpty())
					{
					throw new  NoRecordAvailableException("No records present in DB");
				} else {
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage("Data Fetched succesfull");
					response.setData(books);
					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
				}
				
	}
			@GetMapping("/query")
			public ResponseEntity<ResponseStructure<List<Book>>> fetchbyAvailability()
			{
				ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
				List<Book> books = bookRepository.getByAvilability();
				if (books.isEmpty())
					{
					throw new  NoRecordAvailableException("No records present in DB");
				} else {
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage("Data Fetched succesfull");
					response.setData(books);
					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
				}
			}
			@GetMapping("/genre/{genre}")
			public ResponseEntity<ResponseStructure<List<Book>>> fetchbyGenre(@PathVariable  String genre )
			{
				ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
				List<Book> books = bookRepository.getByGenere(genre);
				if (books.isEmpty())
					{
					throw new  NoRecordAvailableException("No records present in DB");
				} else {
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage("Data Fetched succesfull");
					response.setData(books);
					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
				}
			}
			@GetMapping("/PublishedYear/{year}")
			public ResponseEntity<ResponseStructure<List<Book>>> fetchbyYear(@PathVariable Integer PublishedYear )
			{
				ResponseStructure<List<Book>> response = new ResponseStructure<List<Book>>();
				List<Book> books = bookRepository.getByPublishedYear(PublishedYear);
				if (books.isEmpty())
					{
					throw new  NoRecordAvailableException("No records present in DB");
				} else {
					response.setStatusCode(HttpStatus.OK.value());
					response.setMessage("Data Fetched succesfull");
					response.setData(books);
					return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
				}
			}
			
			
	
	
}
