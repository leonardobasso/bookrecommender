export async function fetchLibraries(id: string) {
  try {
    const url = `http://localhost:7070/api/library/user/${id}`
    const resp = await fetch(url);
    if (!resp.ok) {
      throw new Error(`Errore HTTP: ${resp.status}`);
    }
    const data = await resp.json();
    if (data.status !== 'success' || !data.body) {
      throw new Error(`Errore API: ${data.status}`);
    }
    const libraryData = data.body;
    const libraries = [];

    libraryData.map(library => {
      const add = {
        id: library.libreriaId,
        title: library.nomeLibreria,
        author: library.userId,
      }
      libraries.push(add)
    })
    return libraries;
  } catch (e) {
    console.log(e)
  }
}
