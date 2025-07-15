/**
 * Wrapper che crea una review di un libro
 * @param userId l'id dell'utente che scrive la review
 * @param bookId l'id del libro recensito
 * @param votoStile voto numerico (1..5) allo stile
 * @param votoContenuto voto numerico (1..5) al contenuto
 * @param votoGradevolezza voto numerico (1..5) alla gradevolezza
 * @param votoOriginalita voto numerico (1..5) all'originalità
 * @param votoEdizione voto numerico (1..5) all'edizione
 * @param votoFinale voto numerico (1..5) complessivo
 * @param commentoStile commento scritto sullo stile
 * @param commentoContenuto commento scritto sul contenuto
 * @param commentoGradevolezza commento scritto sulla gradevolezza
 * @param commentoOriginalita commento scritto sull'originalità
 * @param commentoEdizione commento scritto sull'edizione
 * @param commentoFinale commento scritto complessivo
 * @author Leonardo Basso
 */
export async function createReview(userId: string, bookId: number, votoStile: number,
                                   votoContenuto: number, votoGradevolezza: number,
                                   votoOriginalita: number, votoEdizione: number, votoFinale: number,
                                   commentoStile: string, commentoContenuto: string,
                                   commentoGradevolezza: string, commentoOriginalita: string,
                                   commentoEdizione: string, commentoFinale: string) {
  const url: string = "http://localhost:7070/api/review/create"
  try {
    const resp = await fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "userId": userId,
        "libroId": bookId,
        "stileVoto": votoStile,
        "contenutoVoto": votoContenuto,
        "gradevolezzaVoto": votoGradevolezza,
        "originalitaVoto": votoOriginalita,
        "edizioneVoto": votoEdizione,
        "votoFinale": votoFinale,
        "stileCommento": commentoStile,
        "contenutoCommento": commentoContenuto,
        "gradevolezzaCommento": commentoGradevolezza,
        "originalitaCommento": commentoOriginalita,
        "edizioneCommento": commentoEdizione,
        "commentoFinale": commentoFinale,
      })
    })
    const result = await resp.json();

    if (result.status !== "success") {
      throw new Error(result.body)
    }
    return result.status

  } catch (e) {
    throw e
  }
}
