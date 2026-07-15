import "./styles/Card.css"
import { BookOpen, CalendarDays, NotebookText } from "lucide-react"


function Card({ post }) {  
    console.log("Dados que chegaram no Card:", post);
    if (!post) return null;
    const { legend, photo, ownerName, book } = post;
    const { title, author, numberOfPages, publicationYear, publisher } = book || {};
    const imageSource = photo 
       ? `data:image/jpeg;base64,${photo}` 
      : "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=500&q=80";

    return (
        <div className="card-container">
            <section
             className="img-section-card sections-half"
             style={{ backgroundImage: `url(${imageSource})`}}
             ></section>
            <section className="text-section-card sections-half">
                <div className="text-container">
                    <div className="head-card">
                        <h2 className="book-name">{ title }</h2>
                        <p className="author">{ author }</p>
                    </div>

                    <div className="mid-card">
                        <p className="post-legend">
                            { legend }
                        </p>
                    </div>

                    <ul className="lista-atributos">
                        <li className="num-pages">
                            <BookOpen />
                            <span>{ numberOfPages } páginas</span>
                        </li>
                        <li className="year">
                            <CalendarDays />
                            <span>{ publicationYear }</span>
                        </li>
                        <li className="editora">
                            <NotebookText />
                            <span>Editora { publisher }</span>
                        </li>
                    </ul>
                </div>
            </section>
        </div>
    )
}

export default Card