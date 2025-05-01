/**
 * Wrapper per il fetch dei dati sul singolo libro da cercare
 *
 * @param title - Il titolo del libro, il quale funge da chiave
 * @return un dizionario contenente tutti i dati del libro
 * @author Leonardo Basso
 */
export async function fetchBook(title: string) {
  const url: string = 'https://www.googleapis.com/books/v1/volumes?q=' + title
  try {
    const resp: Response = await fetch(url)
    const data = await resp.json()

    const title: string = data.items?.[0].volumeInfo.title || ''
    console.log('titolo: ' + title)
    const author: string = data.items?.[0].volumeInfo.authors?.[0] || ''
    const category: string = data.items?.[0].volumeInfo.categories?.[0] || ''
    const price: int = 20
    const description: string = 'lorem ipsum dolor sit amen'
    const year: int = data.items?.[0].volumeInfo.publishedDate || 0

    return { title: title,
      author: author,
      category: category,
      price: price,
      year: year,
      description: description }
  } catch (e) {
    console.log(e.message)
  }
}
