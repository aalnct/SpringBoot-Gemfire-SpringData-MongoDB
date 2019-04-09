package com.spring.boot.learning;

import com.spring.boot.learning.dataservices.*;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;


/**
 * Created by AmitAgarwal on 2/11/19.
 */
@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = Person.class,
        clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
public class Application{

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        System.out.println("Running spring boot");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner run(PersonRepository personRepository) {
        return args -> {
            Person alice = new Person("Adult Alice", 40);
            Person bob = new Person("Baby Boy", 1);
            Person carol = new Person("Teen Carol", 13);

            System.out.println("Before Accessing data in Gemfire");
            asList(alice, bob, carol).forEach(person -> {
                System.out.println("\t" + person);
            });

            System.out.println("Saving information in Gemfire");

            personRepository.save(bob);
            personRepository.save(alice);
            personRepository.save(carol);

            System.out.println("Look up each person by name");

            asList(alice.getName(), bob.getName(), carol.getName()).forEach(name -> System.out.println("\t" + personRepository.findByName(name)));

            System.out.println("Query Adults over 18");
            stream(personRepository.findByAgeGreaterThan(18).spliterator(),false).forEach(person -> System.out.println("\t" + person));


            System.out.println("Query babies less than 5");
            stream(personRepository.findByAgeLessThan(5).spliterator(),false).forEach(person -> System.out.println("\t" + person));

            System.out.println("Query teens (between 12 and 20):");

            stream(personRepository.findByAgeGreaterThanAndAgeLessThan(12, 20).spliterator(), false)
                    .forEach(person -> System.out.println("\t" + person));
        };
    }

    @Bean
    public CommandLineRunner runApp(CustomerRepository customerRepository) {
        return (args -> {
            //save couple of customer first
            customerRepository.save(new Customer("Jack", "Bauer"));
            customerRepository.save(new Customer("Chloe", "Brian"));
            customerRepository.save(new Customer("Kim", "Bauer"));
            customerRepository.save(new Customer("David", "Palmer"));
            customerRepository.save(new Customer("Michelle", "Dessler"));

            //fetch

            log.info("Cusotmer found with findAll()");
            log.info("******************************");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            //fetch an individual customer using ID

            customerRepository.findById(1L).ifPresent(customer -> {
                log.info("Customer by findById(1)");
                log.info("*************************");
                log.info("" + customer.toString());
                log.info("");
            });

            //fetch customer by Last Name

            log.info("Customer found with findByLastName('Bauer')");
            log.info("********************************");
            customerRepository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });

            log.info("");
        });
    }

    /*@Autowired
    private StudentRepository studentRepository;*/


    /*@Override
    public void run(String... args) throws Exception {
        studentRepository.deleteAll();
        //save a couple of students
        studentRepository.save(new Student("Alice","Smith"));
        studentRepository.save(new Student("Bob", "Smith"));

        //fetch all students
        System.out.println("Students found with findAll()");
        System.out.println("-----------------------------");

        for(Student student : studentRepository.findAll()){
            System.out.println(student);
        }
        System.out.println();


        //find an individual student
        System.out.println("Student with first name");
        System.out.println("------------------------");
        System.out.println(studentRepository.findByFirstName("Alice"));

        System.out.println("Student with lastname");
        System.out.println("---------------------");
        for(Student student : studentRepository.findByLastName("Smith")){
            System.out.println(student);

        }
    }*/
}
