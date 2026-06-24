package br.ufes.pi.trabalho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * A anotação @SpringBootApplication faz com que o servidor Web seja iniciado e
 * classes anotadas com @Controller ou @RestController sejam incluídas como
 * controladores de requisições Web.
 */
@SpringBootApplication
public class TrabalhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoApplication.class, args);
	}

}
