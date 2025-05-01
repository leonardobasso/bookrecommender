import Img from '../../assets/img/defaultCover.png'
/**
 * Questa funzione, usango Google Book API, ritorna il link della copertina di un determinato libro
 * il cui titolo è dato in input
 *
 * @param title il titolo del libro da cercare
 * @author Leonardo Basso
 */
export async function fetchImage(title: string) {
  const url: string = 'https://www.googleapis.com/books/v1/volumes?q=' + title
  try {
    const resp: Response = await fetch(url)
    const data = await resp.json()
    return data.items?.[0].volumeInfo.imageLinks.thumbnail
  } catch(e) {
    console.log(e.message)
    return Img
  }
}
