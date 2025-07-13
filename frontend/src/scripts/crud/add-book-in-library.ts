import {state} from "@/scripts/state.ts";

/**
 * Wrapper per aggiungere un libro a una libreria
 * @param bookId l'id del libro
 * @param title titolo del libro (utile per l'ìnserimento in libraryBook
 * @param libraryId l'id della libreira
 * @author Leonardo Basso
 */
export async function addBookInLibrary(bookId: string, title: string, libraryId: string) {
  try {
    const url: string = `http://localhost:7070/api/library/add/book`
    const res = await fetch(url, {
      method: "POST",
      headers: {
        'Accept': 'application/json',
        'content-type': 'application/json',
      },
      body: JSON.stringify({
        "idLibro": bookId,
        "idLibreria": libraryId
      })
    })
    const data = await res.json()
    if (data.status != "success") {
      throw new Error(data.body)
    }
    state.addLibraryBook(bookId, title)
    return data.body
  } catch (e) {
    console.log(e)
  }
}
