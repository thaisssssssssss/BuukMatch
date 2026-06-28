package br.ufes.pi.trabalho.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;



@Table(name = "tb_user")
@Entity
public class User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", unique=true)
    private String name;
    @Column(name = "email", unique=true)
    private String email;
    private String password;
    private String photo;
    private LocalDate birthdate;
    private ArrayList<BookGenre> GenresFavoritos; 

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    private Address Address;
    private ArrayList<Match> matches;  

    protected User(){}

    public User(String name, String email, String password, LocalDate birthdate, Address Address){
        setname(name);
        setEmail(email);
        setPassword(password);
        this.birthdate = birthdate;
        this.Address = Address;
        this.matches = new ArrayList<Match>();
        this.posts = new ArrayList<Post>();
        this.GenresFavoritos = new ArrayList<BookGenre>();
    }

    public void addMatch(Match match){
        this.matches.add(match);
    }

    public void addPost(Post post){
        this.posts.add(post);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean comparePassword(String password){
//        if(!this.password.equals(password)) throw new Exception("password incorreta");
        if(!this.password.equals(password)) return false;
        else return true;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getPhoto() {
        return photo;
    }

    public Address getAddress() {
        return Address;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public ArrayList<BookGenre> getGenresFavoritos() {
        return GenresFavoritos;
    }
}
