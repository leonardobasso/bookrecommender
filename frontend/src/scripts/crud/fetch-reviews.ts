/**
 * Wrapper per il fetch di review
 * @param bookId l'Id del libro
 * @author Leonardo Basso
 */
export async function fetchReviews(bookId: number) {
  const url = `http://localhost:7070/api/review/book/${bookId}`
  try {

    const req = await fetch(url)

    if (!req.ok) {
      const errorText = await req.text()
      throw new Error(errorText)
    }

    const res = await req.json()
    console.log("Res Body", res.body)

    if (res.status !== 'success') {
      throw new Error(res.body)
    }

    const reviews = []

    let avgFinal = 0;
    let avgStyle = 0;
    let avgContent = 0;
    let avgNice = 0;
    let avgFreshness = 0;
    let avgEdition = 0;

    res.body.forEach(review => {
      const add = {
        userId: review.userId,
        libroId: review.libroId,
        stileVoto: review.stileVoto,
        contenutoVoto: review.contenutoVoto,
        gradevolezzaVoto: review.gradevolezzaVoto,
        originalitaVoto: review.originalitaVoto,
        edizioneVoto: review.edizioneVoto,
        votoFinale: review.votoFinale,
        stileCommento: review.stileCommento,
        contenutoCommento: review.contenutoCommento,
        gradevolezzaCommento: review.gradevolezzaCommento,
        originalitaCommento: review.originalitaCommento,
        edizioneCommento: review.edizioneCommento,
        commentoFinale: review.commentoFinale,
      }
      reviews.push(add)
      avgFinal += review.votoFinale ?? 0;
      avgStyle += review.stileVoto ?? 0;
      avgContent += review.contenutoVoto ?? 0;
      avgNice += review.gradevolezzaVoto ?? 0;
      avgEdition += review.edizioneVoto?? 0;
      avgFreshness += review.originalitaVoto?? 0;
    })
    const count = reviews.length;
    const data = {
      "reviews": reviews,
      "mediaVotoFinale": avgFinal/count,
      "mediaContenuto": avgContent/count,
      "mediaStile": avgStyle/count,
      "mediaGradevolezza": avgNice/count,
      "mediaEdizione": avgEdition/count,
      "mediaOriginalita": avgFreshness/count
    }
    return data
  } catch (e) {
    throw e
  }
}
