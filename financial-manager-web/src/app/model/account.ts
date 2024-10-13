import { User } from "./user";

export interface Account {
  _id: string;
  name: string;
  type: string;
  balance: number;
  user: User;
}
