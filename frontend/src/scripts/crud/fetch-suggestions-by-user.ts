/**
 * Wrapper per la richiesta dei libri suggeriti dall'utente
 * @param userId id dell'utente
 * @param bookId libro che vede i suggerimenti
 * @author Leonardo Basso
 */
export async function fetchSuggestionsByUser(userId: string, bookId: number) {
  const url = `http://localhost:7070/api/book/suggestion/get/user`
  console.log(userId)
  console.log(bookId)
  try {
    const req = {
      "userId": userId,
      "libroDeiConsigliId": bookId
    }
    const resp: Response = await fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(req)
    })
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
