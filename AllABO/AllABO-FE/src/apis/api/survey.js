import { baseInstance } from "../utils/instance"

export const postSurvey = (surveyValue, url) => {
    const temp = `cardType=${surveyValue[0][0]}&feeOption=${surveyValue[1][1]}&benefits=${surveyValue[2][0].substr(0,2)}&category=${surveyValue[3][0]}`;
    const response = baseInstance.post(`${import.meta.env.VITE_MY_SERVER_URL}/api/recommendations${url}?${temp}`, {surveyValue: surveyValue})
    return response;
}