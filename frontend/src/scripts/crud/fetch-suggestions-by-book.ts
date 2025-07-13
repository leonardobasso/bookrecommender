import * as url from "node:url";

/**
 * Wrapper per i consigliati dato un libro
 * @param id id libro che vede i consigliati
 * @return libri consiglati
 * @author Leonardo Basso
 */
export async function fetchSuggestionsByBook(id: string) {
  const url = `http://localhost:7070/api/book/suggestion/get/all/${id}`
  console.log(id)
  try {
    const resp: Response = await fetch(url)
    const data = await resp.json()
    const books = [];
    data.body.forEach(book => {
      const add = {
        id: book.id,
        title: book.nome,
        author: book.autore,
        year: book.annoPub,
        category: book.categoria,
      }
      books.push(add)
    })
    return books
  } catch (e) {
    console.error(e.message)
  }
}
