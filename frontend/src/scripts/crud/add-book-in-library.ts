/**
 * Wrapper per aggiungere un libro a una libreria
 * @param bookId l'id del libro
 * @param libraryId l'id della libreira
 * @author Leonardo Basso
 */
export async function addBookInLibrary(bookId: string, libraryId: string) {
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
    if (res.status != "success") {
      throw new Error(res.body)
    }
    return res.body
  } catch (e) {
    console.log(e)
  }
}
