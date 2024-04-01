import { User } from "../../users/model/user";

export interface Account {
  _id: string;
  name: string;
  type: string;
  balance: number;
  user: User;
}
