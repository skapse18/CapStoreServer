
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.model.Product;
import com.capgemini.model.WishList;
import com.capgemini.repository.UserRepository;
import com.capgemini.service.UserService;
import com.capgemini.service.WishlistService;
import com.capgemini.service.WishlistServiceImpl;

public class WishlistTest {
	WishlistService WishlistService;
	
	@Mock
	UserRepository userRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		WishlistService = new WishlistServiceImpl(userRepository);
	}

	@Test
	public void addproducts()
	{
		WishList wishlist=new WishList();
		Product product=new Product();
		product.setId(123);
		WishlistService.add(product);
		assertEquals(product.getId(), 123);
	}
	@Test
	public void removeproducts()
	{
		
		Product product=new Product();
		product.setId(456);
		WishlistService.remove(product);
		assertNotEquals(product.getId(), 456);
	}
	@Test
	public void addproducts2()
	{
		
		Product product=new Product();
		product.setId(-1);
		WishlistService.add(product);
		assertNotEquals(null, -1);
	}
	@Test
	public void display() 
	{
		
		Product product=new Product();
		WishlistService.display();
		List<Product> list=new ArrayList<>();
		list.add(product);
		assertEquals(null,list);
		
	}
	@Test
	public void display2() 
	{
		
		Product product=new Product();
		product.setId(123);
		WishlistService.display();
		List<Product> list=new ArrayList<>();
		list.add(product);
		assertNotEquals(null,list);
		
	}

}
