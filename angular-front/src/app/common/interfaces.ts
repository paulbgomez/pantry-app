export interface Pantry {
  id: number;
  name: string;
  userId: number;
  creationDate: Date;
  ListOfProducts?: ProductWithStock[];
}

export interface Product {
  id: number;
  name: string;
  category: string;
  barcode: number;
}

export interface ProductWithStock extends Product{
  stock?: number;
}

export interface User {
  id: number;
  name: string;
  email: string;
  password: string;
  role: string;
}
