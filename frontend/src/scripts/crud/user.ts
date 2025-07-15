import {fetchAllLibraryBooks} from "@/scripts/crud/fetch-all-library-books.ts";

/**
 * Wrapper per il login dell'utente
 * @param userId l'id dell'utente
 * @param password la password dell'utenet
 * @return I dati dell'utente o un errore
 * @author Leonardo Basso
 */
export async function login(userId: string, password: string) {
  const url = "http://localhost:7070/api/user/login"
  const data = {
    "userID": userId,
    "password": password
  }
  const json = JSON.stringify(data)

  try {
    const resp = await fetch(url, {
      method: "POST",
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: json,
    })
    const result = await resp.json();

    if (result.status !== "success") {
      throw new Error(result.body)
    }
    await fetchAllLibraryBooks(userId)
    return result.body
  } catch (e) {
    console.log(e)
  }
}
/**
 * Wrapper per la registrazione dell'utente
 * @param userId l'id dell'utente
 * @param password la password dell'utenet
 * @param name nome utente
 * @param surname cognome utente
 * @param taxcode C.F. utente
 * @param email email utente
 * @return I dati dell'utente o un errore
 * @author Leonardo Basso
 */
export async function register(userId, password, name, surname, taxcode, email) {
  const url = "http://localhost:7070/api/user/register"
  const data = {
    "userID": userId,
    "password": password,
    "nome": name,
    "cognome": surname,
    "taxcode": taxcode,
    "email": email
  }
  const json = JSON.stringify(data)

  try {
    const resp = await fetch(url, {
      method: "POST",
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: json,
    })
    const result = await resp.json();

    if (result.status !== "success") {
      throw new Error(result.body)
    }
    return result.body
  } catch (e) {
    console.error(e.message)
  }
}
