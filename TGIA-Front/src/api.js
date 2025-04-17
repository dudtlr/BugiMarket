import axios from "axios";
const api = axios.create({
  baseURL: "http://220.194.133.70:8080/",
  headers: {
    "Content-Type": "application/json"
  },
  withCredentials: true // 쿠키, 세션 사용 위해 설정
});
export default api;
