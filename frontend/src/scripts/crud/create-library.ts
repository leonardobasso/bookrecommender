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
