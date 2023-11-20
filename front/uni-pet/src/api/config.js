import axios from "axios";

const apiConfig = {
  baseURL: "http://localhost:8889/",
  headers: {
    "Content-Type": "application/json",
  },
};

// 인터 셉터
// authApi.interceptors.request.use(config => {
//     config.headers.Authorization = `Bearer ${getLocalStorageToken()}`;
//     return config;
//   });

export const apiInstance = axios.create(apiConfig);
