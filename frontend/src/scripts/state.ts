import { reactive } from 'vue'

/**
 * Implementazione del sistema di stato globale
 * @author Leonardo Basso
 */
export const state = reactive({
  user: {
    isLogged: false,
    userId: '',
    name: '',
    surname: '',
    email: '',
    taxcode: '',
    /**
     * Questa funzione cambia lo stato andando a inserire l'id dell'utente loggato e il parametro
     * `isLogged` come `true`
     * @param id
     * @param name
     * @param surname
     * @param email
     * @param taxcode
     * @author Leonardo Basso
     */
    setLogin(id: string, name: string, surname: string, email: string, taxcode: string): void {
      this.userId = id
      this.name = name
      this.surname = surname
      this.email = email
      this.taxcode = taxcode
      this.isLogged = true
    },
    /**
     * Questa funzione va a rimuovere il precedente userId e a pone `isLogged` come `false`
     * @author Leonardo Basso
     */
    setLogOut(): void {
      this.userId = ''
      this.name = ''
      this.surname = ''
      this.email = ''
      this.taxcode = ''
      this.isLogged = false
    },
  },
})
