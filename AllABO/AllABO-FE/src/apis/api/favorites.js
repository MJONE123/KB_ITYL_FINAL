import { authInstance } from "../utils/instance"

export const addFavorite = async(productId, productNum)=>{
  // console.log(productId, productNum)
  const response = await authInstance.post(`${import.meta.env.VITE_MY_SERVER_URL}/api/favorites/add?productId=${productId}&productNum=${productNum}`)
  return response;
}

export const removeFavorite = async(productId, productNum)=>{
  const response = await authInstance.post(`${import.meta.env.VITE_MY_SERVER_URL}/api/favorites/remove?productId=${productId}&productNum=${productNum}`)
  return response;

}

export const getMyFavoritesList = async()=>{
  const response = await authInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/api/favorites/list`)
  return response
}

export const getMyDetailFavoritesList = async()=>{
  const response = await authInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/api/favorites/mapping-list`);
  return response
}