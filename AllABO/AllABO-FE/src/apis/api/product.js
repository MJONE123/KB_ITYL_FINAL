import { baseInstance } from "../utils/instance";

export const requestProductsData = (path) => {
  const response = baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/product${path}`);
  return response;
};

// export const getCreditCardData = () => {
//   const response = baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/product/credit-card`);
//   return response;
// };

// export const getLoan = () => {
//   const response = baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/product/loan`);
//   return response;
// };

// export const getDeposit = () => {
//   const response = baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/product/deposit`);
//   return response;
// };

// export const getInsurance = () => {
//   const response = baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/product/insurance`);
//   return response;
// };
