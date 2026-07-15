package br.ufes.pi.trabalho.dto;


public class CreatePostRequest{
    private String legend;
    private BookRequest book =  new BookRequest();
    

    public CreatePostRequest(String legend, BookRequest bookRequest){
        setLegend(legend);
        this.book = bookRequest;
    }

    protected CreatePostRequest(){}
    
    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public BookRequest getBook() {
        return book;
    }

    public void setBook(BookRequest book) {
        this.book = book;
    }
}