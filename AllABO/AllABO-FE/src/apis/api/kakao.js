import axios from "axios";
import { baseInstance } from "../utils/instance";

export const kakaoLoginRequestCodeHandler = async () => {
  window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=${import.meta.env.VITE_KAKAO_REST_API_KEY}&redirect_uri=${
    import.meta.env.VITE_KAKAO_REDIRECT_URL
  }&response_type=code`;
};

export const kakaoLoginRequestTokenHandler = async code => {
  const params = new URLSearchParams(); //form의 data
  params.append("code", code);
  const response = await baseInstance.post(`${import.meta.env.VITE_MY_SERVER_URL}/kakao-redirect`, {code: code});
  return response;
};
