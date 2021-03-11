export interface Pantry {
  id: number;
  name: string;
  userId: number;
  creationDate: Date;
  // productsInPantry: {
  //   pantryId: number,
  //   productId: number,
  //   quantity: number
  // }[];
}

export interface Product {
  id: number;
  name: string;
  category: string;
  barcode: number;
  // productsInPantry: {
  //   pantryId: number,
  //   productId: number,
  //   quantity: number
  // }[];
}

export interface User {
  id: number;
  name: string;
  email: string;
  password: string;
  role: string;
}
