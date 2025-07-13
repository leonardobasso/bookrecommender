import {state} from '@/scripts/state.ts'

/**
 * Wrapper per il fetch di tutti i libri in ogni libreria di un utente
 *
 * Usata all'inizio del programma, inizializza lo stato dei libri nelle librerie
 *
 * @param userId l'id dell'utente
 * @author Leonardo Basso
 */
export async function fetchAllLibraryBooks(userId: string) {
  try {
    const response = await fetch(`http://localhost:7070/api/library/books-user/${userId}`)
    const data = await response.json()
    if (data.status === 'success') {
      state.clearLibrary()
      for (const book of data.body) {
        state.addLibraryBook(book.id, book.nome)
      }
    } else {
      console.error(data.body)
    }
  } catch (error) {
    console.error('Server error: ', error)
  }
}
