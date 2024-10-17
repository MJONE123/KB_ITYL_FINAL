import { authInstance } from "../utils/instance"

export const getAssetAfterYear = async()=>{
  const response = await authInstance.post(`${import.meta.env.VITE_MY_SERVER_URL}/assets/after-year`);
  return response;
}