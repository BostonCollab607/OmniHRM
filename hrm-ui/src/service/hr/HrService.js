import api from "../BaseService";

const companyUrl = "/api/company";

export const createCompanyApi = (company) => {
    return api.post(companyUrl, company)
}