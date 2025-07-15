import {fetchImage} from "@/scripts/crud/fetch-image.ts";

/**
 * Wrapper per il fetch dei dati sul singolo libro da cercare
 *
 * @param id - Il titolo del libro, il quale funge da chiave
 * @return un dizionario contenente tutti i dati del libro
 * @author Leonardo Basso
 */
export async function fetchBook(id: string) {
  const url = `http://localhost:7070/api/book/${id}`;
  try {
    const resp = await fetch(url);
    if (!resp.ok) {
      throw new Error(`Errore HTTP: ${resp.status}`);
    }

    const data = await resp.json();
    if (data.status !== 'success' || !data.body) {
      throw new Error(`Errore API: ${data.status}`);
    }
    const bookData = data.body;
    const imageUrl = await fetchImage(bookData.nome)
    return {
      id: bookData.id,
      title: bookData.nome,
      author: bookData.autore,
      image: imageUrl,
      category: bookData.categoria,
      price: bookData.prezzo,
      year: bookData.annoPub,
      description: bookData.descrizione,
      publisher: bookData.publisher,
      month: bookData.mesePub
    };
  } catch (e: any) {
    console.error("fetchBook error:", e.message);
    throw e;
  }
}
