package br.ufes.pi.trabalho.dto;


public class CreatePostRequest{
    private String legend;
    private String photo;
    private BookRequest book;
    

    public CreatePostRequest(String legend, String photo, BookRequest bookRequest){
        setLegend(legend);
        this.photo = photo;
        setBookRequest(bookRequest);
    }

    protected CreatePostRequest(){}
    
    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }
    
    public String getPhoto() {
        return photo;
    }

    public BookRequest getBook() {
        return book;
    }

    public void setBookRequest(BookRequest book) {
        this.book = book;
    }
}