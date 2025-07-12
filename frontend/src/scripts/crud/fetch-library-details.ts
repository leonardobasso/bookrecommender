/**
 * Wrapper che ritorna tutti i libri contenuti in una data lbreria
 * @param id l'id della libreria
 * @author Leonardo Basso
 */
export async function fetchLibraryDetails(id: string) {
  try {
    const url: string = `http://localhost:7070/api/library/details/${id}`
    const req = await fetch(url)

    if (!req.ok) {
      const errorText = await req.text()
      throw new Error(errorText)
    }

    const res = await req.json()

    if (res.status !== 'success') {
      throw new Error(res.body)
    }

    const books = []
    res.body.forEach(book => {
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
    console.error("Errore nel fetchLibraryDetails:", e)
    return []
  }
}
