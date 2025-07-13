import {fetchAllLibraryBooks} from "@/scripts/crud/fetch-all-library-books.ts";

export async function createSuggestion(userId: string, raccomandedB: number, initiaB: number) {
  const url = "http://localhost:7070/api/book/suggestion/create"
  const data = {
    "userId": userId,
    "libroConsigliatoId": Number(raccomandedB),
    "libroDeiConsigliId": Number(initiaB)
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
    console.log(result.body)
    return result.body
  } catch (e) {
    console.log(e)
    throw new Error(e).message
  }
}
