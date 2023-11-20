import axios from "axios";
import { apiInstance, authApi } from "./config";

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

export const signupReq = async (formData) => {
  const response = await apiInstance.post("/signup", formData);
  return response;
};

export const loginReq = async (loginData) => {
  const response = await apiInstance.post("/login", loginData);
  return response;
};

export const headerTestReq = async () => {
  const response = await authApi.get("/");
  return response;
};
