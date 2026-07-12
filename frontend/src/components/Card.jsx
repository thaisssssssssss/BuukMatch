import "./styles/Card.css"
import { BookOpen, CalendarDays, NotebookText } from "lucide-react"

function Card({ post: {title, author, description, pageCount, publicationYear, publisher, imgSrc} }) {    
    console.log(imgSrc)
    return (
        <div className="card-container">
            <section
             className="img-section-card sections-half"
             style={{ backgroundImage: `url(${imgSrc})`}}
             ></section>
            <section className="text-section-card sections-half">
                <div className="text-container">
                    <div className="head-card">
                        <h2 className="book-name">{ title }</h2>
                        <p className="author">{ author }</p>
                    </div>

                    <div className="mid-card">
                        <p className="post-description">
                            { description }
                        </p>
                    </div>

                    <ul className="lista-atributos">
                        <li className="num-pages">
                            <BookOpen />
                            <span>{ pageCount } páginas</span>
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