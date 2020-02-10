export class Autor {
  firstname: string;
  lastname: string;
  email: string;
  city: string;
  state: string;

  constructor(a: AutorInterface = {}) {
    this.firstname = a.firstname;
    this.lastname = a.lastname;
    this.email = a.email;
    this.city = a.city;
    this.state = a.state;
  }
}

interface AutorInterface {
  firstname?: string;
  lastname?: string;
  email?: string;
  city?: string;
  state?: string;
}
