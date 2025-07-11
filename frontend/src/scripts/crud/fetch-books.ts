export async function fetchBooks(){
  const url: string = "http://localhost:7070/api/book/all"
  try {
    const resp: Response = await fetch(url)
    const data = await resp.json()
    const books = [];
    data.forEach(book => {
      const add = {
        id: book.id,
        title: book.nome,
        author: book.autore,
        year: book.annoPub,
        category: book.autore,
      }
      books.push(add)
    })
    return books
  } catch (e)  {
    console.error(e.message)
  }
}
