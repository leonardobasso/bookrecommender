/**
 * Wrapper per la creazione di una libreria da parte di un utente
 * @param userId L'id dell'utente
 * @param name Il nome dell'utente
 * @return lo status dell'operazione (success, error)
 * @author Leonardo Basso
 */
export async function createLibrary(userId: string, name: string) {
  try {
    const url = `http://localhost:7070/api/library/create`
    const resp = await fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "nomeLibreria": name,
        "userId": userId
      })
    })
    const result = await resp.json();

    if (result.status !== "success") {
      throw new Error(result.body)
    }
    return result.status
  } catch (e) {
    console.log(e)
  }
}
