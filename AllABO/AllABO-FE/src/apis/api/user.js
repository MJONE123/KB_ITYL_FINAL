import { baseInstance } from "../utils/instance";

export const login = async (id, pwd) => {
  const response = await baseInstance.post(`${import.meta.env.VITE_MY_SERVER_URL}/login`, { id: id, pwd: pwd });
  return response;
};

export const idDupChk = async id => {
  const response = await baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/iddupchk/${id}`);
  // const response = await baseInstance.get(`${import.meta.env.VITE_MY_SERVER_URL}/iddupchk/teddt@example.com`);
  return response;
};
