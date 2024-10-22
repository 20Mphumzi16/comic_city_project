package za.ac.cput.service.customerOrderService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.customerService.CustomerService;
import za.ac.cput.service.genreService.GenreService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerOrderServiceTest {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CustomerService customerService;

    private CustomerOrder customerOrder1;
    private CustomerOrder customerOrder2;
    private CustomerOrder customerOrder3;

    private Author author1;
    private Author author2;
    private List<Author> authors = new ArrayList<>();

    static CustomerOrder savedCustomerOrder;

    @BeforeEach
    void setUp() {
        byte[] photo = new byte[0];

        Publisher publisher1 = PublisherFactory.buildPublisher(1L, "Marvel", 2000);

        author1 = AuthorFactory.buildAuthor(001L, "Lamark", "Darwin");
        author2 = AuthorFactory.buildAuthor(002L, "Jacob", "Gedleyihlekisa", "Zuma");
        authors.add(author1);
        authors.add(author2);

        Genre sciFi = genreService.read(1L);
        if (sciFi == null) {
            sciFi = genreService.create(GenreFactory.buildGenre("Sci-Fi"));
        }

        Genre action = genreService.read(2L);
        if (action == null) {
            action = genreService.create(GenreFactory.buildGenre("Action"));
        }

        Set<Genre> genres1 = Set.of(sciFi, action);

        ComicBook comicBook1 = ComicBookFactory.bookBuilder("Thor", genres1, "AsGuards Prince son of Zuis",
                "B01", 299.99, 2.00, 1, authors, publisher1, LocalDate.of(2022, 03, 04), photo);

        ArrayList<ComicBook> comicBooks = new ArrayList<>();
        comicBooks.add(comicBook1);

        Address billingAddress = BillingAddressFactory.buildBillingAddress("card", "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
        System.out.println(billingAddress);

//        Address shippingAddress = ShippingAddressFactory.buildShippingAddress(LocalTime.of(9,52), "34 Batersea Drive", "Kibbler park", "2091", "Johannesburg");
//        System.out.println(shippingAddress);

        Contact con1 = CustomerContactFactory.buildContact("leroy1@gmail.com", "0739946042", billingAddress);
        Contact con2 = CustomerContactFactory.buildContact("221164014@mycput.ac.za", "0739946042" , billingAddress);


        Customer customer1 = customerService.read(1L);
        if (customer1 == null) {
            customer1 = CustomerFactory.buildCustomer("Leroy", "Kulcha", "Liam", "Lkulcha123", con1);
            customer1 = customerService.create(customer1);
        }

        customerOrder1 = CustomerOrderFactory.buildCustomerOrder(LocalDate.of(2022, 03, 04), comicBooks, customer1, 650.00, OrderStatus.PROCESSING);
    }


    @Test
    @Order(1)
    void create() {
        System.out.println("===========================CREATE========================================");

        savedCustomerOrder = customerOrderService.create(customerOrder1);
        assertNotNull(savedCustomerOrder);
        System.out.println("Saved Order: " + customerOrder1);
    }

    @Test
    @Order(2)
    void read() {
        System.out.println("===========================READ========================================");
        CustomerOrder readCustomerOrder = customerOrderService.read(savedCustomerOrder.getOrderId());
        assertNotNull(readCustomerOrder);
        System.out.println("Read Order: " + readCustomerOrder);
    }

    @Test
    @Order(3)
    void update() {
        System.out.println("===========================UPDATE========================================");
        CustomerOrder updateCustomerOrder = new CustomerOrder.CustomerOrderBuilder().copy(savedCustomerOrder)
                .setTotalAmount(850)
                .build();

        CustomerOrder savedCustomerOrder = customerOrderService.update(updateCustomerOrder);
        assertEquals(savedCustomerOrder.getOrderId(), updateCustomerOrder.getOrderId());
        assertNotNull(savedCustomerOrder);
        System.out.println(savedCustomerOrder);
    }


    @Test
    @Order(4)
    void getall(){
        System.out.println("==================================GET ALL===========================");
        System.out.println(customerOrderService.getall());
    }

    @Test
    @Order(5)
    void delete() {

        boolean isDeleted = customerOrderService.delete(savedCustomerOrder.getOrderId());
        assertTrue(isDeleted);
        System.out.println("CustomerOrder no " + savedCustomerOrder.getOrderId() + " deleted");
    }
}