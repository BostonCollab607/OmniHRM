import api from "./BaseService";

const baseURL = "/api/form";

export const submitForm = (formData) => {
    return api.post(baseURL, formData);
}

export const getFormData = () => {
    return api.get(baseURL);
}