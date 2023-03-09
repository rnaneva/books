let loadBooksBtn = document.getElementById('loadBooks')
loadBooksBtn.addEventListener('click', reloadBooks)


function reloadBooks() {

    let booksContainer = document.getElementById('books-container');
    booksContainer.innerText = ''

    fetch("http://localhost:8080/api/books")
        .then(result => result.json())
        .then(json => json.forEach(book => {
            let row = document.createElement("tr");

            let isbn = document.createElement("td")
            let author = document.createElement("td")
            let title = document.createElement("td")
            let actionCol = document.createElement("td")
            let deleteBtn = document.createElement('button')

            isbn.innerText = book.isbn;
            author.innerText = book.author.name;
            title.innerText = book.title;

            deleteBtn.addEventListener('click', deleteBook)


            actionCol.appendChild(deleteBtn)
            row.appendChild(isbn)
                row.appendChild(author)
                    row.appendChild(title)
                        row.appendChild(actionCol)
            booksContainer.appendChild(row)
        }))
}

function deleteBook(event) {
    let bookId = event.target.dataset.id;

    const requestOptions = {
        method: 'DELETE'
    };

    fetch(`http://localhost:8080/api/books/${bookId}`, requestOptions)
        .then(_ => reloadBooks()).catch(error => console.log(error))
}