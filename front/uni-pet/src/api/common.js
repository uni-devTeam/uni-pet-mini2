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

export const boardWriteReq = async (writeData) => {
    const response = await authApi.post("/board/save", writeData);
    return response;
};

export const boardDeleteReq = async (boardNo) => {
    const response = await authApi.delete(`/board/delete/${boardNo}`);
    return response;
};


export const boardUpdateReq = async (boardData) => {
    const response = await authApi.put("/board/update", boardData);
    return response;
};


export const commentWriteReq = async (commentData) => {
    const response = await authApi.post("/board/comment", commentData);
    return response;
};

export const commentDeleteReq = async (commentId) => {
    const response = await authApi.delete(`/board/comment/delete/${commentId}`);
    return response;
};




