import axios from "axios";
import { apiInstance } from "./config";

const api = async (url, method, data) => {
  return (
    await axios({
      method: method,
      url,
      data,
    }).catch((e) => {
      console.log(e);
    })
  ).data;
};

export { api };

export const signup = async (formData) => {
  const response = await apiInstance.post("/signup", formData);
  console.log(response);
  return response;
};
