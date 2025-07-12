/**
 * Wrapper per il fetch della ricerca del libro
 *
 * @param title Titolo del libro
 * @param author Nome autore
 * @param year Anno pub
 * @author Leonardo Basso
 *
 */
export async function searchBooks(title: string, author: string, year: number) {
  const seachParam = new URLSearchParams();
  if (title) {
    seachParam.set("nome", title)
  }
  if (author) {
    seachParam.set("autore", author)
  }
  if (year) {
    seachParam.set("annoPub", year)
  }
  const url = `http://localhost:7070/api/book/search?${seachParam.toString()}`
  try {
    const resp = await fetch(url)
    if (!resp.ok) {
      throw new Error(`Errore HTTP: ${resp.status}`);
    }

    const data = await resp.json();
    if (data.status !== 'success' || !data.body) {
      throw new Error(`Errore API: ${data.status}`);
    }
    const bookData = data.body;
    const books = [];
    bookData.forEach(book => {
      const add = {
        id: book.id,
        title: book.nome,
        author: book.autore,
        year: book.annoPub,
      }
      books.push(add)
    })
    console.log(bookData)
    return books
  } catch (e: any) {
    console.error("searchBook error:", e.message);
    throw e;
  }
}
